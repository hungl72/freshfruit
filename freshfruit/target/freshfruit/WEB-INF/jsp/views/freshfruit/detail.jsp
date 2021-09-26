<%@page import="project.controllers.service.CommentChildrenService"%>
<%@page import="project.controllers.dao.CommentsChildrenDAO"%>
<%@page import="project.controllers.dao.CommentsDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="project.controllers.model.CommentChildren"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/components/taglib.jsp" %>
<c:set value="resources/img" var="urlUpload" />
<!DOCTYPE html>
	<div class="breadcrumb-section breadcrumb-bg">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 offset-lg-2 text-center">
					<div class="breadcrumb-text">
						<p>Bạn đang xem danh mục</p>
						<h1>${getNameNews}</h1>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="mt-150 mb-150">
		<div class="container">
			<div class="row">
				<div class="col-lg-10">
					<div class="single-article-section">
						<div class="single-article-text">
						<c:choose>
							<c:when test="${not empty detail}">
							<img src="${pageContext.request.contextPath}/${urlUpload}/${detail.news_detail_image}"/>
							<p class="blog-meta">
								<span class="date"><i class="fas fa-calendar"></i> 27 December, 2019</span>
							</p>
							<c:set var="detail_id" value="${detail.news_detail_id}" />
							<h2>${detail.news_detail_name}</h2>
							<p>${detail.news_detail_description}</p>
							</c:when>
						</c:choose>
						</div>
						<div id="fcmt" style="overflow: scroll;height: 400px;"><div id="pcmt"></div></div>
						
						<div class="comments-list-wrap">
						</div>
						<div class="comment-template">
							<h4>Để lại bình luận của bạn</h4>
							<form id="cmt">
								<p><textarea name="text" id="comment" cols="30" rows="10" placeholder="Your Message"></textarea></p>
								<button class="btn btn-primary" type="submit">Đăng bình luận</button>
							</form>
						</div>
					</div>
				</div>
			</div>
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
<script>
window.onload = function(){
	$.ajax({
	    type: 'POST',
	    url: '${pageContext.request.contextPath}/news/comments/fl',
	    data: {news_detail_id : <c:out value="${news_detail_id}" />},
	    success: function( data ) {
	        $( "#fcmt" ).html(data);
	    }
});
};

function processForm(e) {
    if (e.preventDefault) e.preventDefault();
		$.ajax({
		    type: 'POST',
		    url: '${pageContext.request.contextPath}/news/comments/ip',
		    data: {
				    	id: '${sessionScope.user.id}',
				    	name: '${sessionScope.user.username}',
				    	content: document.getElementById('comment').value,
				    	detail_id : <c:out value="${detail_id}" />
			    	 },
		    success: function( data ) {
		        $( "#pcmt").html(data);
		        document.getElementById('pcmt').id = '';
		        document.getElementById('z').remove();
		    }
});
		document.getElementById('comment').value = '';
    /* do what you want with the form */

    // You must return false to prevent the default form behavior
    return false;
}
var form = document.getElementById('cmt');
if (form.attachEvent) {
    form.attachEvent("submit", processForm);
} else {
    form.addEventListener("submit", processForm);
}

function rp(x){
	$.ajax({
	    type: 'POST',
	    url: '${pageContext.request.contextPath}/news/comments/ic',
	    data: {
	    		   idc : x,
	    		   id: '${sessionScope.user.id}',
	    		   name: '${sessionScope.user.username}',
	    		   content: document.getElementById('commentc-'+x).value,
	    		   detail_id : <c:out value="${detail_id}" />
	    		  },
	    success: function( data ) {
	        $( "#"+x+"").html(data);
	        document.getElementById('commentc-'+x).value = '';
	        document.getElementById('commentc-'+x).style.display = 'none';
	        document.getElementById('commentcc-'+x).style.display = 'none';
	    }
});
}

function rpc(x){
	var y = x;
	const arr = y.split(',');
	$.ajax({
	    type: 'POST',
	    url: '${pageContext.request.contextPath}/news/comments/ic',
	    data: {
	    		   idc : arr[0],
	    		   id: '${sessionScope.user.id}',
	    		   name: '${sessionScope.user.username}',
	    		   content: document.getElementById('commentccc-'+arr[1]).value,
	    		   detail_id : <c:out value="${detail_id}" />
	    		  },
	    success: function( data ) {
	        $( "#"+x+"").html(data);
	        document.getElementById('commentccc-'+arr[1]).value = '';
	    }
});
}

function updateP(x){
	$.ajax({
	    type: 'POST',
	    url: '${pageContext.request.contextPath}/news/comments/up',
	    data: {
	    		   id : x,
	    		   cmt: document.getElementById('commentcs-'+x).value,
				  },
	    success: function( data ) {
	    	//document.getElementById('commentcs-'+x).value = cmt;
	    	document.getElementById('updateP'+x).innerHTML = document.getElementById('commentcs-'+x).value;
	    	hccs(x);
	    }
});
}

function delP(x){
	$.ajax({
	    type: 'POST',
	    url: '${pageContext.request.contextPath}/news/comments/dp',
	    data: { id : x},
	    success: function( data ) {
	    	document.getElementById('p-'+x).remove();
	    }
});
}

function updateC(x){
	$.ajax({
	    type: 'POST',
	    url: '${pageContext.request.contextPath}/news/comments/uc',
	    data: {
	    		   id : x,
	    		   cmt: document.getElementById('commentccs-'+x).value,
				  },
	    success: function( data ) {
	    	//document.getElementById('commentcs-'+x).value = cmt;
	    	document.getElementById('updateC'+x).innerHTML = document.getElementById('commentccs-'+x).value;
	    	hccss(x);
	    }
});
}

function delC(x){
	$.ajax({
	    type: 'POST',
	    url: '${pageContext.request.contextPath}/news/comments/dc',
	    data: { id : x},
	    success: function( data ) {
	    	document.getElementById('cc-'+x).remove();
	    }
});
}

function hc(x) {
	  var y = document.getElementById("commentc-"+x);
	  var z = document.getElementById("commentcc-"+x);
	  if (y.style.display === "none" && z.style.display === "none") {
	    y.style.display = "block";
	    z.style.display = "block";
	  } else {
	    y.style.display = "none";
	    z.style.display = "none";
	  }
	}
	
function hcs(x) {
	  var ys = document.getElementById("commentcs-"+x);
	  var zs = document.getElementById("commentcss-"+x);
	  if(ys.style.display === "none" && zs.style.display === "none"){
		  ys.style.display = "block";
		  zs.style.display = "block";
	  }else{
		  ys.style.display = "none";
		  zs.style.display = "none";
	  }
	}
	
function hccs(x) {
	  var ycs = document.getElementById("commentcs-"+x);
	  var zcs = document.getElementById("commentcss-"+x);
	  if(ycs.style.display === "none" && zs.style.display === "none"){
		  ycs.style.display = "block";
		  zcs.style.display = "block";
	  }else{
		  ycs.style.display = "none";
		  zcs.style.display = "none";
	  }
	}
	
function hccss(x) {
	  var yccs = document.getElementById("commentccs-"+x);
	  var zccs = document.getElementById("commentccss-"+x);
	  if(yccs.style.display === "none" && zccs.style.display === "none"){
		  yccs.style.display = "block";
		  zccs.style.display = "block";
	  }else{
		  yccs.style.display = "none";
		  zccs.style.display = "none";
	  }
	}
	
function hcc(x) {
	  var y = document.getElementById("commentccc-"+x);
	  var z = document.getElementById("commentcccc-"+x);
	  if (y.style.display === "none" && z.style.display === "none") {
	    y.style.display = "block";
	    z.style.display = "block";
	  } else {
	    y.style.display = "none";
	    z.style.display = "none";
	  }
	}
</script>
	<!-- end logo carousel -->