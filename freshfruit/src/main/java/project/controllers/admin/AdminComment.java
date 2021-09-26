package project.controllers.admin;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import project.constant.URLConstant;
import project.controllers.dao.CommentsChildrenDAO;
import project.controllers.dao.CommentsDAO;
import project.controllers.dao.NewsDAO;
import project.controllers.dao.NewsDetailDAO;
import project.controllers.model.Comment;
import project.controllers.model.CommentChildren;
import project.controllers.service.CommentChildrenService;

@Controller
@RequestMapping(URLConstant.ADMIN)
public class AdminComment {
	
	@Autowired
	private CommentsDAO commentDAO;
	
	@Autowired
	private CommentChildrenService commentChildrenService;
	
	@Autowired
	private CommentsChildrenDAO commentsChildrenDAO;
	
	@Autowired
	private NewsDAO newsDAO;
	
	@Autowired
	private NewsDetailDAO newsDetailDAO;

	@GetMapping(URLConstant.ADMIN_COMMENT_INDEX)
	public String index() {
		return "admin.comment";
	}
	
	@PostMapping(URLConstant.ADMIN_COMMENT_LOAD)
	public String load(Model model, HttpServletRequest request, HttpServletResponse response) {
		try {
				request.setCharacterEncoding("UTF-8");response.setContentType("text/html");response.setCharacterEncoding("UTF-8");
				for(Comment i : commentDAO.newsDetailId()){
		            response.getWriter().print("<div id = \"loadComment\" class=\"col-md-12\" style=\"border: 2px solid orange;margin-bottom: 1rem;padding: 1rem;border-radius: 5px;\">");
		            response.getWriter().print("<h4 style=\"color: sienna;font-weight: bold;\">Tên danh mục : "+newsDAO.news_name(i.getNews_detail_id()).getNews_name()+" --- Tiêu đề bài viết : "+newsDetailDAO.getNewsDetailByNewsDetailId(i.getNews_detail_id()).getNews_detail_name()+"</h4>");
					for(Comment c : commentDAO.findAllCommentByNewsDetailId(i.getNews_detail_id())){
						response.getWriter().print("<div id=\"updateP"+c.getComment_id()+"\" style=\"margin-top: 1rem;\">");
						response.getWriter().print("<h5 id=\"contentParent"+c.getComment_id()+"\" onclick=\"contentParent("+c.getComment_id()+","+c.getComment_id()+",0)\" style=\"border: 2px solid red;border-radius: 5px;padding: 0.5rem;font-weight: 600;\">"+c.getComment_content()+" <span style=\"color: indigo;\"> ---> "+c.getName_user()+" <a href=\"javascript:void(0)\" style=\"float: right;padding: 0 1rem;\" onclick=\"delParent("+c.getComment_id()+")\">Xóa</a></span><i id=\"down\" style=\"float: right;\" class=\"fas fa-caret-square-down\"></i></h5>");
						response.getWriter().print("<ul style=\"display: none;\" id=\"contentChildren"+c.getComment_id()+"\" class=\"list-group\">");
						for(CommentChildren cc : commentsChildrenDAO.findAllCommentChildrennn(c.getComment_id())){
							response.getWriter().print("<li id=\"cc"+cc.getComment_children_id()+"\" class=\"list-group-item\">"+cc.getComment_children_content()+"<span style=\"color: indigo;\"> ---> "+cc.getName_user()+"</span><a href=\"javascript:void(0)\" style=\"float: right;\" onclick=\"delChildren("+cc.getComment_children_id()+")\">Xóa</a></li>");
						}
						response.getWriter().print("</ul></div>");      
					}
					response.getWriter().print("</div>");
		        }
			} catch (Exception e) {
				e.printStackTrace();
			}
		return null;
	}
	
	@PostMapping(URLConstant.ADMIN_COMMENT_DELP)
	public String delP(@RequestParam ("id") Integer id, HttpServletResponse response) {
		commentDAO.del(id);
		try {
		for(Comment i : commentDAO.newsDetailId()){
			for(Comment c : commentDAO.findAllCommentByNewsDetailId(i.getNews_detail_id())){
				if(i.getNews_detail_id() == c.getNews_detail_id()) {
					response.getWriter().print("<h5 id=\"contentParent"+c.getComment_id()+"\" onclick=\"contentParent("+c.getComment_id()+","+c.getComment_id()+",0)\" style=\"border: 2px solid red;border-radius: 5px;padding: 0.5rem;font-weight: 600;\">"+c.getComment_content()+" <span style=\"color: indigo;\"> ---> "+c.getName_user()+" <a href=\"javascript:void(0)\" style=\"float: right;padding: 0 1rem;\" onclick=\"delParent("+c.getComment_id()+")\">Xóa</a></span><i id=\"down\" style=\"float: right;\" class=\"fas fa-caret-square-down\"></i></h5>\r\n" + 
							"\r\n" + 
							"                            <ul style=\"display: none;\" id=\"contentChildrenc.getComment_id()\" class=\"list-group\">");
				}
				for(CommentChildren cc : commentsChildrenDAO.findAllCommentChildrennn(c.getComment_id())){
					response.getWriter().print("<li id=\"cc"+cc.getComment_children_id()+"\" class=\"list-group-item\">"+cc.getComment_children_content()+"<span style=\"color: indigo;\"> ---> "+cc.getName_user()+"</span><a href=\"javascript:void(0)\" style=\"float: right;\" onclick=\"delChildren("+cc.getComment_children_id()+")\">Xóa</a></li></ul>");
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@PostMapping(URLConstant.ADMIN_COMMENT_DELC)
	public String delC(@RequestParam ("id") Integer id, HttpServletResponse response) {
		commentChildrenService.del(id);
		try {
			response.getWriter().print("");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
