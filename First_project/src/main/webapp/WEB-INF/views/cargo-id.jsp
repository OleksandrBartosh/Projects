<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
	integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"
	integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"
	integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1"
	crossorigin="anonymous"></script>
<title>Cargo</title>
<style type="text/css">
.table-striped>tbody>tr:nth-child(n)>th {
	background-color: #343a40 !important;
	color: #ffffff;
}

.table {
	margin-top: 10px;
	padding-left: 0;
	padding-right: 0;
	background-color: white;
}

.img {
	max-width: 100%;
	height: auto;
	background-color: grey;
	margin: auto;
	padding: 0;
	display: block;
	border: 2px #888888 solid;
}

.img-back {
	box-shadow: 10px 10px 5px #888888;
}

ul {
	list-style-type: none;
}

.owner {
	margin: auto;
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
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarNavButton">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="navbar-collapse collapse justify-content-stretch"
				id="navbarNav">
				<ul class="nav navbar-nav">
					<li class="nav-item"><a class="nav-link" href="/">Home<span
							class="sr-only">(current)</span></a></li>
					<li class="nav-item"><a class="nav-link active" href="/cargo">Cargos</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="/owner">Owners</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="/transporter">Transporters</a>
					</li>
				</ul>
				<sec:authorize access="isAnonymous()">
					<ul class="navbar-nav ml-auto">
						<li class="nav-item active"><a class="nav-link" href="/login">Login</a></li>
						<li class="nav-item active"><a class="nav-link"
							href="/registration">Sign Up</a></li>
					</ul>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<ul class="navbar-nav ml-auto">
						<li class="nav-item active"><a class="nav-link"
							href="/profile">Profile</a></li>
						<li><form:form action="/logout" class="navbar-nav ml-auto">
								<button class="btn btn-light">Logout</button>
							</form:form></li>
					</ul>
				</sec:authorize>
			</div>
		</div>
	</nav>
	<div class="container">
		<div class="row top">
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
						<th class="text-center">Creation date</th>
						<th class="text-center"></th>
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
						<sec:authorize access="hasRole('ROLE_TRANSPORTER')">
							<td class="text-center"><a href="/cargo/${cargo.id}/get"
								class="btn btn-dark btn-sm">Get</a></td>
						</sec:authorize>
					</tr>
				</table>
			</div>
		</div>
		<div class="row owner">
			<div class="col-4">
				<div class="img-back">
					<img class="img" alt="owner image" src="/images/${owner.photoUrl}?version=${owner.version}">
				</div>
			</div>
			<div class="col-8 text-left ownerdata">
				<ul>
					<li>Rate: ${owner.rate}</li>
					<li>Name: ${owner.name}</li>
					<li>Surname: ${owner.surname}</li>
					<li>Phone: ${owner.phone}</li>
					<li>Count: ${owner.count}</li>
					<li>Address: ${owner.address}</li>
					<li>Registration Date: ${owner.registrationDate}</li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>