<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	
		<%@include file="header.jsp" %>
		 <script type="text/javascript">
		 
		 function changeImg(){
	          document.getElementById("validateCodeImg").src="${pageContext.request.contextPath}/DrawImg?"+Math.random();
	     }
      var msg='${msg}';

      if(msg){
          alert(msg);
      }
      
     </script>
<c:remove var="msg" scope="session"/>
		


<!--文件体开始-->

		<table cellspacing=1 cellpadding=3 align=center class=tableBorder2>
		<tr>
		<td height=25 valign=middle>
                  <img src="images/Forum_nav.gif" align="absmiddle">
                  <a href=index.jsp>杰普电子商务门户</a> → 用户登录
                </td>
                </tr>
		</table>
              <br>

	<form action="loginS" method=post name="login">
		<table cellpadding=3 cellspacing=1 align=center class=tableborder1>
		<tr>
			<td valign=middle colspan=2 align=center height=25 background="images/bg2.gif" ><font color="#ffffff"><b>输入您的用户名、密码登录</b></font></td>
		</tr>
		<tr>
		<td valign=middle class=tablebody1 align="right">请输入您的用户名:</td>
			<td valign=middle class=tablebody1><INPUT name=userid type=text> &nbsp;
				<a href="register.jsp">没有注册？</a>
			</td>
		</tr>
		<tr>
			<td valign=middle class=tablebody1 align="right">请输入您的密码:</td>
			<td valign=middle class=tablebody1><INPUT name=password type=password> &nbsp; </td>
		</tr>
		<tr>
			<td valign=middle class=tablebody1 align="right">验证码:</td>
			<td valign=middle class=tablebody1>
			<input type="text" name="validateCode"/>
             <img alt="" src="${pageContext.request.contextPath}/DrawImg" 
             id="validateCodeImg" onclick="changeImg()">
             <a href="javascript:void(0)" onclick="changeImg()">看不清，换一张</a></td>
		</tr>
		<tr>
			<td class=tablebody2 valign=middle colspan=2 align=center><input type=button value="登 录" onclick="javascript:checkMe()"></td>
		</tr>
		</table>
	</form>
<!--文件尾开始-->
		<%@include file="footer.jsp" %>
</html>
