<%@page import="cn.hsx.bean.Customer"%>
<%@page import="java.util.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@taglib uri="http://www.hsx.cn.com/jsp/hsx/tag/core" prefix="hsx"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		Customer c1 = new Customer("001", "user001", "tokyo", "0802122", "20090808");
		Customer c2 = new Customer("001", "user001", "tokyo", "0802122", "20090808");
		Customer c3 = new Customer("001", "user001", "tokyo", "0802122", "20090808");
		Customer c4 = new Customer("001", "user001", "tokyo", "0802122", "20090808");
		List<Customer> datas = Arrays.asList(c1, c2, c3, c4);
		request.setAttribute("datas", datas);
	%>

	<%-- 	<hsx:forEach var="a" item="${datas}">
		${a.id } -- ${a.name }-- ${a.address }--${a.phone }<br>
	</hsx:forEach>

	<hsx:choose>
		<hsx:when test="${param.age>24}">param.age>24<br>
		</hsx:when>
		<hsx:when test="${param.age>20}">param.age>20<br>
		</hsx:when>
		<hsx:otherwise>param.age</hsx:otherwise>
	</hsx:choose>
 --%>
	<hsx:if test="${param.age>0 }">
 	age>0
 </hsx:if>

</body>
</html>