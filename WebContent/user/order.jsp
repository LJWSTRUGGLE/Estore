<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<%@include file="/header.jsp"%>
<script type="text/javascript">
	var msg = '${msg}';
	if (msg) {
		alert(msg);
	}
</script>
<c:remove var="msg" scope="session" />


<!--文件体开始-->

<table cellspacing=1 cellpadding=3 align=center class=tableBorder2>
	<tr>
		<td height=25 valign=middle><img src="images/Forum_nav.gif"
			align="middle"> <a href=index.jsp>杰普电子商务门户</a> → <img
			border="0" src="images/dog.gif" width="19" height="18"> 订单列表</td>
	</tr>
</table>
<br>

<table cellpadding=3 cellspacing=1 align=center class=tableborder1>
	<tr>
		<td valign=middle align=center height=25 background="images/bg2.gif"
			width=""><font color="#ffffff"><b>序号</b></font></td>
		<td valign=middle align=center height=25 background="images/bg2.gif"
			width=""><font color="#ffffff"><b>订单编号</b></font></td>
		<td valign=middle align=center height=25 background="images/bg2.gif"
			width=""><font color="#ffffff"><b>订单金额</b></font></td>
		<td valign=middle align=center height=25 background="images/bg2.gif"
			width=""><font color="#ffffff"><b>订单状态</b></font></td>
		<td valign=middle align=center height=25 background="images/bg2.gif"
			width=""><font color="#ffffff"><b>付款方式</b></font></td>
		<td valign=middle align=center height=25 background="images/bg2.gif"
			width=""><font color="#ffffff"><b>操作</b></font></td>
	</tr>
	<c:forEach items="${orders}" var="order" varStatus="s">

		<tr>
			<td class=tablebody2 valign=middle align=center width="">${s.index
				+ 1 }</td>
			<td class=tablebody1 valign=middle width="">&nbsp;&nbsp;<a
				href="user/orderinfo.info?orderid=1">${order.id}</a></td>
			<td class=tablebody2 valign=middle align=left width="">&nbsp;&nbsp;￥${order.cost
				}</td>
			   <c:if test="${order.status==0 }">
				<td class=tablebody1 valign=middle align=center width="">待付款</td>
			   </c:if>
			
			<c:if test="${order.status==1 }">
				<td class=tablebody1 valign=middle align=center width="">已付款</td>
			</c:if>
			<td class=tablebody2 valign=middle align=left width="">&nbsp;&nbsp;支付宝</td>
			<td class=tablebody1 valign=middle align=center width=""><input
				type="button" value="删除"
				onclick="javascript:window.location='user/removeOrder?id=${order.id}';">&nbsp;
				<input type="button" value="明细"
				onclick="javascript:window.location='user/orderfindbyid?id=${order.id}';">
				<c:if test="${order.status==0 }">
					<input type="button" value="去付款"
						onclick="javascript:window.location='user/payOrder?id=${order.id}';" />
				</c:if>
			</td>
		</tr>
	</c:forEach>
</table>
<br>

<!--文件尾开始-->
<%@include file="/footer.jsp"%>
</body>
</html>