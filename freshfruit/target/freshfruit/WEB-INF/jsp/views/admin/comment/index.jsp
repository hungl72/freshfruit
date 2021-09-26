<%@page import="project.controllers.model.News"%>
<%@page import="project.controllers.dao.NewsDAO"%>
<%@page import="project.controllers.dao.CommentsChildrenDAO"%>
<%@page import="project.controllers.model.CommentChildren"%>
<%@page import="project.controllers.model.NewsDetail"%>
<%@page import="project.controllers.dao.MenuDAO"%>
<%@page import="project.controllers.service.NewsDetailService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="project.controllers.model.Comment"%>
<%@page import="java.util.List"%>
<%@page import="project.controllers.dao.NewsDetailDAO"%>
<%@page import="project.controllers.dao.CommentsDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/WEB-INF/jsp/components/taglib.jsp" %>
    
     <c:url value="/resources/img" var="urlUpload"/>
        <div class="page-wrapper">
            <div class="page-breadcrumb">
                <div class="row">
                    <div class="col-5 align-self-center">
                        <h4 class="page-title">Quản lý bình luận</h4>
                    </div>
                    <div class="col-7 align-self-center">
                    </div>
                </div>
            </div>
            <div class="container-fluid">
	            <c:if test="${not empty msg}">
					<div class="alert alert-primary" role="alert">${msg}</div>
				</c:if>
               <div id="fcmt" class="row" style="margin-bottom: 1rem;"></div>   
            </div>
         </div>
<script>
function contentParent(x,y,z){
    $('#contentChildren'+y).slideToggle();
    if(z == 1){
        $('#contentParent'+y).attr('onclick','contentParent('+x+','+y+',0)');
        $('#contentParent'+y).css("border","2px solid red");
	        if($('#down'+y).attr('class') == 'far fa-caret-square-down'){
	            $('#down'+y).attr('class','fas fa-caret-square-down');
	        }else{
	            $('#down'+y).attr('class','far fa-caret-square-down');
	        }
    }else{
        $('#contentParent'+y).attr('onclick','contentParent('+x+','+y+',1)');
        $('#contentParent'+y).css("border","none");
	        if($('#down'+y).attr('class') == 'far fa-caret-square-down'){
	            $('#down'+y).attr('class','fas fa-caret-square-down');
	        }else{
	            $('#down'+y).attr('class','far fa-caret-square-down');
	        }
    }
}
window.onload = function(){
	$.ajax({
	    type: 'POST',
	    url: '${pageContext.request.contextPath}/admin/comment/load',
	    data: {},
	    success: function( data ) {
	        $( "#fcmt" ).html(data);
	    }
});
}
function delParent(x,i){
	$.ajax({
		url: '${pageContext.request.contextPath}/admin/comment/delP',
		method: 'POST',
		data: {id : x},
		success:function(data){
			$('#updateP'+x).remove();
		}
	});
}

function delChildren(x){
	$.ajax({
		url: '${pageContext.request.contextPath}/admin/comment/delC',
		method: 'POST',
		data: {id : x},
		success:function(data){
			$('#cc'+x).remove();
		}
	});
}
</script>