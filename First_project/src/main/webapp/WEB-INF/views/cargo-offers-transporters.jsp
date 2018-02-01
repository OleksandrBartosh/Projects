<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form"  uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"	integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"	crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"	integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1"	crossorigin="anonymous"></script>
<title>Offers</title>
<style type="text/css">
	.table-striped>tbody>tr:nth-child(n)>th {
		background-color: #343a40 !important;
		color: #ffffff;
		text-align: center;
	}

	.table {
		margin-top: 10px;
		padding-left: 0;
		padding-right: 0;
		background-color: white;
		text-align: center;
	}
	.img{
		width: 100%;
	}
	.top{
		margin-top: 60px;
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
					<li class="nav-item">
						<a class="nav-link" href="/">Home<span class="sr-only">(current)</span></a>
					</li>
					<li class="nav-item">
						<a class="nav-link active" href="/cargo">Cargos</a>
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
					<th class="text-center">Creation date</th>
				</tr>
				<tr>
					<td class="text-center">${cargo.goods}</td>
					<td class="text-center">${cargo.weight}</td>
					<td class="text-center">${cargo.height}</td>
					<td class="text-center">${cargo.width}</td>
					<td class="text-center">${cargo.length}</td>
					<td class="text-center">${cargo.cityFrom}</td>
					<td class="text-center">${cargo.cityTo}</td>
					<td class="text-center">${cargo.price}</td>
					<td class="text-center">${cargo.creationDate}</td>
				</tr>
			</table>
			<table class="table table-bordered table-striped">
				<tr>
					<th>Rate</th>
					<th>Count</th>
					<th>Name</th>
					<th>Brand</th>
					<th>Model</th>
					<th>Max weight</th>
					<th>Option</th>
				</tr>
				<c:forEach var="transporters" items="${transporters}">
				<tr>
					<td>${transporters.rate}</td>
					<td>${transporters.count}</td>
					<td>${transporters.name}</td>
					<td>${transporters.brand}</td>
					<td>${transporters.model}</td>
					<td>${transporters.maxWeight}</td>
					<td>
						<a href="${cargo.id}/accept/${transporters.id}" class="btn btn-success btn-sm">Accept</a>
						<a href="/mycargo/decline/${transporters.id}" class="btn btn-danger btn-sm">Decline</a>
					 </td>
				</tr>
				</c:forEach>
				<c:forEach var="transporter" items="${transporter}">
					<tr>
						<td>${transporter.rate}</td>
						<td>${transporter.count}</td>
						<td>${transporter.name}</td>
						<td>${transporter.brand}</td>
						<td>${transporter.model}</td>
						<td>${transporter.maxWeight}</td>
						<td>
							<a href="/transporter/${transporter.id}" class="btn btn-primary btn-sm">Info</a>
							<a href="${cargo.id}/confirm/${transporter.id}" class="btn btn-success btn-sm">Confirm</a>
						 </td>
					</tr>
				</c:forEach>
				<c:forEach var="transp" items="${transp}">
					<tr>
						<td>${transp.rate}</td>
						<td>${transp.count}</td>
						<td>${transp.name}</td>
						<td>${transp.brand}</td>
						<td>${transp.model}</td>
						<td>${transp.maxWeight}</td>
						<td>In the way </td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>