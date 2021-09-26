<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@include file="/WEB-INF/jsp/components/taglib.jsp" %>
 <style>
    .errors{
    	color:red;
    }
</style>
        <div class="page-wrapper">
            <div class="page-breadcrumb">
                <div class="row">
                    <div class="col-5 align-self-center">
                        <h4 class="page-title">Thêm tin tức</h4>
                    </div>
                    <div class="col-7 align-self-center">
                    </div>
                </div>
            </div>
            <div class="container-fluid">
                <div class="row">
                <form method="post" enctype="multipart/form-data">
                    <div class="col-6">
							<div class="form-group">
								<label for="name">Tên tin tức</label>
								<input name="news_detail_name" id="address" type="text" class="form-control" placeholder="Tên tin tức ...">
								<form:errors path="nd.news_detail_name" cssClass="errors" />
							</div>
							<div class="form-group">
								<label for="description">Nội dung</label>
								<textarea name="news_detail_description" placeholder="Mô tả ..."></textarea>
								<form:errors path="nd.news_detail_description" cssClass="errors" />
								<script type="text/javascript">
								CKEDITOR.replace('news_detail_description');
							</script>
							</div>
                                <div class="form-group col-md-6" style="margin-top: 1rem;">
                                    <label>Danh mục tin</label>
                                    <c:choose>
                                    	<c:when test="${not empty news}">
		                                   <div class="form-group">
		                                   <select class="form-control" name="news_id">
		                                     <c:forEach items="${news}" var="n">
		                                         <option value="${n.news_id}">${n.news_name}</option>
		                                     </c:forEach>
		                                     </select>
		                                   </div>
                                    </c:when>
                                    </c:choose>
                                </div>
							<div class="form-group">
								<label for="file">Hình ảnh</label>
								<input name="file" id="file" type="file" class="form-control" placeholder="Hình ảnh">
							</div>
							<div class="row">
						<div class="col-sm-12">
							<input type="submit" value="Thêm" class="btn btn-primary" />
						</div>
					</div>
                        </div>
                        </form>
                    </div>
                </div>
            </div>