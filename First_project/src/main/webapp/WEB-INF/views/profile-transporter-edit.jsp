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
<title>Edit</title>
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
						<a class="nav-link active" href="/transporter">Transporters</a>
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
		<div class="row mt-3">
		<div class="col-3"></div>
			<div class="col-6">
				<form:form action="/profile/edit" method="POST" modelAttribute="transporterCar">
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
						<label class="col-form-label col-2" for="carAge">Car age</label>
						<div class="col-10">
							<form:input path="carAge" id="carAge" class="form-control"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-form-label col-2" for="maxWeight">Max weight</label>
						<div class="col-10">
							<form:input path="maxWeight" id="maxWeight" class="form-control"/>
						</div>
					</div>
					<div class="form-group row">
						<div class="col-10 offset-sm-2">
        					<button type="submit" class="btn btn-dark">Save</button>
      					</div>
					</div>
				</form:form>
			</div>
			<div class="col-3"></div>
		</div>
	</div>