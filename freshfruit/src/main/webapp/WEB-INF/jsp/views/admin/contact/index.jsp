<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/WEB-INF/jsp/components/taglib.jsp" %>
    
     <c:url value="/resources/img" var="urlUpload"/>
        <div class="page-wrapper">
            <div class="page-breadcrumb">
                <div class="row">
                    <div class="col-5 align-self-center">
                        <h4 class="page-title">Quản lý liên hệ</h4>
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
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th scope="col">Mã liên hệ</th>
                                                <th scope="col">Số điện thoại</th>
                                                <th scope="col">Tiêu đề</th>
                                                <th scope="col">Nội dung</th>
                                                <th scope="col">Chức năng</th>
                                            </tr>
                                        </thead>                        
                                        <tbody>    
                                        	<tr id="update"></tr>           
                                        	<c:choose>
                                        		<c:when test="${not empty listContact}">
													<c:forEach items="${listContact}" var="c">
			                                            <tr id="update${c.contact_id}">
			                                                <td>${c.contact_id}</td>
			                                                <td>${c.contact_phone}</td>
			                                                <td>${c.contact_subject}</td>
			                                                <td>${c.contact_message}</td>
			                                                <td><a href="javascript:void(0)" onclick="del(${c.contact_id})"><button class="btn btn-primary">Xóa</button></a></td>
			                                             </tr>
                                            		</c:forEach>
                                                 </c:when>
                                        	</c:choose>
                                        </tbody>
                                    </table>
                                    <div style="float: right;">
                                    <c:if test="${currentPage != 1}">
									<a href="${pageContext.request.contextPath}/admin/contact/page/${currentPage-1}"><button class="btn btn-primary"> << </button></a>
									</c:if>
										<c:forEach begin="1" end="${totalPage}" var="i">
											<c:choose>
												<c:when test="${i == currentPage}">
													<a href="${pageContext.request.contextPath}/admin/contact/page/${i}"><button class="btn btn-danger">${i}</button></a>
												</c:when>
												<c:otherwise>
													<a href="${pageContext.request.contextPath}/admin/contact/page/${i}"><button class="btn btn-primary">${i}</button></a>
												</c:otherwise>
											</c:choose>
								      </c:forEach>
								      <c:if test="${currentPage != totalPage}">
									<a href="${pageContext.request.contextPath}/admin/contact/page/${currentPage+1}"><button class="btn btn-primary"> >> </button></a>
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
	    url: '${pageContext.request.contextPath}/admin/contact/del',
	    data: {id : x, page : ${currentPage}},
	    success: function(data) {
	       $('#update'+x).remove();
	       $('#update').html(data);
	    }
		});
	}
}
</script>