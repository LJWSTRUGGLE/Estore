package com.briup.estore.web.servlet;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.briup.estore.bean.Customer;
import com.briup.estore.bean.Line;
import com.briup.estore.bean.Order;
import com.briup.estore.common.AlipayClientFactory;
import com.briup.estore.common.exception.LineException;
import com.briup.estore.common.exception.OrderException;
import com.briup.estore.service.ILineService;
import com.briup.estore.service.IOrderService;
import com.briup.estore.service.impl.LineServiceImp;
import com.briup.estore.service.impl.OrderServiceImp;

@SuppressWarnings("unused")
@WebServlet("/user/payOrder")
public class PayOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IOrderService orderService = new OrderServiceImp(); 
	private ILineService iLineService =new LineServiceImp();
    public PayOrderServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		session.setMaxInactiveInterval(60*60);
		String subject = "estore";
		
		
		/*Long id = Long.parseLong(req.getParameter("id"));
		Double total = 0.0;
		order.setStatus(1);
		orderService.updateOrderStatusById(id);*/
		//orderService.findById(curOrderId);
		Long id = Long.parseLong(req.getParameter("id"));
		Double total = 0.0;
		Order order;
		try {
			order = orderService.findById(id);
			System.out.println(order);
			req.setAttribute("order", order);
			total = order.getCost();
			order.setStatus(1);
			orderService.updateOrderStatusById(id);	
		} catch (OrderException e1) {
			
			e1.printStackTrace();
		}
	   
		/*for (Order order : orders) {
			if(order.getId() == id) {
				total = order.getCost();
				order.setStatus(1);
				orderService.updateOrderStatusById(id);
				break;
			}*/
		
		
		
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		AlipayTradePagePayModel alipayModel = new AlipayTradePagePayModel();

		String path = req.getContextPath();
		String basePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+path+"/";
		
		//回调
		//alipayRequest.setNotifyUrl(basePath+"/confirm.do");
		 alipayRequest.setReturnUrl("http://127.0.0.1:8989/estore/user/orderFindAll");

		alipayRequest.setBizModel(alipayModel);
		JSONObject jsonObject = new JSONObject();
		/* 针对 统一收单交易创建接口 */
		/* (必选) 商户订单号,64个字符以内、可包含字母、数字、下划线；需保证在商户端不重复 */
		jsonObject.put("out_trade_no", System.currentTimeMillis());
		/* (必选) 订单标题 */
		jsonObject.put("subject", subject);
		/* (必选) 订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000] */
		jsonObject.put("total_amount", total);
		/* (可选) 销售产品码 */
		jsonObject.put("product_code", "FAST_INSTANT_TRADE_PAY");
		/* (可选) 订单描述 */
		jsonObject.put("body", "付钱");

		alipayRequest.setBizContent(jsonObject.toString());

		String form = "";
		try {
			AlipayClient alipayClient = com.briup.estore.common.AlipayClientFactory.getAlipayClient();

			AlipayTradePagePayResponse alipayResponse = alipayClient.pageExecute(alipayRequest);

			form = alipayResponse.getBody(); // 调用SDK生成表单
		} catch (AlipayApiException e) {
			e.printStackTrace();
			session.setAttribute("ispay", 0);
		}
		resp.setContentType("text/html;charset=" + AlipayClientFactory.charset);
		resp.getWriter().write(form);// 直接将完整的表单html输出到页面
		resp.getWriter().flush();
		resp.getWriter().close();
	}

}
