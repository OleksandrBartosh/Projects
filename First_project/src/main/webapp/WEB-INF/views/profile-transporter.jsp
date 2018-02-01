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
<title>${transporters.name}${transporters.surname}</title>
<style>
	.text-comment{
		padding: 10px 5px 10px 5px;
		border: 1px solid black;
	}
	.comment{
		margin-top: 15px;
		margin-bottom: 15px;
	}
	.img{
		width: 75%;
	}
	.top{
		margin-top: 80px;
	}
	ul {
	list-style-type: none;
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
					<sec:authorize access="hasRole('ROLE_ADMIN')">
						<li class="nav-item"><a class="nav-link" href="/admin">Admin</a>
						</li>
					</sec:authorize>
					<li class="nav-item"><a class="nav-link" href="/cargo">Cargos</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="/owner">Owners</a>
					</li>
					<li class="nav-item"><a class="nav-link active"
						href="/transporter">Transporters</a></li>
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
			<div class="col-6">
				<h2>Current status</h2>
				<ul>
					<li>Status:  ${transporter.status}</li> 
					<li>Registration date: ${transporter.registrationDate}</li> 
					<li>Name: ${transporter.name}</li> 
					<li>Surname: ${transporter.surname}</li> 
					<li>Age: ${transporter.age}</li> 
					<li>Phone: ${transporter.phone}</li>
					<li class="col pt-2">
						<div><a href="/myoffers" class="btn btn-dark btn-lg">My offers</a></div>
					</li>
				</ul>
			</div>
			<div class="col-6 pl-5">
			<img class="img" alt="transporter image" src="/images/${transporter.photoUrl}?version=${transporters.version}">
			</div>
		</div>
		<div class="row mt-4">
			<div class="col-6">
				<h2>Edit status</h2>
				<form:form action="/profile" method="POST" modelAttribute="transporter">
					<div class="form-group row">
						<label class="col-form-label col-2" for="dateArrive">Date
							arrive</label>
						<div class="col-10">
							<form:input path="dateArrive" id="dateArrive"
								class="form-control" />
						</div>
					</div>
					<div class="form-group row">
						<label class="col-form-label col-2" for="status">Status:</label>
						<div class="col-10">
							<form:select path="status" id="status" class="form-control"
								items="${statuses}" />
						</div>
					</div>
					<div class="form-group row">
						<label class="col-form-label col-2" for="cityArrive">City:</label>
						<div class="col-10">
							<form:select path="cityArrive" id="cityArrive"
								class="form-control" items="${cities}" />
						</div>
					</div>
					<div class="form-group row">
						<div class="col-10 offset-sm-2">
							<button type="submit" class="btn btn-success">Save</button>
						</div>
					</div>
				</form:form>
			</div>
			<div class="col-6">
			<div class="pl-4"><h2>Car information</h2></div>
				<ul>
					<li>Brand: ${transporter.brand}</li> 
					<li>Model: ${transporter.model}</li> 
					<li>Max weight: ${transporter.maxWeight}</li> 
					<li>Car age: ${transporter.carAge}</li> 
					<li><a href="/profile/edit" class="btn btn-warning btn-lg">Edit</a></li>
				</ul>
			</div>
		</div>
		<div class="row mt-2">
			<div class="col-6 text-left">
				<h2>Current cargo</h2>
				<div>Goods: ${cargo.goods}</div>
				<div>Weight: ${cargo.weight}</div>
				<div>Height: ${cargo.height}</div>
				<div>Width: ${cargo.width}</div>
				<div>Length: ${cargo.length}</div>
				<div>City from: ${cargo.cityFrom}</div>
				<div>City to: ${cargo.cityTo}</div>
				<div>Price: ${cargo.price}</div>
				<div>Creation date: ${cargo.creationDate}</div>
			</div>
		</div>
		<div class="row">
		<div class="col-3"></div>
			<div class="col-6">
				<c:forEach var="comment" items="${comments}">
					<div class="row">
						<div class="col comment">
							<div class="text-left">${comment.name} ${comment.surname}</div>
							<div class="text-left">${comment.date}</div>
							<div class="text-comment">${comment.comment}</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<div class="col-3"></div>
		</div>
	</div>
</body>
</html>