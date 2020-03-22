<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="cn.hsx.bean.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<title>Add Customer</title>
</head>
<body>

	<font color="red">${error }</font>
	<div class="container">
		<div class="row">
			<div class="col-sm-12">
				<form action="add.do" method="post">
					<input type="hidden" value="${customer.id }" name="oldId" /> <input
						type="hidden" value="${customer.name }" name="oldName" /> <input
						type="hidden" value="${customer.address }" name="oldAddress" /> <input
						type="hidden" value="${customer.phone }" name="oldPhone" />
					<table class="table table-hover">
						<tr>
							<td>ID:</td>
							<td><input type="text" value="${customer.id }" name="id" /></td>
						</tr>
						<tr>
							<td>NAME:</td>
							<td><input type="text" value="${customer.name }" name="name" /></td>
						</tr>
						<tr>
							<td>ADDRESS:</td>
							<td><input type="text" value="${customer.address }"
								name="address" /></td>
						</tr>
						<tr>
							<td>PHONE:</td>
							<td><input type="text" value="${customer.phone }"
								name="phone" /></td>
						</tr>
						<tr>
							<td><input type="submit" value="SUBMIT"></td>
							<td><a href="query.do">Return home...</a></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>