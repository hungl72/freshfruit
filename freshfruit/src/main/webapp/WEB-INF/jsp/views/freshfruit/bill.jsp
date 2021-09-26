<%@page import="project.controllers.service.CommentChildrenService"%>
<%@page import="project.controllers.dao.CommentsChildrenDAO"%>
<%@page import="project.controllers.dao.CommentsDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="project.controllers.model.CommentChildren"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/components/taglib.jsp" %>
<!DOCTYPE html>

<!-- breadcrumb-section -->
	<div class="breadcrumb-section breadcrumb-bg">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 offset-lg-2 text-center">
					<div class="breadcrumb-text">
						<h1>Hóa đơn mua hàng</h1>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- end breadcrumb section -->
<c:if test="${not empty listBill}">
	<c:choose>
		<c:when test="${not empty listBill && fn:length(listBill) gt 0}">
			<div class="product-section mt-150 mb-150">
				<div class="container">
					<div class="row product-lists">
						<table class="table">
						  <thead>
						    <tr>
						      <th scope="col">Mã hóa đơn</th>
						      <th scope="col">Ngày xuất hóa đơn</th>
						      <th scope="col">Chi tiết hóa đơn</th>
						      <th scope="col">Giá gốc</th>
						      <th scope="col">Số tiền cần thanh toán</th>
						      <th scope="col">Địa chỉ</th>
						      <th scope="col">Tên khách hàng</th>
						    </tr>
							</thead>
							  <tbody>
								 <c:forEach items="${listBill}" var="b">
								    <tr>
								      <td>${b.bill_id}</td>
								      <td>${b.bill_date}</td>
								      <td>${b.bill_product}</td>
								      <td>${b.root_price}</td>
								      <td>${b.bill_price}</td>
								      <td>${b.bill_information}</td>
								      <td>${b.name_user}</td>
								    </tr>
								</c:forEach>
							  </tbody>
					</table>
				</div>
			</div>
		</div>
		</c:when>
	</c:choose>
</c:if>
<c:if test="${not empty billEmpty}">
	<h4>${billEmpty}</h4>
</c:if>
	<div class="logo-carousel-section">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="logo-carousel-inner">
						<div class="single-logo-item">
							<img src="${contextPath}/img/company-logos/1.png" alt="">
						</div>
						<div class="single-logo-item">
							<img src="${contextPath}/img/company-logos/2.png" alt="">
						</div>
						<div class="single-logo-item">
							<img src="${contextPath}/img/company-logos/3.png" alt="">
						</div>
						<div class="single-logo-item">
							<img src="${contextPath}/img/company-logos/4.png" alt="">
						</div>
						<div class="single-logo-item">
							<img src="${contextPath}/img/company-logos/5.png" alt="">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- end logo carousel -->