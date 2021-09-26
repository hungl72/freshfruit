<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@include file="/WEB-INF/jsp/components/taglib.jsp" %>
  <c:url value="/resources/img" var="urlUpload"/>
	<div class="homepage-slider">
		<div class="single-homepage-slider homepage-bg-1">
			<div class="container">
				<div class="row">
					<div class="col-md-12 col-lg-7 offset-lg-1 offset-xl-0">
						<div class="hero-text">
							<div class="hero-text-tablecell">
								<p class="subtitle">Fresh & Organic</p>
								<h1>Delicious Seasonal Fruits</h1>
								<div class="hero-btns">
									<a href="shop.html" class="boxed-btn">Fruit Collection</a>
									<a href="contact.html" class="bordered-btn">Contact Us</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="single-homepage-slider homepage-bg-2">
			<div class="container">
				<div class="row">
					<div class="col-lg-10 offset-lg-1 text-center">
						<div class="hero-text">
							<div class="hero-text-tablecell">
								<p class="subtitle">Fresh Everyday</p>
								<h1>100% Organic Collection</h1>
								<div class="hero-btns">
									<a href="shop.html" class="boxed-btn">Visit Shop</a>
									<a href="contact.html" class="bordered-btn">Contact Us</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="single-homepage-slider homepage-bg-3">
			<div class="container">
				<div class="row">
					<div class="col-lg-10 offset-lg-1 text-right">
						<div class="hero-text">
							<div class="hero-text-tablecell">
								<p class="subtitle">Mega Sale Going On!</p>
								<h1>Get December Discount</h1>
								<div class="hero-btns">
									<a href="shop.html" class="boxed-btn">Visit Shop</a>
									<a href="contact.html" class="bordered-btn">Contact Us</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="product-section mt-150 mb-150">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 offset-lg-2 text-center">
					<div class="section-title">	
						<h3><span class="orange-text">Sản phẩm</span> mới nhất</h3>
					</div>
				</div>
			</div>
			<div class="row">
				<c:choose>
					<c:when test="${not empty listProduct}">
						<c:forEach items="${listProduct}" var="p">
							<div class="col-lg-4 col-md-6 text-center">
								<div class="single-product-item">
									<div class="product-image">
										<a href="${pageContext.request.contextPath}/product/${p.menu_id}/${p.product_id}"><img src="${urlUpload}/${p.product_image}" alt=""></a>
									</div>
									<h3>${p.product_name}</h3>
									<p class="product-price"><span>Per Kg</span> ${p.product_price}$ </p>
									<a href="${pageContext.request.contextPath}/product/${p.menu_id}/${p.product_id}" class="cart-btn">Xem thêm</a>
								</div>
							</div>
						</c:forEach>
					</c:when>
				</c:choose>
			</div>
		</div>
	</div>
	<div class="latest-news pt-150 pb-150">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 offset-lg-2 text-center">
					<div class="section-title">	
						<h3><span class="orange-text">Bài viết</span> mới nhất</h3>
					</div>
				</div>
			</div>
			<div class="row">
			<c:choose>
				<c:when test="${not empty listNewsDetail}">
					<c:forEach items="${listNewsDetail}" var="nd">
						<div class="col-lg-4 col-md-6">
							<div class="single-latest-news">
								<a href="single-news.html"><img src="${urlUpload}/${nd.news_detail_image}" alt=""></a>
								<div class="news-text-box">
									<h3><a href="single-news.html">${nd.news_detail_name}</a></h3>
									<p class="blog-meta">
										<span class="date"><i class="fas fa-calendar"></i> ${nd.news_detail_date}</span>
									</p>
									<p class="excerpt">${nd.news_detail_description}</p>
									<a href="${pageContext.request.contextPath}/news/detail/${nd.news_id}/${nd.news_detail_id}" class="read-more-btn">Đọc thêm <i class="fas fa-angle-right"></i></a>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:when>
			</c:choose>
			</div>
		</div>
	</div>
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