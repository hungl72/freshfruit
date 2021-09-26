<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@include file="/WEB-INF/jsp/components/taglib.jsp" %>
  <c:url value="/resources/img" var="urlUpload"/>
<!DOCTYPE html>
	<div class="breadcrumb-section breadcrumb-bg">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 offset-lg-2 text-center">
					<div class="breadcrumb-text">
						<p>Fresh and Organic</p>
						<h1>Shop</h1>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="product-section mt-150 mb-150">
		<div class="container">
			<c:if test="${not empty listProduct}">
				<c:if test="${not empty listProductByMenuId}">
						<div class="row">
			                <div class="col-md-12">
			                    <div class="product-filters">
			                        <ul>
			                            <li class="active" data-filter="*">All</li>
			                            <li data-filter=".strawberry">Strawberry</li>
			                            <li data-filter=".berry">Berry</li>
			                            <li data-filter=".lemon">Lemon</li>
			                        </ul>
			                    </div>
			                </div>
			            </div>
				</c:if>
			</c:if>
			<div class="row product-lists">
			<c:choose>
				<c:when test="${not empty listProductByMenuId}">
					<c:forEach items="${listProductByMenuId}" var="product">
				<div class="col-lg-4 col-md-6 text-center strawberry">
					<div class="single-product-item">
						<div class="product-image">
							<a href="${pageContext.request.contextPath}/product/${product.menu_id}/${product.product_id}"><img src="${urlUpload}/${product.product_image}" alt="" height="180px"></a>
						</div>
						<h3>${product.product_name}</h3>
						<p class="product-price"><span>Per Kg</span> ${product.product_price}$ </p>
						<a href="${pageContext.request.contextPath}/product/${product.menu_id}/${product.product_id}" class="cart-btn">Xem thêm</a>
					</div>
				</div>
					</c:forEach>
				</c:when>
					<c:when test="${not empty listProduct}">
						<c:forEach items="${listProduct}" var="product">
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
				</c:when>
				<c:otherwise>
					<h4>Chưa có sản phẩm nào !</h4>
				</c:otherwise>
			</c:choose>
			</div>
		<c:if test="${totalPage > 1}">
			<div class="row">
						<div class="col-lg-12 text-center">
							<div class="pagination-wrap">
								<ul>
									<c:if test="${currentPage != 1}">
									<li><a href="${pageContext.request.contextPath}/product/${menu_id}/page/${currentPage-1}"> << </a></li>
									</c:if>
										<c:forEach begin="1" end="${totalPage}" var="i">
											<c:choose>
												<c:when test="${i == currentPage}">
													<li><a class="active" href="${pageContext.request.contextPath}/product/${menu_id}/page/${i}">${i}</a></li>
												</c:when>
												<c:otherwise>
													<li class="<c:if test="${i == currentPage}"></c:if>"><a href="${pageContext.request.contextPath}/product/${menu_id}/page/${i}">${i}</a></li>
												</c:otherwise>
											</c:choose>
								      </c:forEach>
								      <c:if test="${currentPage != totalPage}">
									<li><a href="${pageContext.request.contextPath}/product/${menu_id}/page/${currentPage+1}"> >> </a></li>
									</c:if>
								</ul>
							</div>
						</div>
					</div>
		</c:if>
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