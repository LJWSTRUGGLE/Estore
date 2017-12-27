package com.briup.estore.web.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/DrawImg")
public class DrawImg extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 120;
	public static final int HEIGHT = 30;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String createTypeFlag = req.getParameter("createTypeFlag");
		BufferedImage bi = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = bi.getGraphics();
		setBackGround(g);
		setBorder(g);
		drawRandomLine(g);
		String random = drawRandomNum((Graphics2D) g, createTypeFlag);
		req.getSession().setAttribute("checkcode", random);
		resp.setContentType("image/jpeg");
		resp.setDateHeader("expries", -1);
		resp.setHeader("Cache-Control", "no-cache");
		resp.setHeader("Pragma", "no-cache");
		ImageIO.write(bi, "jpg", resp.getOutputStream());
	}

	private void setBackGround(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, WIDTH, HEIGHT);
	}

	private void setBorder(Graphics g) {
		g.setColor(Color.blue);
		g.drawRect(1, 1, WIDTH - 2, HEIGHT - 2);
	}

	private void drawRandomLine(Graphics g) {
		g.setColor(Color.green);
		for (int i = 0; i < 5; i++) {
			int x1 = new Random().nextInt(WIDTH);
			int y1 = new Random().nextInt(HEIGHT);
			int x2 = new Random().nextInt(WIDTH);
			int y2 = new Random().nextInt(HEIGHT);
			g.drawLine(x1, y1, x2, y2);
		}
	}

	private String drawRandomNum(Graphics2D g, String... createTypeFlag) {
        g.setColor(Color.red);
        g.setFont(new Font("宋体", Font.BOLD, 15));
        String baseChineseChar = "\u7684\u4e00\u4e86\u662f\u6211\u4e0d\u5728\u4eba\u4eec\u6709\u6765\u4ed6\u8fd9" +
        		"\u4e0a\u7740\u4e2a\u5730\u5230\u5927\u91cc\u8bf4\u5c31\u53bb\u5b50\u5f97\u4e5f\u548c" +
        		"\u90a3\u8981\u4e0b\u770b\u5929\u65f6\u8fc7\u51fa\u5c0f\u4e48\u8d77\u4f60\u90fd\u628a" +
        		"\u597d\u8fd8\u591a\u6ca1\u4e3a\u53c8\u53ef\u5bb6\u5b66" +
        		"\u53ea\u4ee5\u4e3b\u4f1a\u6837\u5e74\u60f3\u751f" +
        		"\u540c\u8001\u4e2d\u5341\u4ece\u81ea\u9762\u524d" +
        		"\u5934\u9053\u5b83\u540e\u7136\u8d70";
        String baseNummberLetter = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String baseNumber = "0123456789";
        String baseLetter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        if (createTypeFlag.length > 0 && null != createTypeFlag[0]) {
            if (createTypeFlag[0].equals("ch")) {
                // 截取汉字
                return createRandomChar(g, baseChineseChar);
            }else if (createTypeFlag[0].equals("nl")) {
                // 截取数字和字母的组合
                return createRandomChar(g, baseNummberLetter);
            }else if (createTypeFlag[0].equals("n")) {
                // 截取数字
                return createRandomChar(g, baseNumber);
            }else if (createTypeFlag[0].equals("l")) {
                // 截取字母
                return createRandomChar(g, baseLetter);
           }
        }else {
            // 默认截取数字和字母的组合
           return createRandomChar(g, baseNumber);
        }
		return "";
	}

	private String createRandomChar(Graphics2D g, String baseChar) {
		StringBuffer sb = new StringBuffer();
        int x = 5;
        String ch ="";
        // 控制字数
        for (int i = 0; i < 4; i++) {
            // 设置字体旋转角度
            int degree = new Random().nextInt() % 30;
            ch = baseChar.charAt(new Random().nextInt(baseChar.length())) + "";
            sb.append(ch);
            // 正向角度
            g.rotate(degree * Math.PI / 180, x, 20);
           g.drawString(ch, x, 20);
            // 反向角度
            g.rotate(-degree * Math.PI / 180, x, 20);
            x += 30;
        }
		return sb.toString();
	}
}
