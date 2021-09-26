<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/WEB-INF/jsp/components/taglib.jsp" %>
    
     <c:url value="/resources/img" var="urlUpload"/>
        <div class="page-wrapper">
            <div class="page-breadcrumb">
                <div class="row">
                    <div class="col-5 align-self-center">
                        <h4 class="page-title">Quản lý người dùng</h4>
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
                                                <th scope="col">Mã người dùng</th>
                                                <th scope="col">Tên đăng nhập</th>
                                                <th scope="col">Họ tên</th>
                                                <th scope="col">Thông tin - Địa chỉ</th>
                                                <th scope="col">Ảnh</th>
                                                <th scope="col">Cấp bậc</th>
                                                <th scope="col">Trạng thái</th>
                                                 <th scope="col">Chức năng</th>
                                            </tr>
                                        </thead>                        
                                        <tbody>
                                        <tr id="update"></tr>                  
                                        	<c:choose>
                                        		<c:when test="${not empty listUser}">
													<c:forEach items="${listUser}" var="u">
			                                            <tr id="updateD${u.id}">
			                                                <td>${u.id}</td>
			                                                <td>${u.username}</td>
			                                                <td>${u.fullname}</td>
			                                                <td>${u.information}</td>
			                                                <td><img src="${urlUpload}/${u.image}" style="width: 200px;height: 150px;"/></td>
			                                                <c:set value="${u.role_id}" var="role" />
															<c:choose>
															<c:when test="${role == 1}">
															<td>Admin</td>
															</c:when>
															<c:when test="${role == 2}">
															<td>Moderator</td>
															</c:when>
															<c:otherwise>
																<td>User</td>
															</c:otherwise>
														   </c:choose>
														   <c:set value="${u.enabled}" var="enabled" />
			                                                <c:choose>
			                                                <c:when test="${enabled == 1 && u.role_id == 1}"><td></td><td></td></c:when>
															<c:when test="${enabled == 1 && u.role_id == 2}">
															<td width="14%"><button class="btn btn-primary">Đang hoạt động</button></td>
															<td width="15%">
			                                                   <a href="javascript:void(0)" onclick="status(${u.id})"><button class="btn btn-danger">Khóa</button></a>
			                                                   <a href="javascript:void(0)" onclick="del('${u.id}')"><button class="btn btn-danger">Xóa</button></a>
			                                                </td>
															</c:when>
															<c:when test="${enabled == 1 && u.role_id == 3}">
															<td width="14%"><button class="btn btn-primary">Đang hoạt động</button></td>
															<td width="15%">
			                                                   <a href="javascript:void(0)" onclick="status(${u.id})"><button class="btn btn-danger">Khóa</button></a>
			                                                   <a href="javascript:void(0)" onclick="del('${u.id}')"><button class="btn btn-danger">Xóa</button></a>
			                                                </td>
															</c:when>
															<c:when test="${enabled == 0}">
															<td width="14%"><button class="btn btn-danger">Đang bị khóa</button></td>
															<td width="15%">
			                                                   <a href="javascript:void(0)" onclick="status(${u.id})"><button class="btn btn-primary">Mở khóa</button></a>
			                                                   <a href="javascript:void(0)" onclick="del('${u.id}')"><button class="btn btn-danger">Xóa</button></a>
			                                                </td>
															</c:when>
														   </c:choose>
			                                            </tr>                
                                            		</c:forEach>
                                                 </c:when>
                                        	</c:choose>
                                        </tbody>
                                    </table>
                                    <div style="float: right;">
                                    <c:if test="${currentPage != 1}">
									<a href="${pageContext.request.contextPath}/admin/user/page/${currentPage-1}"><button class="btn btn-primary"> << </button></a>
									</c:if>
										<c:forEach begin="1" end="${totalPage}" var="i">
											<c:choose>
												<c:when test="${i == currentPage}">
													<a href="${pageContext.request.contextPath}/admin/user/page/${i}"><button class="btn btn-danger">${i}</button></a>
												</c:when>
												<c:otherwise>
													<a href="${pageContext.request.contextPath}/admin/user/page/${i}"><button class="btn btn-primary">${i}</button></a>
												</c:otherwise>
											</c:choose>
								      </c:forEach>
								      <c:if test="${currentPage != totalPage}">
									<a href="${pageContext.request.contextPath}/admin/user/page/${currentPage+1}"><button class="btn btn-primary"> >> </button></a>
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
function status(x){
	$.ajax({
		url: '${pageContext.request.contextPath}/admin/user/status',
		method: 'POST',
		data: {id : x, page : ${currentPage}},
		success:function(data){
			$('#updateD'+x).html(data);
		}
	});
}
function del(x){
	let del = confirm('Bạn có chắc chắn muốn xóa ?');
	if(del){
	$.ajax({
		url: '${pageContext.request.contextPath}/admin/user/del',
		method: 'POST',
		data: {id : x, page : ${currentPage}},
		success:function(data){
			$('#updateD'+x).remove();
			$('#update').html(data);
		}
	});
	}
}
</script>