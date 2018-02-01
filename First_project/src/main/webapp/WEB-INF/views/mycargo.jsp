<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"  uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="custom" uri="/WEB-INF/tags/implicit.tld" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"	integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"	crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"	integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1"	crossorigin="anonymous"></script>
<title>My cargo</title>
<style type="text/css">
.page-item.active .page-link {
	    color: #fff;
	    background-color: #343a40;
	    border-color: #343a40;
	}
	.page-link:focus{
	    color: #fff;
	    text-decoration: none;
	    background-color: #343a40;
	    border-color: #ddd;
	}
	.page-link:hover {
	 	color: #fff;
	    text-decoration: none;
	    background-color: #343a40;
	    border-color: #ddd;
	}
	.page-link {
	    position: relative;
	    display: block;
	    padding: .5rem .75rem;
	    margin-left: -1px;
	    line-height: 1.25;
	    color: #343a40;
	    background-color: #fff;
	    border: 1px solid #ddd;
	}
	.dropdown-item.active, .dropdown-item:active {
	    color: #fff;
	    text-decoration: none;
	    background-color: #343a40;
	 }
	 .table-striped > tbody > tr:nth-child(n) > th {
  		 background-color: #343a40!important;
  		 color: #ffffff;
	}
	.table{
		margin-top: 10px;
		padding-left: 0;
		padding-right: 0;
	}
	.top{
		margin-top: 60px;
	}
	.btn{
		background-color: white;
	}
</style>
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
					<li class="nav-item active">
						<a class="nav-link" href="/">Home<span class="sr-only">(current)</span></a>
					</li>
					<sec:authorize access="hasRole('ROLE_ADMIN')">
						<li class="nav-item">
							<a class="nav-link" href="/admin">Admin</a>
						</li>
				   </sec:authorize>
					<li class="nav-item">
						<a class="nav-link" href="/cargo">Cargos</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/owner">Owners</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/transporter">Transporters</a>
					</li>
				</ul>
				    <sec:authorize access="isAnonymous()">
				    	 <ul class="navbar-nav ml-auto">
				    		<li class="nav-item active"><a class="nav-link" href="/login">Login</a></li>
							<li class="nav-item active"><a class="nav-link" href="/registration">Sign Up</a></li>
						</ul>
					</sec:authorize>
					<sec:authorize access="isAuthenticated()">
						 <ul class="navbar-nav ml-auto">
							<li class="nav-item active">
								<a class="nav-link" href="/profile">Profile</a>
							</li>
				    		<li>
					    		<form:form action="/logout" class="navbar-nav ml-auto">
					    			<button class="btn btn-light">Logout</button>
					    		</form:form>
				    		</li>
				    	</ul>
	      			</sec:authorize>
			</div>
	</div>
</nav>
	<div class="container">
	<div class="row top">
			<div class="col-6"></div>
			<div class="col-6 text-right">
				<custom:size posibleSizes="5,10,15,20" size="${cargos.size}" />
			</div>
	</div>
	<div class="row">
			<div class="col-12">
				<table class="table table-bordered table-striped">
					<tr>
						<th class="text-center">Goods</th>
						<th class="text-center">Weight</th>
						<th class="text-center">Height</th>
						<th class="text-center">Width</th>
						<th class="text-center">Length</th>
						<th class="text-center">City from</th>
						<th class="text-center">City to</th>
						<th class="text-center">Price</th>
						<th class="text-center">Options</th>
					</tr>
					<c:forEach var="cargo" items="${cargos.content}">
					<tr>
						<td>${cargo.goods}</td>
						<td>${cargo.weight}</td>
						<td>${cargo.height}</td>
						<td>${cargo.width}</td>
						<td>${cargo.length}</td>
						<td>${cargo.cityFrom}</td>
						<td>${cargo.cityTo}</td>
						<td>${cargo.price}</td>
						<td class="text-center">
							<a href="/mycargo/${cargo.id}" class="btn btn-outline-primary btn-sm">Offers</a>
							<a href="/mycargo/update/${cargo.id}" class="btn btn-outline-warning btn-sm">Update</a>
							<a href="/mycargo/delete/${cargo.id}" class="btn btn-outline-danger btn-sm">Delete</a>
						</td>
					</tr>
					</c:forEach>
				</table>
			</div>
		</div> 
		<div class="row">
			<div class="col-12">
				<custom:pageable page="${cargos}"/>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<form:form action="/mycargo" method="POST" modelAttribute="cargo">
					<div class="form-group row">
						<label class="col-2 col-form-label">Goods:</label>
						<div class="col-10">
							<form:select path="goods" items="${goodss}" class="form-control"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-2 col-form-label">Weight:</label>
						<div class="col-10">
							<form:input path="weight" class="form-control"/> 
						</div>
					</div>
					<div class="form-group row">
						<label class="col-2 col-form-label">Height:</label>
						<div class="col-10">
							<form:input path="height" class="form-control"/> 
						</div>
					</div>
					<div class="form-group row">
						<label class="col-2 col-form-label">Width:</label>
						<div class="col-10">
							<form:input path="width" class="form-control"/> 
						</div>
					</div>
					<div class="form-group row">
						<label class="col-2 col-form-label">Length:</label>
						<div class="col-10">
							<form:input path="length" class="form-control"/> 
						</div>
					</div>
					<div class="form-group row">
						<label class="col-2 col-form-label">City from:</label>
						<div class="col-10">
							<form:select path="cityFrom" items="${cities}" class="form-control"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-2 col-form-label">City to:</label>
						<div class="col-10">
							<form:select path="cityTo" items="${cities}" class="form-control"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-2 col-form-label">Price:</label>
						<div class="col-10">
							<form:input path="price" class="form-control"/> 
						</div>
					</div>
					<div class="form-group row">
						<div class="col-10 offset-sm-2">
        					<button type="submit" class="btn btn-outline-success">Save</button>
        					<a href="/mycargo/cancel" class="btn btn-outline-danger" type="submit">Cancel</a>
      					</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>