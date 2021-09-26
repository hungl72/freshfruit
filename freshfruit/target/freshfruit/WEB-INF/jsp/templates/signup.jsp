<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/WEB-INF/jsp/components/taglib.jsp" %>
<!doctype html>
<html lang="en">
  <head>
  	<title>Tạo tài khoản</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/auth/css/style.css">
	</head>
	<body>
	<section class="ftco-section">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-md-7 col-lg-5">
					<div class="login-wrap p-4 p-md-5">
		      	<div class="icon d-flex align-items-center justify-content-center">
		      		<span class="fa fa-user-o"></span>
		      	</div>
		      	<h3 class="text-center mb-4">Tạo tài khoản</h3>
						<form class="login-form" method="post">
		      		<div class="form-group">
		      			<input type="text" class="form-control rounded-left" name="username" placeholder="Tên đăng nhâp ..." required>
		      		</div>
		      		<div class="form-group">
		      			<input type="text" class="form-control rounded-left" name="fullname" placeholder="Họ tên ..." required>
		      		</div>
	            <div class="form-group d-flex">
	              <input type="password" class="form-control rounded-left" name="password" placeholder="Mật khẩu ..." required>
	            </div>
	            <div class="form-group d-flex">
	              <input type="password" class="form-control rounded-left" name="repassword" placeholder="Nhập lại mật khẩu ..." required>
	            </div>
	            <div class="form-group d-flex">
	              <h5 style="color: red;">${notification}</h5>
	            </div>
	            <div class="form-group">
	            	<button type="submit" class="form-control btn btn-primary rounded submit px-3">Đăng ký</button>
	            </div>
	          </form>
	        </div>
				</div>
			</div>
		</div>
	</section>

	<script src="${pageContext.request.contextPath}/resources/auth/js/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/auth/js/popper.js"></script>
  <script src="${pageContext.request.contextPath}/resources/auth/js/bootstrap.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/auth/js/main.js"></script>

	</body>
</html>

