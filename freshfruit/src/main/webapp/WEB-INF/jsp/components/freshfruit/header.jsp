<%@page import="project.controllers.model.Cart"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.io.IOException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="project.util.DBConnectionUtil"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@include file="/WEB-INF/jsp/components/taglib.jsp" %> 
   	<%
	   	Cart cart = (Cart)session.getAttribute("cart");
		if(cart == null){
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
	%>
    <div class="loader">
        <div class="loader-inner">
            <div class="circle"></div>
        </div>
    </div>
	<div class="top-header-area" id="sticker">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 col-sm-12 text-center">
					<div class="main-menu-wrap">
						<!-- logo -->
						<div class="site-logo">
							<a href="index.html">
								<img src="${contextPath}/img/logo.png" alt="">
							</a>
						</div>
						<nav class="main-menu">
							<ul>
								<li class="current-list-item"><a href="${pageContext.request.contextPath}/index">Trang chủ</a></li>
								<li><a href="javascript:void(0)">Tin tức</a>
									<ul class="sub-menu">
									<c:choose>
										<c:when test="${not empty listNews}">
											<c:forEach items="${listNews}" var="l">
												<li><a href="${pageContext.request.contextPath}/news/${l.news_id}/page/1">${l.news_name}</a></li>
											</c:forEach>
										</c:when>
									</c:choose>
									<li></li>
									</ul>
								</li>
								<li><a href="javascript:void(0)">Hoa quả</a>
									<ul class="sub-menu">
									<%!
			public void  CreateChildMenuu(int parentId, javax.servlet.jsp.JspWriter out){
			        try{
			            List<Integer> idChildMenuu=new ArrayList<Integer>();
			            List<String> nameChildMenuu=new ArrayList<String>();
			            try{
			            	Connection con11 = DBConnectionUtil.getConnection();
				    		Statement st11 = con11.createStatement();
				    		ResultSet rs11 = st11.executeQuery("SELECT * FROM menu");
			                while(rs11.next())
			                {
			                    if(rs11.getInt("parent_id") == (parentId))
			                    {
			                        idChildMenuu.add(rs11.getInt("menu_id"));
			                        nameChildMenuu.add(rs11.getString("menu_name"));
			                    }
			                }
			                if(idChildMenuu.size()>0)
			                {
			                    
			                    for(int i=0;i<idChildMenuu.size();i++)
			                    {
			                       out.println("<li style = "+'"'+"padding-left: 1rem;margin-top: -1rem;"+'"'+">"+"<a href="+"/freshfruit/product/"+idChildMenuu.get(i)+"/page/1"+">"+nameChildMenuu.get(i)+"</a>"+"");
			                       CreateChildMenuu(idChildMenuu.get(i),out);
			                    }
			                }
			                else{
			                    out.println("</li>");
			                }
			            }catch(IOException ex){}
			            }catch (SQLException e){}}        
			        public void  CreateMenuu(javax.servlet.jsp.JspWriter out){
			            try{
			            	Connection conn = DBConnectionUtil.getConnection();
			    			Statement stt = conn.createStatement();
			    			ResultSet rss = stt.executeQuery("SELECT * FROM menu"); 
			                try
			                {
			                    while(rss.next())
			                    {
			                        if(rss.getInt("parent_id") == 0) 
			                        {
			                            out.println("<li>");
			                            out.println("<a href="+"javascript:void(0)"+">"+rss.getString("menu_name")+"</a>");
			                            CreateChildMenuu(rss.getInt("menu_id"),out);
			                        }                  
			                    }
			                    out.println("</a></li>");
			                } catch(IOException ex){}
			            }
			            catch (SQLException e){}
			        }
			%>
			<%CreateMenuu(out); %>
											</ul>
										</li>
										<c:if test="${not empty sessionScope.user}">
											<li><a href="${pageContext.request.contextPath}/bill">Hóa đơn</a></li>
											<li><a href="${pageContext.request.contextPath}/contact">Liên hệ</a></li>
										</c:if>
										<c:choose>
											<c:when test="${not empty sessionScope.user}">
												<li><a href="${pageContext.request.contextPath}/profile/${sessionScope.user.id}">Chào, ${sessionScope.user.username}</a></li>
												<li><a href="${pageContext.request.contextPath}/auth/logout">Đăng xuất</a></li>
											</c:when>
											<c:otherwise>
												<li><a href="${pageContext.request.contextPath}/auth/login">Đăng nhập</a></li>
												<li><a href="${pageContext.request.contextPath}/auth/signup">Đăng ký</a></li>
											</c:otherwise>
										</c:choose>
								<li>
									<div class="header-icons">
										<a class="shopping-cart" href="${pageContext.request.contextPath}/cart"><i class="fas fa-shopping-cart"></i></a>
										<a class="mobile-hide search-bar-icon" href="#"><i class="fas fa-search"></i></a>
									</div>
								</li>
							</ul>
						</nav>
						<a class="mobile-show search-bar-icon" href="#"><i class="fas fa-search"></i></a>
						<div class="mobile-menu"></div>
						<!-- menu end -->
					</div>
				</div>
			</div>
		</div>
	</div>
	    <div class="search-area">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<span class="close-btn"><i class="fas fa-window-close"></i></span>
					<div class="search-bar">
						<div class="search-bar-tablecell">
							<h3>Search For:</h3>
								<form method="get" action="${pageContext.request.contextPath}/search">
								<input type="text" placeholder="Bạn tìm gì ... " name="search" id="srch-term">
								<h4 style="color: white;">Sản phẩm</h4><input type="radio" name="query" value="product" id="products" >
								<h4 style="color: white;">Bài viết</h4><input type="radio" name="query" value="news" id="news" checked>
								<input hidden="" name="page" value="1" >
								<button onclick="return checkValidate()" type="submit">Search <i class="fas fa-search"></i></button>
								</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<script>
	document.getElementById("news").onclick = function () {
	    document.getElementById("news").checked = true;
	    document.getElementById("products").checked = false;
	};
	document.getElementById("products").onclick = function () {
	    document.getElementById("products").checked = true;
	    document.getElementById("news").checked = false;
	};
	function checkValidate(){
		if(document.getElementById("srch-term").value == ""){
			alert("Nhập nội dung bạn muốn tìm !");
			return false;
		}else{
			return true;
		}
	}
</script>