<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
<title>Registration</title>
<style type="text/css">
	.top{
		margin-top: 80px;
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
				    		<li class="nav-item"><a class="nav-link" href="/login">Login</a></li>
							<li class="nav-item active"><a class="nav-link" href="/registration">Sign Up</a></li>
						</ul>
					</sec:authorize>
			</div>
	</div>
</nav>
	<div class="container">
		<div class="row top">
			<div class="col-2"></div>
			<div class="col-8">
				<form:form action="/registration/owner" method="POST" modelAttribute="owner" enctype="multipart/form-data">
					<div class="form-group row">
						<label class="col-form-label col-2" for="email">Email:</label>
						<div class="col-10">
							<form:input path="email" id="email" class="form-control" />
						</div>
					</div>
					<div class="form-group row">
						<label class="col-form-label col-2" for="password">Password:</label>
						<div class="col-10">
							<form:password path="password" id="password" class="form-control" />
						</div>
					</div>
					<div class="form-group row">
						<label class="col-form-label col-2" for="repeatpassword">Repeat
							password:</label>
						<div class="col-10">
							<form:password path="repeatPassword" id="repeatpassword"
								class="form-control" />
						</div>
					</div>
					<div class="form-group row">
						<label class="col-form-label col-2" for="surname">Name:</label>
						<div class="col-10">
							<form:input path="name" id="name" class="form-control"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-form-label col-2" for="name">Surname:</label>
						<div class="col-10">
							<form:input path="surname" id="surname" class="form-control"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-form-label col-2" for="phone">Phone:</label>
						<div class="col-10">
							<form:input path="phone" id="phone" class="form-control" />
						</div>
					</div>
					<div class="form-group row">
						<label class="col-form-label col-2" for="address">Address:</label>
						<div class="col-10">
							<form:input path="address" id="address" class="form-control" />
						</div>
					</div>
					<div class="form-group row">
					<label for="file" class="control-label col-sm-2">Select image:</label>
					<div class="col-sm-10">
						<input name="file" id="file" type="file">
					</div>
				</div>
					<div class="form-group row">
						<div class="col-10 ml-auto">
							<button class="btn btn-outline-success">Register</button>
							<a href="/registration/transporter"
								class="btn btn-outline-primary">Transporter
								registration</a>
						</div>
					</div>
				</form:form>
			</div>
			<div class="col-2"></div>
		</div>
	</div>
</body>
</html>