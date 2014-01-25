<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.jonwelzel.util.Version"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ecommerce <%=Version.VALUE%></title>
</head>
  <link rel="stylesheet" href="resources/css/styles.css" />
  <style type="text/css">
    body {
      padding-top: 60px;
    }
  </style>
</head>
<body>
	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
        	<div class="container">
				<a class="brand" href="${pageContext.request.contextPath}">ecommerce</a>
			</div>
		</div>
	</div>
	<div class="container">
		<form action="greeting" class="well form-inline">
			<div class="clearfix">
				<label for="name">What is your name?</label>
				<div class="input">
					<input type="text" name="name" id="name" class="input-small" /> 
					<input type="submit" class="btn btn-primary btn-medium" name="go" value="go" />
				</div>
			</div>
		</form>
		<c:if test="${not empty requestScope.greeting}">
			<div class="alert alert-success fade in">
				<a class="close" data-dismiss="alert" href="#">×</a>
				<strong>Success</strong>
				<p>${requestScope.greeting}</p>
			</div>
		</c:if>
	</div>
	<!-- Use jQuery to enable bootstrap-alert (adding closing functionality to alert messages)-->
	<!-- Using a CDN to get jQuery, as eclipse doesn't like it very much 
	     and finds some JavaScript validation errors -->
	<script src="http://code.jquery.com/jquery-1.7.2.min.js"></script>
    <script src="resources/js/bootstrap-alert.js"></script>
</body>
</html>