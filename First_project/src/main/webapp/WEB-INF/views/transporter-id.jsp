<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="custom" uri="/WEB-INF/tags/implicit.tld" %>
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
<title>${transporters.name} ${transporters.surname}</title>
<style>
	.text-comment{
		padding: 10px 5px 10px 5px;
	}
	.comment{
		margin-top: 15px;
		margin-bottom: 15px;
	}
	.img{
		width: 100%;
	}
	.top{
		margin-top: 70px;
	}
	.comment-header{
		margin-top: 20px;
		background-color: #343a40;
		border-bottom: 5px red solid;
		height: 40px;
		text-align: center;
		text-decoration: solid;
	}
	h4{
		padding-top: 5px;
		color: white;
	}
	h5{
		color: #343a40;
	}
	.text-comment{
		padding: 10px 5px 10px 5px;
	}
	.comment{
		margin-top: 15px;
		margin-bottom: 15px;
		border-top: 3px red solid;
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
			<div class="col-4">
				<div><img class="img" alt="transporter image" src="/images/${transporters.photoUrl}?version=${transporters.version}"></div>
			</div>
			<div class="col-4">
				<div>Rate: ${transporters.rate}</div>
				<div>Count: ${transporters.count}</div>
				<div>Status: ${transporters.status}</div>
				<div>City arrive: ${transporters.cityArrive}</div>
				<div>Date arrive: ${transporters.dateArrive}</div>
			</div>
			<div class="col-4">
				<div>Name: ${transporters.name}</div>
				<div>Surname: ${transporters.surname}</div>
				<div>Age: ${transporters.age}</div>
				<div>Phone: ${transporters.phone}</div>
				<div>Registration Date: ${transporters.registrationDate}</div><br>
				<div>Brand: ${transporters.brand}</div>
				<div>Model: ${transporters.model}</div>
				<div>Max weight: ${transporters.maxWeight}</div>
				<div>Car age: ${transporters.carAge}</div>
			</div>
		</div>
		<!-- <sec:authorize access="hasRole('ROLE_OWNER')">
		<div class="row mt-3">
			<form:form modelAttribute="cargo" action="/transporter/${id}/offer" method="POST">
				<div class="form-group row">
					<label class="col-form-label col-2" for="cargo">Choose cargo:</label>
					<div class="col-8">
						<form:select path="cargo" id="cargo" class="form-control" items="${cargos}"/>
					</div>
					<div class="col-2">
						<button class="btn btn-dark" type="submit">Offer</button>
					</div>
				</div>
			</form:form>
		</div>
		</sec:authorize> -->
		<sec:authorize access="hasRole('ROLE_OWNER')">
		<div class="row justify-content-center">
				<form:form modelAttribute="comment" action="/transporter/${id}" method="POST">
					<div class="comment-header"><h4>COMMENTS</h4></div>
					<div class="form-group row mt-4">
						<div class="col-4">
							<div class="row">
							<div class="col ml-auto" style="color:red;">
								<form:errors path="rate"/>
							</div>
						</div>
							<label for="rate" >Rate</label>
							<form:select path="rate" id="rate" class="form-control ">
								<form:option value="1">1</form:option>
								<form:option value="2">2</form:option>
								<form:option value="3">3</form:option>
								<form:option value="4">4</form:option>
								<form:option value="5">5</form:option>
							</form:select>
						</div>
						<div class="col-8"></div>
				   </div>
					<div class="row ">
						<div class="col text-center mb-4">
							<form:textarea path="comment" class="form-control"  cols="100" rows="5" placeholder="Add a comment"/>
						</div>
					</div>
					<div class="row">
						<div class="col ml-auto">
							<button class="btn btn-dark" type="submit">Send</button>
						</div>
					</div>
				</form:form>
		</div>
		</sec:authorize>
		<c:forEach var="comment" items="${comments.content}">
			<div class="row">
				<div class="col-3"></div>
				<div class="col-6 comment pt-4">
					<div class="row">
						<div class=" col-6 text-left pb-2"><h5>${comment.name} ${comment.surname}</h5></div>
						<div class=" col-6 text-right">${comment.rate}</div>
					</div>
					<div class="text-left">${comment.date}</div>
					<div class="text-comment">${comment.comment}</div>
				</div>
				<div class="col-3"></div>
			</div>
			</c:forEach>
	</div>
</body>
</html>