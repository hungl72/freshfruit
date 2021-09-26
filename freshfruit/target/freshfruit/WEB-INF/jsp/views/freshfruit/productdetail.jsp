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
						<p>Bạn đang xem</p>
						<h1>${getNameMenu}</h1>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="single-product mt-150 mb-150">
		<div class="container">
			<div class="row">
				<c:choose>
					<c:when test="${not empty productByProductId}" >
					<div class="col-md-5">
					<div class="single-product-img">
						<img src="${urlUpload}/${productByProductId.product_image}" alt="">
					</div>
				</div>
				<div class="col-md-7">
					<div class="single-product-content">
						<h3 style="margin-bottom: 0;">${productByProductId.product_name} 
						<c:choose>
							<c:when test="${productByProductId.product_promotion > 0}">
								<span style="font-weight: bold;;color: red;font-size: 1.2rem;">(Khuyến mãi => ${productByProductId.product_promotion}%)</span>
							</c:when>
						</c:choose>
						</h3>
						<c:choose>
							<c:when test="${productByProductId.product_promotion > 0}">
								<c:set var="x" value="${productByProductId.product_price - (productByProductId.product_price*productByProductId.product_promotion/100)}"></c:set>
								<p class="single-product-pricing"><strong style="text-decoration: line-through;">${productByProductId.product_price} $ / Kg</strong> => <strong style="font-weight: bold;color: red;">${x} $ / Kg</strong></p>
							</c:when>
							<c:otherwise>
								<p class="single-product-pricing">${productByProductId.product_price} $ / Kg</p>
							</c:otherwise>
						</c:choose>
						<p>${productByProductId.product_description}</p>
						<div class="single-product-form">
							<form action="index.html">
								<input id="mass" type="number" placeholder="0" value = "0"> <label style="font-size: 1.8rem;font-weight: bold;">Kg</label>
							</form>
							<c:set var="z" value="${productByProductId}" /> 
							<a href="javascript:void(0)" onclick="cart('${z.product_id}')" class="cart-btn"><i class="fas fa-shopping-cart"></i>Thêm vào giỏ</a>
							<p><strong>Danh mục : </strong>${getNameCategory}</p>
						</div>
						<h4></h4>
					</div>
					</c:when>
				</c:choose>
				</div>
			</div>
		</div>
	</div>
	<div class="more-products mb-150">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 offset-lg-2 text-center">
					<div class="section-title">	
						<h3><span class="orange-text">Sản phẩm</span> liên quan</h3>
					</div>
				</div>
			</div>
			<div class="row">
				<c:choose>
					<c:when test="${not empty listProductSameCategory}">
						<c:forEach items="${listProductSameCategory}" var="p">
							<div class="col-lg-4 col-md-6 text-center">
								<div class="single-product-item">
									<div class="product-image">
										<a href="${pageContext.request.contextPath}/product/${p.menu_id}/${p.product_id}"><img src="${urlUpload}/${p.product_image}" alt="" height="180px"></a>
									</div>
									<h3>${p.product_name}</h3>
									<p class="product-price"><span>Per Kg</span> ${p.product_price}$ </p>
									<a href="${pageContext.request.contextPath}/product/${p.menu_id}/${p.product_id}" class="cart-btn">Xem thêm</a>
								</div>
							</div>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<h3>Không có sản phẩm liên quan nào !</h3>
				</c:otherwise>
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
<script>
function cart(x) {
	var mass = document.getElementById("mass").value;
	if(mass > 0){
	$.ajax({
		url : '${pageContext.request.contextPath}/cart',
		type : 'post',
		data :{x : x, y : mass},
		success:function(data){
        	alert('Đã thêm vào giỏ !')
        }
	});
	}else{
		alert('Khối lượng không được <= 0 !')
	}
};	
</script>