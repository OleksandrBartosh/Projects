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
<title>${owner.name} ${owner.surname}</title>
<style type="text/css">
	
	.img{
			width: 75%;
		}
	.ownerdata{
			padding-left: 100px;
		}
	.top{
		margin-top: 70px;
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
						<a class="nav-link active" href="/owner">Owners</a>
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
			<div class="col-5">
				<div>
					<img class="img" alt="owner image" src="/images/${owner.photoUrl}?version=${owner.version}">
				</div>
			</div>
			<div class="col-7 ownerdata">
				<ul>
					<li>Rate: ${owner.rate}</li>
					<li>Name: ${owner.name}</li>
					<li>Surname: ${owner.surname}</li>
					<li>Phone: ${owner.phone}</li>
					<li>Count: ${owner.count}</li>
					<li>Address: ${owner.address}</li>
					<li>Registration Date: ${owner.registrationDate}</li>
				</ul>
				<div class="row text-left ownerdata">
					<div class="col">
						<a href="/mycargo" class="btn btn-dark btn-lg">My cargo</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>