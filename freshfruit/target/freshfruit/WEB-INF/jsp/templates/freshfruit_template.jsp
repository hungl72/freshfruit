<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/components/taglib.jsp" %>
<c:url value="/resources/freshfruit" var="contextPath" scope="application" />
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="Responsive Bootstrap4 Shop Template, Created by Imran Hossain from https://imransdesign.com/">

	<!-- title -->
	<title>Fruitkha</title>

	<!-- favicon -->
	<link rel="shortcut icon" type="image/png" href="assets/img/favicon.png">
	<!-- google font -->
	<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Poppins:400,700&display=swap" rel="stylesheet">
	<!-- fontawesome -->
	<link rel="stylesheet" href="${contextPath}/css/all.min.css">
	<!-- bootstrap -->
	<link rel="stylesheet" href="${contextPath}/bootstrap/css/bootstrap.min.css">
	<!-- owl carousel -->
	<link rel="stylesheet" href="${contextPath}/css/owl.carousel.css">
	<!-- magnific popup -->
	<link rel="stylesheet" href="${contextPath}/css/magnific-popup.css">
	<!-- animate css -->
	<link rel="stylesheet" href="${contextPath}/css/animate.css">
	<!-- mean menu css -->
	<link rel="stylesheet" href="${contextPath}/css/meanmenu.min.css">
	<!-- main style -->
	<link rel="stylesheet" href="${contextPath}/css/main.css">
	<!-- responsive -->
	<link rel="stylesheet" href="${contextPath}/css/responsive.css">

	<link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js">
</head>

<body>
<tiles:insertAttribute name="header"></tiles:insertAttribute>
	<tiles:insertAttribute name="content"></tiles:insertAttribute>
<tiles:insertAttribute name="footer"></tiles:insertAttribute>