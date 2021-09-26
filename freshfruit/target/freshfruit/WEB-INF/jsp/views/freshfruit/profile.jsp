<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/components/taglib.jsp" %>
 <c:url value="/resources/img" var="urlUpload"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/freshfruit/css/profile.css" />
</head>
 <style>
    .errors{
    	color:red;
    }
</style>
<body style="background-color: #051922;">
	<div class="container" style="padding-top: 8rem;">
	<form method="post" action="${pageContext.request.contextPath}/profile/update" enctype="multipart/form-data">
    <div class="main-body">
          <div class="row gutters-sm">
            <div class="col-md-4 mb-3">
              <div class="card">
                <div class="card-body">
                  <div class="d-flex flex-column align-items-center text-center">
                    <img src="${urlUpload}/${u.image}" alt="Admin" class="rounded-circle" width="200"/>
					<input name="file" id="file" type="file" class="form-control" placeholder="Hình ảnh">
                    <div class="mt-3">
                      <h5>Tên tài khoản : <input name="username" id="address" type="text" class="form-control" value="${u.username}" readonly="readonly"></h5>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-md-8">
              <div class="card mb-3">
                <div class="card-body">
                <c:if test="${not empty msg}">
					<div class="alert alert-primary" role="alert">${msg}</div>
				</c:if>
                  <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0">Mã người dùng</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                      ${u.id}
                    </div>
                  </div>
                  <hr>
                  <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0">Họ tên</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                      <input name="fullname" id="address" type="text" class="form-control" value="${u.fullname}">
                    </div>
                  </div>
                  <hr>
                  <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0">Thông tin liên hệ</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                      <input name="information" id="address" type="text" class="form-control" value="${u.information}">
                    </div>
                  </div>
                  <hr>
                  <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0">Email</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                      <input name="email" id="address" type="text" class="form-control" value="${u.email}">
                    </div>
                  </div>
                  <hr>
                  <div class="row">
                    <div class="col-sm-3">
                      <h6 class="mb-0">Mật khẩu</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                      <input name="password" id="address" type="password" class="form-control" value="" >
                    </div>
                  </div>
                  <hr>
                  <div class="row">
                    <div class="col-sm-12">
                      <button class="btn btn-primary" type="submit">Cập nhật</button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        </form>
    </div>
</body>
</html>