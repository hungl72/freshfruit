<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/WEB-INF/jsp/components/taglib.jsp" %>
         <div class="page-wrapper">
            <div class="page-breadcrumb">
                <div class="row">
                    <div class="col-5 align-self-center">
                        <h4 class="page-title">Quản lý menu</h4>
                    </div>
                </div>
            </div>
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                    	<div id="data">
                            <div class="col-md-5">
                                <button class="btn btn-primary" onclick="addP()" style="margin-bottom: 1rem;">Thêm danh mục cha</button>
                                <input id="P" class="form-control" value=""/>
                            </div>
                            <div style="margin: 1rem;border: 0.5px solid #7460ee;"></div>
                            <div class="col-md-5">
                                <button class="btn btn-primary" onclick="addC()" style="margin-bottom: 1rem;">Thêm danh mục con</button>
                                <input class="form-control" id="C" value=""/>
                                <div class="form-group" style="margin-top: 1rem;">
                                    <label>Chọn danh mục cha</label>
                                    <c:choose>
                                    	<c:when test="${not empty menuP}">
		                                    <select class="form-select shadow-none col-12" id="S" required="required">
		                                        <c:forEach items="${menuP}" var="m">
		                                        	<option value="${m.menu_id}">${m.menu_name}</option>
		                                        </c:forEach>
		                                    </select>
                                    </c:when>
                                    </c:choose>
                                </div>
                            </div>
                            <div style="margin: 1rem;border: 0.5px solid #7460ee;"></div>
                            <div class="col-md-12">
                                <button class="btn btn-primary" style="margin-bottom: 1rem;">Chọn danh mục muốn xóa ?</button>
                                <div class="form-group col-md-6">
                                    <label>Danh mục cha</label>
                                    <c:choose>
                                    	<c:when test="${not empty menuP}">
                                    	<ul class="dropdown" style="max-height:  150px;overflow: scroll;">
		                                     <c:forEach items="${menuP}" var="p">
		                                        <li class="dropdown-item"><a href="javascript:void(0)" onclick="delP(${p.menu_id})">${p.menu_name}</a></li>
		                                     </c:forEach>
		                                     </ul>
                                    </c:when>
                                    </c:choose>
                                </div>
                                <div class="form-group col-md-6" style="margin-top: 1rem;">
                                    <label>Danh mục con</label>
                                    <c:choose>
                                    	<c:when test="${not empty menuC}">
		                                   <ul class="dropdown" style="max-height:  150px;overflow: scroll;">
		                                     <c:forEach items="${menuC}" var="c">
		                                        <li class="dropdown-item"><a href="javascript:void(0)" onclick="delC(${c.menu_id})">${c.menu_name}</a></li>
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
function addP() {
	var P = document.getElementById("P").value;
	if(P === ""){
		alert('Không được để trống !');
	}else{
		$.ajax({
			url : '${pageContext.request.contextPath}/admin/menu/change/p',
			type : 'post',
			data :{x : P},
			success:function(data){
	        	alert('Thêm danh mục cha thành công !');
	        	document.getElementById("P").value = '';
	        	$("#data").html(data);
	        }
		});
	}
};	

function delP(x) {
	let delP = confirm('Bạn có chắc chắn muốn xóa ?');
	if(delP){
	$.ajax({
		url : '${pageContext.request.contextPath}/admin/menu/del/p',
		type : 'post',
		data :{x : x},
		success:function(data){
        	alert('Xóa danh mục cha thành công !');
        	$("#data").html(data);
        }
	});
	}
};	

function addC() {
	var C = document.getElementById("C").value;
	var S = document.getElementById("S").value;
	if(C === ""){
		alert('Không được để trống !');
	}else{
		$.ajax({
			url : '${pageContext.request.contextPath}/admin/menu/change/c',
			type : 'post',
			data :{x : C, y: S},
			success:function(data){
	        	alert('Thêm danh mục con thành công !');
	        	document.getElementById("C").value = '';
	        	$("#data").html(data);
	        }
		});
	}
};	

function delC(x) {
	let delC = confirm('Bạn có chắc chắn muốn xóa ?');
	if(delC){
		$.ajax({
			url : '${pageContext.request.contextPath}/admin/menu/del/c',
			type : 'post',
			data :{x : x},
			success:function(data){
	        	alert('Xóa danh mục con thành công !');
	        	$("#data").html(data);
	        }
		});
	}
};	
</script>