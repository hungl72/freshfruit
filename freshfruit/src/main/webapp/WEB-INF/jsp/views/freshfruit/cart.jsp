<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@include file="/WEB-INF/jsp/components/taglib.jsp" %>
     <c:set value="resources/img" var="urlUpload" />
<!DOCTYPE html>
	<div class="breadcrumb-section breadcrumb-bg">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 offset-lg-2 text-center">
					<div class="breadcrumb-text">
						<h1>Giỏ hàng</h1>
					</div>
				</div>
			</div>
		</div>
	</div>
<c:choose>
<c:when test="${not empty arrProduct}">
<div id="update">
	<div class="cart-section mt-150 mb-150">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-md-12">
					<div class="cart-table-wrap">
						<table class="cart-table">
							<thead class="cart-table-head">
								<tr class="table-head-row">
									<th class="product-image">Ảnh sản phẩm</th>
									<th class="product-name">Tên sản phẩm</th>
									<th class="product-total">Khối lượng (Kg)</th>
									<th class="product-total">Khuyến mãi (%)</th>
									<th class="product-price">Đơn giá</th>
									<th class="product-price">Thành tiền</th>
									<th class="product-remove"></th>
								</tr>
							</thead>
							<tbody>
									<c:forEach items="${arrProduct}" var="arr">
									<tr class="table-body-row">
										<td class="product-image"><img src="${urlUpload}/${arr.product_image}" alt=""></td>
										<td class="product-name">${arr.product_name}</td>
										<td class="product-total">${arr.product_mass}</td>
										<td class="product-total">${arr.product_promotion}</td>
										<td class="product-price">${(arr.product_price-(arr.product_price*arr.product_promotion)/100)}</td>
										<c:set var="money" value="${(arr.product_price-(arr.product_price*arr.product_promotion)/100)*arr.product_mass}"></c:set>
										<td class="product-price">${money}</td>
										<td class="product-remove"><a href="javascript:void(0)" onclick="del(${arr.product_id})"><i class="far fa-window-close"></i></a></td>
									</tr>
									</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<div class="col-lg-4">
					<div class="total-section">
						<table class="total-table">
							<thead class="total-table-head">
								<tr class="table-total-row">
									<th>Chi tiết đơn</th>
									<th>Giá</th>
								</tr>
							</thead>
							<tbody>
								<tr class="total-data">
									<td><strong>Tổng tiền cho đơn hàng : </strong></td>
										<c:choose>
											<c:when test="${not empty total}">
												<td class="product-price">${total}</td>
											</c:when>
											<c:otherwise>
												<td class="product-price"></td>
											</c:otherwise>
										</c:choose>
								</tr>
								<tr class="total-data">
									<td><strong>Số tiền cần thanh toán (Đã trừ khuyến mãi nếu có) : </strong></td>
									<c:choose>
											<c:when test="${not empty totalPromotion}">
												<td class="product-price">${totalPromotion}</td>
											</c:when>
											<c:otherwise>
												<td class="product-price"></td>
											</c:otherwise>
										</c:choose>
								</tr>
							</tbody>
						</table>
						<div class="cart-buttons">
							<c:choose>
								<c:when test="${not empty sessionScope.user && not empty arrProduct}">
									<form action="https://www.sandbox.paypal.com/cgi-bin/webscr" method="post">
							            <input type="hidden" name="business" value="hungpbc99@gmail.com">
							            <input type="hidden" name="cmd" value="_xclick">
							            <input type="hidden" name="item_name" value="HoaDonMuaHang">
							            <input type="number" hidden="hidden" name="amount" placeholder="Nhập số tiền vào" value="${totalPromotion}">
							            <input type="hidden" name="currency_code" value="USD">
							            <input type="hidden" name="return" value="http://localhost:8080/freshfruit/cart/payment/success">
							            <input type="hidden" name="cancel_return" value="http://localhost/demothanhtoanonline/loi.html">
							            <input type="submit" name="submit" value="Thanh toán Paypal">
									</form>
								</c:when>
								<c:otherwise>
									<p>Bạn cần đăng nhập để thực hiện thanh toán !</p>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	</c:when>
	<c:otherwise><h4>Chưa có sản phẩm nào trong giỏ !</h4></c:otherwise>
</c:choose>
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
function del(x){
	let del = confirm('Bạn có chắc chắn muốn xóa ?');
	if(del){
	$.ajax({
	    type: 'POST',
	    url: '${pageContext.request.contextPath}/cart/del',
	    data: {id : x},
	    success: function( data ) {
	        $( "#update").html(data);
	    }
	});
	}
}
</script>