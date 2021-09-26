<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/WEB-INF/jsp/components/taglib.jsp" %>
         <div class="page-wrapper">
            <div class="page-breadcrumb">
                <div class="row">
                    <div class="col-5 align-self-center">
                        <h4 class="page-title">Quản lý danh mục tin tức</h4>
                    </div>
                    <div class="col-7 align-self-center">
                    </div>
                </div>
            </div>
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                    	<div id="data">
                            <div class="col-md-5">
                                <button class="btn btn-primary" onclick="add()" style="margin-bottom: 1rem;">Thêm danh mục</button>
                                <input id="N" class="form-control" value=""/>
                            </div>
                            <div style="margin: 1rem;border: 0.5px solid #7460ee;"></div>
                            <div class="col-md-12">
                                <button class="btn btn-primary" style="margin-bottom: 1rem;">Chọn danh mục muốn xóa ?</button>
                                <div class="form-group col-md-6">
                                    <label>Danh mục</label>
                                    <c:choose>
                                    	<c:when test="${not empty news}">
                                    	<ul class="dropdown" style="max-height:  150px;overflow: scroll;">
		                                     <c:forEach items="${news}" var="n">
		                                        <li class="dropdown-item"><a href="javascript:void(0)" onclick="del(${n.news_id})">${n.news_name}</a></li>
		                                     </c:forEach>
		                                     </ul>
                                    </c:when>
                                    </c:choose>
                                </div>
                            </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <script>
function add() {
	var N = document.getElementById("N").value;
	if(N === ""){
		alert('Không được để trống !');
	}else{
		$.ajax({
			url : '${pageContext.request.contextPath}/admin/news/add',
			type : 'post',
			data :{x : N},
			success:function(data){
	        	alert('Thêm danh mục thành công !');
	        	document.getElementById("N").value = '';
	        	$("#data").html(data);
	        }
		});
	}
};	

function del(x) {
	let del = confirm('Bạn có chắc chắn muốn xóa ?');
	if(del){
	$.ajax({
		url : '${pageContext.request.contextPath}/admin/news/del',
		type : 'post',
		data :{x : x},
		success:function(data){
        	alert('Xóa danh mục thành công !');
        	$("#data").html(data);
        }
	});
	}
};	
</script>