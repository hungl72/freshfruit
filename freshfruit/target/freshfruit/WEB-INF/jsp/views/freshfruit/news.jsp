<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/components/taglib.jsp" %>
<c:set value="resources/img" var="urlUpload"/>
<!DOCTYPE html>
	<div class="breadcrumb-section breadcrumb-bg">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 offset-lg-2 text-center">
					<div class="breadcrumb-text">
						<h1>${getNewsName}</h1>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="latest-news mt-150 mb-150">
		<div class="container">
			<div class="row">
			<c:choose>
				<c:when test="${not empty list_news_id_news_detail_id}">
					<c:forEach items="${list_news_id_news_detail_id}" var="l">
						<div class="col-lg-4 col-md-6">
							<div class="single-latest-news">
								<a href="${pageContext.request.contextPath}/news/detail/${l.news_id}/${l.news_detail_id}"><img src="${pageContext.request.contextPath}/${urlUpload}/${l.news_detail_image}"/></a>
								<div class="news-text-box">
									<h3><a href="${pageContext.request.contextPath}/news/detail/${l.news_id}/${l.news_detail_id}">${l.news_detail_name}</a></h3>
									<p class="blog-meta">
										<span class="date"><i class="fas fa-calendar"></i> ${l.news_detail_date}</span>
									</p>
										<c:set value="${fn:length(l.news_detail_description)}" var="length" />
										<c:set value="${200}" var="lengt" />
										<c:choose>
										<c:when test="${length lt lengt}">
										<p class="excerpt">${l.news_detail_description} ...</p>
										</c:when>
									<c:otherwise>
										<p class="excerpt">${fn:substring(l.news_detail_description, 0, 200)} ...</p>
									</c:otherwise>
									</c:choose>
									<a href="${pageContext.request.contextPath}/news/detail/${l.news_id}/${l.news_detail_id}" class="read-more-btn">Đọc thêm ... <i class="fas fa-angle-right"></i></a>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<h4>Chưa có bài viết nào !</h4>
				</c:otherwise>
			</c:choose>
		</div>
			<c:if test="${totalPage > 1}">
			<div class="row">
						<div class="col-lg-12 text-center">
							<div class="pagination-wrap">
								<ul>
									<c:if test="${currentPage != 1}">
									<li><a href="${pageContext.request.contextPath}/news/${news_id}/page/${currentPage-1}"> << </a></li>
									</c:if>
										<c:forEach begin="1" end="${totalPage}" var="i">
											<c:choose>
												<c:when test="${i == currentPage}">
													<li><a class="active" href="${pageContext.request.contextPath}/news/${news_id}/page/${i}">${i}</a></li>
												</c:when>
												<c:otherwise>
													<li class="<c:if test="${i == currentPage}"></c:if>"><a href="${pageContext.request.contextPath}/news/${news_id}/page/${i}">${i}</a></li>
												</c:otherwise>
											</c:choose>
								      </c:forEach>
								      <c:if test="${currentPage != totalPage}">
									<li><a href="${pageContext.request.contextPath}/news/${news_id}/page/${currentPage+1}"> >> </a></li>
									</c:if>
								</ul>
							</div>
						</div>
					</div>
		</c:if>
		</div>
	</div>
	<div class="logo-carousel-section">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="logo-carousel-inner">
						<div class="single-logo-item">
							<img src="${contextPath}/img/company-logos/1.png" alt="">
						</div>
						<div class="single-logo-item">
							<img src="${contextPath}/img/company-logos/2.png" alt="">
						</div>
						<div class="single-logo-item">
							<img src="${contextPath}/img/company-logos/3.png" alt="">
						</div>
						<div class="single-logo-item">
							<img src="${contextPath}/img/company-logos/4.png" alt="">
						</div>
						<div class="single-logo-item">
							<img src="${contextPath}/img/company-logos/5.png" alt="">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>