<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="custom" uri="/WEB-INF/tags/implicit.tld" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value="/resources/css/brand.css" />" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"	integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"	crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"	integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1"	crossorigin="anonymous"></script>
<title>Brand</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
	<div class="container-fluid">
		<a class="navbar-brand" href="/">MySite</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavButton">
			<span class="navbar-toggler-icon"></span>
		</button>
			<div class="navbar-collapse collapse justify-content-stretch" id="navbarNav">
				<ul class="nav navbar-nav">
					<li class="nav-item">
						<a class="nav-link" href="/">Home<span class="sr-only">(current)</span></a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/admin">Admin</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/cargo">Cargos</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/owner">Owners</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/transporter">Transporters</a>
					</li>
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle active" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Management</a>
						<div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownMenuLink">
							<a class="dropdown-item  bg-dark active" href="/admin/brand">Brand</a>
							<a class="dropdown-item" href="/admin/city">City</a>
							<a class="dropdown-item" href="/admin/goods">Goods</a>
							<a class="dropdown-item" href="/admin/model">Model</a>
						</div>
				   </li>
				</ul>
			    <form:form action="/logout" class="navbar-nav ml-auto">
					<button class="btn btn-light">Logout</button>
				</form:form>  
			</div>
	</div>
</nav>
	<div class="container">
		<div class="row top">
			<div class="col">
				<form:form action="/admin/brand" method="POST" modelAttribute="brand">
				<custom:hiddenInputs excludeParams="name"/>
					<label class="col-auto col-form-label">Name:</label>
					<div class="row">
						<div class="col-10 ml-auto" style="color:red;">
							<form:errors path="name"/>
						</div>
				</div>
					<div class="form-group row">
						<form:input path="name" class="form-control" placeholder="Add new brand"/>
							<button class="btn btn-success" type="submit">Save</button>
							<a href="/admin/brand/cancel<custom:allParams/>" class="btn btn-warning" type="submit">Cancel</a>
					</div>
				</form:form>
			</div>
		</div>
		<div class="row">
			<div class="col-10">
				<form:form modelAttribute="filter" action="/admin/brand" method="GET">
					<div class="form-group row">
						<div class="col-12">
							<form:input path="search" class="form-control" placeholder="Search"/>
						</div>
					</div>
				</form:form>
			</div>
			<div class="col-2">
				<div class="row">
					<div class="col-6 text-center">
							<button class="dropdown-toggle btn btn-outline-dark dropdown-toggle" type="button" data-toggle="dropdown">Sort <span class="caret"></span>
							</button>
							<div class="dropdown-menu">
								<custom:sort innerHtml="Name asc" paramValue="name"/>
								<custom:sort innerHtml="Name desc" paramValue="name,desc"/>
							</div>
					</div>
					<div class="col-6 text-center size">
						<custom:size posibleSizes="1,2,5,10" size="${brands.size}" />
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col padding-0">
				<table class="table table-bordered table-striped">
					<tr>
						<th class="text-center">Name</th>
						<th class="text-center">Options</th>
					</tr>
					<c:forEach var="brand" items="${brands.content}">
						<tr>
							<td>${brand.name}</td>
							<td class="text-center">
								<a href="/admin/brand/update/${brand.id}<custom:allParams/>" class="btn btn-warning btn-sm">Update</a>
								<a href="/admin/brand/delete/${brand.id}<custom:allParams/>" class="btn btn-danger btn-sm">Delete</a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<custom:pageable page="${brands}"/>
			</div>
		</div>
	</div>
</body>
</html>