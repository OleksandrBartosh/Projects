<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
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
	<div class="col-1"></div>
		<div class="col-10">
			<form:form action="/registration/transporter" method="POST" modelAttribute="transporter" enctype="multipart/form-data">
				<div class="form-group row">
					<label class="col-form-label col-2" for="email">Email:</label>
					<div class="col-10">
						<form:input path="email" id="email" class="form-control"/>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-form-label col-2" for="password">Password:</label>
					<div class="col-10">
						<form:password path="password" id="password" class="form-control"/>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-form-label col-2" for="repeatpassword">Repeat password:</label>
					<div class="col-10">
						<form:password path="repeatPassword" id="repeatpassword" class="form-control"/>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-form-label col-2" for="name">Name:</label>
					<div class="col-10">
						<form:input path="name" id="name" class="form-control"/>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-form-label col-2" for="surname">Surname:</label>
					<div class="col-10">
						<form:input path="surname" id="surname" class="form-control"/>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-form-label col-2" for="age">Age:</label>
					<div class="col-10">
						<form:input path="age" id="age" class="form-control"/>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-form-label col-2" for="phone">Phone:</label>
					<div class="col-10">
						<form:input path="phone" id="phone" class="form-control"/>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-form-label col-2" for="brand">Brand:</label>
					<div class="col-10">
						<form:select path="brand" id="brand" class="form-control" items="${brands}"/>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-form-label col-2" for="model">Model:</label>
					<div class="col-10">
						<form:select path="model" id="model" class="form-control" items="${models}"/>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-form-label col-2" for="maxWeight">Max weight:</label>
					<div class="col-10">
						<form:input path="maxWeight" id="maxWeight" class="form-control"/>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-form-label col-2" for="carAge">Car age:</label>
					<div class="col-10">
						<form:input path="carAge" id="carAge" class="form-control"/>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-form-label col-2" for="cityTo">City:</label>
					<div class="col-10">
						<form:select path="cityTo" id="cityTo" class="form-control" items="${cities}"/>
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
						<button class="btn btn-outline-success btn-sm">Register</button>
						<a href="/registration/owner" class="btn btn-outline-primary btn-sm">Owner registration</a>
					</div>
				</div>
			</form:form>
		</div>
		<div class="col-1"></div>
	</div>
</div>
</body>
</html>