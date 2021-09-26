<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@include file="/WEB-INF/jsp/components/taglib.jsp" %>
             <c:url value="/resources/img" var="urlUpload"/>
 <style>
    .errors{
    	color:red;
    }
</style>
        <div class="page-wrapper">
            <div class="page-breadcrumb">
                <div class="row">
                    <div class="col-5 align-self-center">
                        <h4 class="page-title">Sửa sản phẩm</h4>
                    </div>
                    <div class="col-7 align-self-center">
                        <div class="d-flex align-items-center justify-content-end">
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb">
                                    <li class="breadcrumb-item">
                                        <a href="#">Home</a>
                                    </li>
                                    <li class="breadcrumb-item active" aria-current="page">Basic Table</li>
                                </ol>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container-fluid">
                <div class="row">
                <form method="post" enctype="multipart/form-data">
                    <div class="col-6">
							<div class="form-group">
								<label for="name">Tên sản phẩm</label>
								<input name="product_name" id="address" type="text" class="form-control" value="${p.product_name}">
								<form:errors path="p.product_name" cssClass="errors" />
							</div>
							<div class="form-group">
								<label for="price">Đơn giá</label>
								<input name="product_price" id="address" type="text" class="form-control" value="${p.product_price}">
								<form:errors path="p.product_price" cssClass="errors" />
							</div>
							<div class="form-group">
								<label for="promotion">Khuyến mãi</label>
								<input name="product_promotion" id="address" type="text" class="form-control" value="${p.product_promotion}">
								<form:errors path="p.product_promotion" cssClass="errors" />
							</div>
							<div class="form-group">
								<label for="description">Mô tả</label>
								<textarea id="product_description"  name="product_description" placeholder="">${p.product_description}</textarea>
								<form:errors path="p.product_description" cssClass="errors" />
								<script type="text/javascript">
								CKEDITOR.replace('product_description');
							</script>
							</div>
                                <div class="form-group col-md-6" style="margin-top: 1rem;">
                                    <label>Danh mục con</label>
                                    <c:choose>
                                    	<c:when test="${not empty menuC}">
		                                   <div class="form-group">
		                                   <select class="form-control" name="menu_id" id="menu_id">
		                                   <option selected value="${p.menu_id}">Chọn ...</option>
		                                     <c:forEach items="${menuC}" var="c">
		                                         <option value="${c.menu_id}">${c.menu_name}</option>
		                                     </c:forEach>
		                                     </select>
		                                   </div>
                                    </c:when>
                                    </c:choose>
                                </div>
                                <div class="form-group">
								<label for="promotion">Danh mục con hiện tại</label>
								<input type="text" class="form-control" value="${p.menu_name}" disabled="disabled">
								<form:errors path="p.product_promotion" cssClass="errors" />
							</div>
							<div class="form-group">
								<label for="file">Hình ảnh</label><br />
								<img src="${urlUpload}/${p.product_image}" style="width: 300px;height: 200px;padding: 0.5rem 0rem 1rem 0rem;"/>
								<input name="file" id="file" type="file" class="form-control" placeholder="Hình ảnh">
							</div>
							<div class="row">
						<div class="col-sm-12">
							<input type="submit" value="Sửa" class="btn btn-primary" />
						</div>
					</div>
                        </div>
                        </form>
                    </div>
                </div>
            </div>