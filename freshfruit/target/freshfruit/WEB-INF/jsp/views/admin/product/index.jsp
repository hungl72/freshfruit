<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/WEB-INF/jsp/components/taglib.jsp" %>
     <c:url value="/resources/img" var="urlUpload"/>
        <div class="page-wrapper">
            <div class="page-breadcrumb">
                <div class="row">
                    <div class="col-5 align-self-center">
                        <h4 class="page-title">Quản lý sản phẩm</h4>
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
                                <a href="${pageContext.request.contextPath}/admin/product/add"><button class="btn btn-primary">Thêm sản phẩm</button></a>
                                <div class="table-responsive">
                                <div id="update">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th scope="col">Mã sản phẩm</th>
                                                <th scope="col">Tên sản phẩm</th>
                                                <th scope="col">Đơn giá ($)</th>
                                                <th scope="col">Khuyến mãi (%)</th>
                                                <th scope="col">Mô tả</th>
                                                <th scope="col">Ảnh</th>
                                                <th scope="col">Danh mục</th>
                                                 <th scope="col">Chức năng</th>
                                            </tr>
                                        </thead>                        
                                        <tbody>                  
                                        	<c:choose>
                                        		<c:when test="${not empty listProduct}">
													<c:forEach items="${listProduct}" var="p">
			                                            <tr>
			                                                <td>${p.product_id}</td>
			                                                <td width="12%;">${p.product_name}</td>
			                                                <td>${p.product_price}</td>
			                                                <td>${p.product_promotion}</td>
			                                                <c:set value="${fn:length(p.product_description)}" var="length" />
															<c:set value="${200}" var="lengt" />
															<c:choose>
															<c:when test="${length lt lengt}">
															<td>${p.product_description}</td>
															</c:when>
															<c:otherwise>
																<td>${fn:substring(p.product_description, 0, 200)} ...</td>
															</c:otherwise>
														   </c:choose>
			                                                <td><img src="${urlUpload}/${p.product_image}" style="width: 200px;height: 150px;"/></td>
			                                                <td width="12%;">${p.menu_name}</td>
			                                                <td width="12%;">
			                                                   <a href="${pageContext.request.contextPath}/admin/product/edit/${p.product_id}"><button class="btn btn-primary">Sửa</button></a>
			                                                   <a href="javascript:void(0)" onclick="del('${p.product_id}')"><button class="btn btn-primary">Xóa</button></a>
			                                                </td>
			                                            </tr>                
                                            		</c:forEach>
                                                 </c:when>
                                        	</c:choose>
                                        </tbody>
                                    </table>
                                    </div>
                                    <div style="float: right;">
                                    <c:if test="${currentPage != 1}">
									<a href="${pageContext.request.contextPath}/admin/product/page/${currentPage-1}"><button class="btn btn-primary"> << </button></a>
									</c:if>
										<c:forEach begin="1" end="${totalPage}" var="i">
											<c:choose>
												<c:when test="${i == currentPage}">
													<a href="${pageContext.request.contextPath}/admin/product/page/${i}"><button class="btn btn-danger">${i}</button></a>
												</c:when>
												<c:otherwise>
													<a href="${pageContext.request.contextPath}/admin/product/page/${i}"><button class="btn btn-primary">${i}</button></a>
												</c:otherwise>
											</c:choose>
								      </c:forEach>
								      <c:if test="${currentPage != totalPage}">
									<a href="${pageContext.request.contextPath}/admin/product/page/${currentPage+1}"><button class="btn btn-primary"> >> </button></a>
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
	    url: '${pageContext.request.contextPath}/admin/product/del',
	    data: {id : x, page : ${currentPage}},
	    success: function( data ) {
	        $( "#update").html(data);
	    }
});
}
</script>