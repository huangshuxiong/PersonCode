<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="cn.hsx.bean.*"%>
<%@ taglib uri="http://www.hsx.cn.com/jsp/hsx/tag/core" prefix="hsx"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<title>Customer Home</title>
</head>
<body>
	<div class="container">
		<div class="jumbotron text-center">
			<h1>Customer System</h1>
		</div>
		<div class="row">
		<div class="col-lg-12 col-md-8 col-sm-4 col-xs-2"></div>
			<form action="query.do" method="post">
				<div class="form-group has-success">
					<label for="id">ID:</label>
					<div class="col-md-12">
						<input type="text" name="id" class="form-control" id="id" />
					</div>
				</div>
				<div class="form-group">
					<label for="id">NAME:</label>
					<div class="col-md-12">
						<input type="text" name="name" class="form-control" />
					</div>
				</div>
				<div class="form-group">
					<label for="id">ADDRESS:</label>
					<div class="col-md-12">
						<input type="text" name="address" class="form-control" />
					</div>
				</div>
				<div class="form-group">
					<label for="id">PHONE:</label>
					<div class="col-md-12">
						<input type="text" name="phone" class="form-control" />
					</div>
				</div>
				<div class="form-group">
					<label for="id">CREATE_YMD:</label>
					<div class="col-md-12">
						<input type="text" name="createymd" class="form-control" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-offset-4 col-md-4">
						<input type="submit" class="form-control" />
					</div>
					<div class="col-md-2">
						<a href="addCustomer.jsp">Add New Customer...</a>
					</div>
				</div>
			</form>
		</div>
		<hr>
		<div class="row">
			<hsx:if test="${requestScope.customers }">
				<table class="table table-hover table-responsive">
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>ADDRESS</th>
						<th>PHONE</th>
						<th>CREATE_YMD</th>
						<th>Delete</th>
						<th>Update</th>
					</tr>
					<hsx:forEach var="c" item="${requestScope.customers }">
						<tr>
							<td><input type="checkbox" name="ck" />&nbsp;${c.getId()}</td>
							<td>${c.getName()}</td>
							<td>${c.getAddress()}</td>
							<td>${c.getPhone()}</td>
							<td>${c.getCre_YMD()}</td>
							<td><a href="delete.do?id=${c.getId()}">Delete</a></td>
							<td><a href="update.do?id=${c.getId()}">Update</a></td>
						</tr>
					</hsx:forEach>
				</table>
			</hsx:if>
		</div>
	</div>
</body>
</html>