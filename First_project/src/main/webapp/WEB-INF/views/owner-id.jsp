<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>Owners</title>
<style>
	.table-striped>tbody>tr:nth-child(n)>th {
		background-color: #343a40 !important;
		color: #ffffff;
	}
	.table {
		margin-top: 20px;
		padding-left: 0;
		padding-right: 0;
		background-color: white;
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
	.img {
		max-width: 80% ;
		height: auto;
		background-color: grey;
		margin: auto;
		padding: 0;
		display: block;
		border: 2px #888888 solid;
	}
	.ownerdata{
		padding-left: 200px;
	}
	.owner {
		margin: auto;
	}
	ul {
		list-style-type: none;
	}
	.top{
		margin-top: 70px;
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
			<div class="col-4">
				<div>
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
						<th class="text-center">Creation date</th>
						<sec:authorize access="hasRole('ROLE_TRANSPORTER')">
							<th class="text-center">Options</th>
						</sec:authorize>
					</tr>
					<c:forEach var="cargo" items="${cargos.content}">
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
							<td class= "text-center">
								<a href="/cargo/${cargo.id}" class="btn btn-dark btn-sm">Get</a>
							</td>
							</sec:authorize>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<sec:authorize access="hasRole('ROLE_TRANSPORTER')">
		<div class="row mb-4">
			<div class="col">
				<form:form modelAttribute="comment" action="/owner/${id}" method="POST">
					<div class="comment-header"><h4>COMMENTS</h4></div>
					<div class="form-group row mt-4">
						<div class="col-4">
							<label for="rate" >Rate</label>
							<form:select path="rate" id="rate" class="form-control ">
								<form:option value=""></form:option>
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