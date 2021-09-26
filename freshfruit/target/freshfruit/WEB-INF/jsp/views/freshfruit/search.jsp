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
						<p>Kết quả tìm kiếm với từ : </p>
						<h3 style="color: white">${search}</h3>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- end breadcrumb section -->
	<c:if test="${not empty resultProduct}">
	<c:choose>
		<c:when test="${not empty resultProduct}">
			<div class="product-section mt-150 mb-150">
				<div class="container">
					<div class="row product-lists">
							<c:forEach items="${resultProduct}" var="product">
						<div class="col-lg-4 col-md-6 text-center strawberry">
							<div class="single-product-item">
								<div class="product-image">
									<a href="${pageContext.request.contextPath}/product/${product.menu_id}/${product.product_id}"><img src="${contextPath}/img/products/product-img-1.jpg" alt=""></a>
								</div>
								<h3>${product.product_name}</h3>
								<p class="product-price"><span>Per Kg</span> ${product.product_price}$ </p>
								<a href="${pageContext.request.contextPath}/product/${product.menu_id}" class="cart-btn"><i class="fas fa-shopping-cart"></i> Add to Cart</a>
							</div>
						</div>
							</c:forEach>
					</div>
				</div>
				</div>
			</c:when>
			<c:otherwise>
				<h4>Chưa có sản phẩm nào</h4>
			</c:otherwise>
			</c:choose>
			</c:if>
		
		<c:if test="${not empty resultDetail}">
		<c:choose>
		<c:when test="${not empty resultDetail}">
		<div class="latest-news mt-150 mb-150">
		<div class="container">
			<div class="row">
					<c:forEach items="${resultDetail}" var="l">
						<div class="col-lg-4 col-md-6">
							<div class="single-latest-news">
								<a href="${pageContext.request.contextPath}/news/detail/${l.news_id}/${l.news_detail_id}"><div class="latest-news-bg news-bg-1"></div></a>
								<div class="news-text-box">
									<h3><a href="${pageContext.request.contextPath}/news/detail/${l.news_id}/${l.news_detail_id}">${l.news_detail_name}</a></h3>
									<p class="blog-meta">
										<span class="author"><i class="fas fa-user"></i> Admin</span>
										<span class="date"><i class="fas fa-calendar"></i> 27 December, 2019</span>
									</p>
										<c:set value="${fn:length(l.news_detail_description)}" var="length" />
										<c:set value="${200}" var="lengt" />
										<c:choose>
										<c:when test="${length lt lengt}">
										<p class="excerpt">${l.news_detail_description} ...</p>
										</c:when>
									<c:otherwise>
										<p class="excerpt">${fn:substring(l.news_detail_description, 0, 200)} ...</p>
									</c:otherwise>
									</c:choose>
									<a href="${pageContext.request.contextPath}/news/detail/${l.news_id}/${l.news_detail_id}" class="read-more-btn">Đọc thêm ... <i class="fas fa-angle-right"></i></a>
								</div>
							</div>
						</div>
					</c:forEach>
					</div>
				</div>
			</div>
			</c:when>
			<c:otherwise>
				<h4>Chưa có bài viết nào</h4>
			</c:otherwise>
			</c:choose>
			</c:if>
			
			<c:if test="${totalPage > 1}">
			<div class="row">
						<div class="col-lg-12 text-center">
							<div class="pagination-wrap">
								<ul>
									<c:if test="${currentPage != 1}">
									<li><a href="${pageContext.request.contextPath}/search?search=${search}&query=${query}&page=${currentPage-1}"> << </a></li>
									</c:if>
										<c:forEach begin="1" end="${totalPage}" var="i">
											<c:choose>
												<c:when test="${i == currentPage}">
													<li><a class="active" href="${pageContext.request.contextPath}/search?search=${search}&query=${query}&page=${i}">${i}</a></li>
												</c:when>
												<c:otherwise>
													<li class="<c:if test="${i == currentPage}"></c:if>"><a href="${pageContext.request.contextPath}/search?search=${search}&query=${query}&page=${i}">${i}</a></li>
												</c:otherwise>
											</c:choose>
								      </c:forEach>
								      <c:if test="${currentPage != totalPage}">
									<li><a href="${pageContext.request.contextPath}/search?search=${search}&query=${query}&page=${currentPage+1}"> >> </a></li>
									</c:if>
								</ul>
							</div>
						</div>
					</div>
		</c:if>
	<!-- single article section -->
	
	<!-- end single article section -->

	<!-- logo carousel -->
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