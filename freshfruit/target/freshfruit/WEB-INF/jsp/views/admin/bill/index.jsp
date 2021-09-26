<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/WEB-INF/jsp/components/taglib.jsp" %>
    
     <c:url value="/resources/img" var="urlUpload"/>
        <div class="page-wrapper">
            <div class="page-breadcrumb">
                <div class="row">
                    <div class="col-5 align-self-center">
                        <h4 class="page-title">Quản lý hóa đơn</h4>
                    </div>
                    <div class="col-7 align-self-center">
                    </div>
                </div>
            </div>
            <div class="container-fluid">
	            <c:if test="${not empty msg}">
					<div class="alert alert-primary" role="alert">${msg}</div>
				</c:if>
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body">
                                <div class="table-responsive">
                                <div id="update">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th scope="col">Mã hóa đơn</th>
                                                <th scope="col">Ngày xuất hóa đơn</th>
                                                <th scope="col">Chi tiết hóa đơn</th>
                                                <th scope="col">Giá gốc</th>
                                                <th scope="col">Đã giảm</th>
                                                <th scope="col">Đơn giá</th>
                                                <th scope="col">Địa chỉ</th>
                                                <th scope="col">Mã khách hàng</th>
                                                <th scope="col">Tên khách hàng</th>
                                            </tr>
                                        </thead>                        
                                        <tbody>                  
                                        	<c:choose>
                                        		<c:when test="${not empty listBill}">
													<c:forEach items="${listBill}" var="l">
			                                            <tr>
			                                                <td>${l.bill_id}</td>
			                                                <td>${l.bill_date}</td>
			                                                <td>${l.bill_product}</td>
			                                                <td>${l.root_price}</td>
			                                                <td>${l.reduced}</td>
			                                                <td>${l.bill_price}</td>
			                                                <td>${l.bill_information}</td>
			                                                <td>${l.id_user}</td>
			                                                <td>${l.name_user}</td>
			                                            </tr>                
                                            		</c:forEach>
                                                 </c:when>
                                        	</c:choose>
                                        </tbody>
                                    </table>
                                    </div>
                                    <div style="float: right;">
                                    <c:if test="${currentPage != 1}">
									<a href="${pageContext.request.contextPath}/admin/bill/page/${currentPage-1}"><button class="btn btn-primary"> << </button></a>
									</c:if>
										<c:forEach begin="1" end="${totalPage}" var="i">
											<c:choose>
												<c:when test="${i == currentPage}">
													<a href="${pageContext.request.contextPath}/admin/bill/page/${i}"><button class="btn btn-danger">${i}</button></a>
												</c:when>
												<c:otherwise>
													<a href="${pageContext.request.contextPath}/admin/bill/page/${i}"><button class="btn btn-primary">${i}</button></a>
												</c:otherwise>
											</c:choose>
								      </c:forEach>
								      <c:if test="${currentPage != totalPage}">
									<a href="${pageContext.request.contextPath}/admin/bill/page/${currentPage+1}"><button class="btn btn-primary"> >> </button></a>
									</c:if>
                                    </div>
                                    <div style="clear: both;"></div>
                                </div>
                            </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
<script>
function del(x){
	$.ajax({
	    type: 'POST',
	    url: '${pageContext.request.contextPath}/admin/newsdetail/del',
	    data: {id : x, page : ${currentPage}},
	    success: function( data ) {
	        $( "#update").html(data);
	    }
});
}
</script>