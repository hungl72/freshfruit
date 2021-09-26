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
                                <a href="${pageContext.request.contextPath}/admin/newsdetail/add"><button class="btn btn-primary">Thêm tin tức</button></a>
                                <div class="table-responsive">
                                <div id="update">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th scope="col">Mã tin tức</th>
                                                <th scope="col">Tên tin tức</th>
                                                <th scope="col">Ngày đăng</th>
                                                <th scope="col">Nội dung</th>
                                                <th scope="col">Ảnh</th>
                                                <th scope="col">Mã danh mục tin</th>
                                                <th scope="col">Tên danh mục tin</th>
                                                 <th scope="col">Chức năng</th>
                                            </tr>
                                        </thead>                        
                                        <tbody>                  
                                        	<c:choose>
                                        		<c:when test="${not empty listDetail}">
													<c:forEach items="${listDetail}" var="d">
			                                            <tr>
			                                                <td>${d.news_detail_id}</td>
			                                                <td width="12%;">${d.news_detail_name}</td>
			                                                <td>${d.news_detail_date}</td>
			                                                <c:set value="${fn:length(d.news_detail_description)}" var="length" />
															<c:set value="${200}" var="lengt" />
															<c:choose>
															<c:when test="${length lt lengt}">
															<td>${d.news_detail_description}</td>
															</c:when>
															<c:otherwise>
																<td>${fn:substring(d.news_detail_description, 0, 200)} ...</td>
															</c:otherwise>
														   </c:choose>
														   <td><img src="${urlUpload}/${d.news_detail_image}" style="width: 200px;height: 150px;"/></td>
			                                                <td width="12%;">${d.news_id}</td>
			                                                <td width="12%;">${d.news_name}</td>
			                                                <td width="12%;">
			                                                   <a href="${pageContext.request.contextPath}/admin/newsdetail/edit/${d.news_detail_id}"><button class="btn btn-primary">Sửa</button></a>
			                                                   <a href="javascript:void(0)" onclick="del('${d.news_detail_id}')"><button class="btn btn-primary">Xóa</button></a>
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
									<a href="${pageContext.request.contextPath}/admin/newsdetail/page/${currentPage-1}"><button class="btn btn-primary"> << </button></a>
									</c:if>
										<c:forEach begin="1" end="${totalPage}" var="i">
											<c:choose>
												<c:when test="${i == currentPage}">
													<a href="${pageContext.request.contextPath}/admin/newsdetail/page/${i}"><button class="btn btn-danger">${i}</button></a>
												</c:when>
												<c:otherwise>
													<a href="${pageContext.request.contextPath}/admin/newsdetail/page/${i}"><button class="btn btn-primary">${i}</button></a>
												</c:otherwise>
											</c:choose>
								      </c:forEach>
								      <c:if test="${currentPage != totalPage}">
									<a href="${pageContext.request.contextPath}/admin/newsdetail/page/${currentPage+1}"><button class="btn btn-primary"> >> </button></a>
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
	let del = confirm('Bạn có chắc chắn muốn xóa ?');
	if(del){
	$.ajax({
	    type: 'POST',
	    url: '${pageContext.request.contextPath}/admin/newsdetail/del',
	    data: {id : x, page : ${currentPage}},
	    success: function( data ) {
	        $( "#update").html(data);
	    }
		});
	}
}
</script>