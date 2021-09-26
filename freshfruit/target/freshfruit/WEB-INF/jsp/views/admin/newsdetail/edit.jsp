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
                        <h4 class="page-title">Sửa tin tức</h4>
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
								<input name="news_detail_name" id="address" type="text" class="form-control" value="${nd.news_detail_name}">
								<form:errors path="nd.news_detail_name" cssClass="errors" />
							</div>
							<div class="form-group">
								<label for="description">Nội dung</label>
								<textarea id="news_detail_description"  name="news_detail_description" placeholder="">${nd.news_detail_description}</textarea>
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
		                                   <select class="form-control" name="news_id" id="news_id">
		                                   <option selected value="${nd.news_id}">Chọn ...</option>
		                                     <c:forEach items="${news}" var="n">
		                                         <option value="${n.news_id}">${n.news_name}</option>
		                                     </c:forEach>
		                                     </select>
		                                   </div>
                                    </c:when>
                                    </c:choose>
                                </div>
                                <div class="form-group">
								<label for="promotion">Danh mục tin hiện tại</label>
								<input type="text" class="form-control" value="${nd.news_name}" disabled="disabled">
								<form:errors path="nd.news_name" cssClass="errors" />
							</div>
							<div class="form-group">
								<label for="file">Hình ảnh</label><br />
								<img src="${urlUpload}/${nd.news_detail_image}" style="width: 300px;height: 200px;padding: 0.5rem 0rem 1rem 0rem;"/>
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