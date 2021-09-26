package project.controllers.freshfruit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import project.constant.URLConstant;
import project.controllers.dao.CommentsChildrenDAO;
import project.controllers.dao.CommentsDAO;
import project.controllers.model.Comment;
import project.controllers.model.CommentChildren;
import project.controllers.model.NewsDetail;
import project.controllers.model.User;
import project.controllers.service.CommentService;
import project.controllers.service.NewsDetailService;
import project.controllers.service.NewsService;
import project.controllers.service.UserService;

@Controller
public class FreshFruitDetailController {
	
	@Autowired
	private NewsService newsService;
	
	@Autowired
	private CommentsDAO commentsDAO;
	
	@Autowired
	private CommentsChildrenDAO commentsChildrenDAO;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private NewsDetailService detailService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping(URLConstant.FRESH_FRUIT_DETAIL)
	public String index(Model model, @PathVariable Integer news_id, @PathVariable Integer news_detail_id) {
		List<Comment> listComments = new ArrayList<Comment>();
		listComments = commentService.getAll();
		NewsDetail detail = new NewsDetail();
		detail = detailService.findByIdId(news_id, news_detail_id);
		model.addAttribute("listComments", listComments);
		model.addAttribute("detail", detail);
		model.addAttribute("getNameNews", newsService.findById(detail.getNews_id()).getNews_name());
		model.addAttribute("listNews", newsService.getAll());
		return "freshfruit.detail";
	}
	
	@PostMapping(URLConstant.FRESH_FRUIT_DETAIL_COMMENT_FULL_LOAD)
	public void fullLoad(@RequestParam int news_detail_id, HttpServletRequest request,HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");response.setContentType("text/html");response.setCharacterEncoding("UTF-8");
			if(commentService.getAll(news_detail_id) != null && commentService.getAll(news_detail_id).size() > 0) {
					List<Comment> listComment = commentService.getAll(news_detail_id);
					response.getWriter().print(
																	"<div class=\"comments-list-wrap\">\r\n" + 
																	"<h4 class=\"comment-count-title\">Bình luận</h4>\r\n"
																 );
					for(Comment c : listComment) {
						response.getWriter().print(
																		"<div class=\"comment-list\">" +
																		"<div id=\"p-"+c.getComment_id()+"\" class=\"single-comment-body\">\r\n" + 
																		"<div class=\"comment-user-avater\">\r\n" + 
																		"<img src=\"/freshfruit/resources/img/"+userService.findById(c.getId_user()).getImage()+"\" alt=\"\">\r\n" + 
																		"</div>\r\n" + 
																		"<div class=\"comment-text-body\">\r\n");
						try {
						User user = (User)request.getSession().getAttribute("user");
							if(user.getId() == c.getId_user()) {
								response.getWriter().print("<h4>"+c.getName_user()+" <span class=\"comment-date\">"+c.getComment_datecreated()+"</span> <a href=\"javascript:void(0)\" onclick=\"hc("+c.getComment_id()+")\"\">Trả lời</a><a href=\"javascript:void(0)\" onclick=\"hcs("+c.getComment_id()+")\"\">Sửa</a><a href=\"javascript:void(0)\" onclick=\"delP("+c.getComment_id()+")\"\">Xóa</a></h4>\r\n");
								response.getWriter().print("<p id=\"updateP"+c.getComment_id()+"\">"+c.getComment_content()+"</p>\r\n" +
										"<textarea id=\"commentcs-"+c.getComment_id()+"\" class=\"form-control\" style=\"padding-left: 0.2rem;margin-bottom: 0.5rem;display: none;\" >"+c.getComment_content()+"</textarea>\r\n" + 
										"<button id=\"commentcss-"+c.getComment_id()+"\" onclick=\"updateP('"+c.getComment_id()+"')\" class=\"btn btn-primary\" style = \"display: none;\">Sửa</button>" +
										"<textarea id=\"commentc-"+c.getComment_id()+"\" class=\"form-control\" style=\"padding-left: 0.2rem;margin-bottom: 0.5rem;display: none;\" >@"+c.getName_user()+"</textarea>\r\n" + 
										"<button id=\"commentcc-"+c.getComment_id()+"\" onclick=\"rp('"+c.getComment_id()+"')\" class=\"btn btn-primary\" style = \"display: none;\">Phản hồi</button>" +
										"</div>\r\n"
									  ); 
							}else {
								response.getWriter().print("<h4>"+c.getName_user()+" <span class=\"comment-date\">"+c.getComment_datecreated()+"</span> <a href=\"javascript:void(0)\" onclick=\"hc("+c.getComment_id()+")\"\">Trả lời</a></h4>\r\n");
								response.getWriter().print("<p id=\"updateP"+c.getComment_id()+"\">"+c.getComment_content()+"</p>\r\n" +
										"<textarea id=\"commentcs-"+c.getComment_id()+"\" class=\"form-control\" style=\"padding-left: 0.2rem;margin-bottom: 0.5rem;display: none;\" >"+c.getComment_content()+"</textarea>\r\n" + 
										"<button id=\"commentcss-"+c.getComment_id()+"\" onclick=\"updateP('"+c.getComment_id()+"')\" class=\"btn btn-primary\" style = \"display: none;\">Sửa</button>" +
										"<textarea id=\"commentc-"+c.getComment_id()+"\" class=\"form-control\" style=\"padding-left: 0.2rem;margin-bottom: 0.5rem;display: none;\" >@"+c.getName_user()+"</textarea>\r\n" + 
										"<button id=\"commentcc-"+c.getComment_id()+"\" onclick=\"rp('"+c.getComment_id()+"')\" class=\"btn btn-primary\" style = \"display: none;\">Phản hồi</button>" +
										"</div>\r\n"
									  ); 
							}
						}catch(Exception e) {
							response.getWriter().print("<h4>"+c.getName_user()+" <span class=\"comment-date\">"+c.getComment_datecreated()+"</span></h4>\r\n");
							response.getWriter().print("<p id=\"updateP"+c.getComment_id()+"\">"+c.getComment_content()+"</p>\r\n" +
									"<textarea id=\"commentcs-"+c.getComment_id()+"\" class=\"form-control\" style=\"padding-left: 0.2rem;margin-bottom: 0.5rem;display: none;\" >"+c.getComment_content()+"</textarea>\r\n" + 
									"<button id=\"commentcss-"+c.getComment_id()+"\" onclick=\"updateP('"+c.getComment_id()+"')\" class=\"btn btn-primary\" style = \"display: none;\">Sửa</button>" +
									"<textarea id=\"commentc-"+c.getComment_id()+"\" class=\"form-control\" style=\"padding-left: 0.2rem;margin-bottom: 0.5rem;display: none;\" >@"+c.getName_user()+"</textarea>\r\n" + 
									"<button id=\"commentcc-"+c.getComment_id()+"\" onclick=\"rp('"+c.getComment_id()+"')\" class=\"btn btn-primary\" style = \"display: none;\">Phản hồi</button>" +
									"</div>\r\n"
								  ); 
						}
						
			if(commentsChildrenDAO.getAll(c.getComment_id()) != null) {
				response.getWriter().print("<div id="+c.getComment_id()+">");
				for(CommentChildren cc : commentsChildrenDAO.getAll(c.getComment_id())) {
						response.getWriter().print(
																		"<div id=\"cc-"+cc.getComment_children_id()+"\" class=\"single-comment-body child\">\r\n" + 
																		"<div class=\"comment-user-avater\">\r\n" + 
																		"<img src=\"/freshfruit/resources/img/"+userService.findById(cc.getId_user()).getImage()+"\" alt=\"\">\r\n" + 
																		"</div>\r\n" + 
																		"<div class=\"comment-text-body\">\r\n");
						try {
								User user = (User)request.getSession().getAttribute("user");
								if(user.getId() == cc.getId_user()) {
									response.getWriter().print("<h4>"+cc.getName_user()+" <span class=\"comment-date\">"+cc.getComment_children_datecreated()+"</span> <a href=\"javascript:void(0)\" onclick=\"hcc("+cc.getComment_children_id()+")\">Trả lời</a><a href=\"javascript:void(0)\" onclick=\"hccss("+cc.getComment_children_id()+")\">Sửa</a><a href=\"javascript:void(0)\" onclick=\"delC("+cc.getComment_children_id()+")\">Xóa</a></h4>\r\n");
									response.getWriter().print("<p id=\"updateC"+cc.getComment_children_id()+"\">"+cc.getComment_children_content()+"</p>\r\n" + 
											"<textarea id=\"commentccs-"+cc.getComment_children_id()+"\" class=\"form-control\" style=\"padding-left: 0.2rem;margin-bottom: 0.5rem;display: none;\" >"+cc.getComment_children_content()+"</textarea>\r\n" + 
											"<button id=\"commentccss-"+cc.getComment_children_id()+"\" onclick=\"updateC('"+cc.getComment_children_id()+"')\" class=\"btn btn-primary\" style = \"display: none;\">Sửa</button>" +
											"<textarea id=\"commentccc-"+cc.getComment_children_id()+"\" class=\"form-control\" style=\"padding-left: 0.2rem;margin-bottom: 0.5rem;display: none;\" >@"+cc.getName_user()+"</textarea>\r\n" + 
											"<button id=\"commentcccc-"+cc.getComment_children_id()+"\" onclick=\"rpc('"+c.getComment_id()+","+cc.getComment_children_id()+"')\" class=\"btn btn-primary\" style=\"display: none;\">Phản hồi</button>" +
											"</div>\r\n" + 
											"</div>"
										  );
								}else {
									response.getWriter().print("<h4>"+cc.getName_user()+" <span class=\"comment-date\">"+cc.getComment_children_datecreated()+"</span> <a href=\"javascript:void(0)\" onclick=\"hcc("+cc.getComment_children_id()+")\">Trả lời</a></h4>\r\n");
									response.getWriter().print("<p id=\"updateC"+cc.getComment_children_id()+"\">"+cc.getComment_children_content()+"</p>\r\n" + 
											"<textarea id=\"commentccs-"+cc.getComment_children_id()+"\" class=\"form-control\" style=\"padding-left: 0.2rem;margin-bottom: 0.5rem;display: none;\" >"+cc.getComment_children_content()+"</textarea>\r\n" + 
											"<button id=\"commentccss-"+cc.getComment_children_id()+"\" onclick=\"updateC('"+cc.getComment_children_id()+"')\" class=\"btn btn-primary\" style = \"display: none;\">Sửa</button>" +
											"<textarea id=\"commentccc-"+cc.getComment_children_id()+"\" class=\"form-control\" style=\"padding-left: 0.2rem;margin-bottom: 0.5rem;display: none;\" >@"+cc.getName_user()+"</textarea>\r\n" + 
											"<button id=\"commentcccc-"+cc.getComment_children_id()+"\" onclick=\"rpc('"+c.getComment_id()+","+cc.getComment_children_id()+"')\" class=\"btn btn-primary\" style=\"display: none;\">Phản hồi</button>" +
											"</div>\r\n" + 
											"</div>"
										  );
								}
							}catch(Exception e) {
								response.getWriter().print("<h4>"+cc.getName_user()+" <span class=\"comment-date\">"+cc.getComment_children_datecreated()+"</span></h4>\r\n");
								response.getWriter().print("<p id=\"updateC"+cc.getComment_children_id()+"\">"+cc.getComment_children_content()+"</p>\r\n" + 
										"<textarea id=\"commentccs-"+cc.getComment_children_id()+"\" class=\"form-control\" style=\"padding-left: 0.2rem;margin-bottom: 0.5rem;display: none;\" >"+cc.getComment_children_content()+"</textarea>\r\n" + 
										"<button id=\"commentccss-"+cc.getComment_children_id()+"\" onclick=\"updateC('"+cc.getComment_children_id()+"')\" class=\"btn btn-primary\" style = \"display: none;\">Sửa</button>" +
										"<textarea id=\"commentccc-"+cc.getComment_children_id()+"\" class=\"form-control\" style=\"padding-left: 0.2rem;margin-bottom: 0.5rem;display: none;\" >@"+cc.getName_user()+"</textarea>\r\n" + 
										"<button id=\"commentcccc-"+cc.getComment_children_id()+"\" onclick=\"rpc('"+c.getComment_id()+","+cc.getComment_children_id()+"')\" class=\"btn btn-primary\" style=\"display: none;\">Phản hồi</button>" +
										"</div>\r\n" + 
										"</div>"
									  );
							}
						
				}
				response.getWriter().print("</div>");
			}
						response.getWriter().print(
																		"<c:set var=\"idp\" value=\""+c.getComment_id()+"\" />\r\n" + 
																		"</div>\r\n" + 
																		"\r\n"
																	 );
				}
						response.getWriter().print(
								"<div id="+"pcmt" +"></div>" +
								"</div>"
							 );
			}else {
				response.getWriter().print("<div id=\"pcmt\">");
				response.getWriter().print(
																"<div class=\"comments-list-wrap\">\r\n" + 
																"<h4 id =\"z\" class=\"comment-count-title\">Không có Phản hồi nào !</h4>\r\n" +
																"</div>"
															 );
				response.getWriter().print("</div>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@PostMapping(URLConstant.FRESH_FRUIT_DETAIL_COMMENT_INSERT_PARENT)
	public void comment (@RequestParam int id, @RequestParam String name, @RequestParam String content, @RequestParam int detail_id, HttpSession session, HttpServletRequest request,HttpServletResponse response) {
			User user = (User)request.getSession().getAttribute("user");
			commentsDAO.insert(user.getId(), name, content, detail_id);
		try {
			request.setCharacterEncoding("UTF-8");response.setContentType("text/html");response.setCharacterEncoding("UTF-8");
			if(commentService.findById(commentsDAO.totalRow()) != null) {
				Comment c = commentService.findById(commentsDAO.totalRow());
				response.getWriter().print(
																"<div class=\"comment-list\">" +
																"<div id=\"p-"+c.getComment_id()+"\" class=\"single-comment-body\">\r\n" + 
																"<div class=\"comment-user-avater\">\r\n" + 
																"<img src=\"/freshfruit/resources/img/"+userService.findOne(user).getImage()+"\" alt=\"\">\r\n" + 
																"</div>\r\n" + 
																"<div class=\"comment-text-body\">\r\n" + 
																"<h4>"+c.getName_user()+" <span class=\"comment-date\">"+c.getComment_datecreated()+"</span> <a href=\"javascript:void(0)\" onclick=\"hc("+c.getComment_id()+")\"\">Trả lời</a><a href=\"javascript:void(0)\" onclick=\"hcs("+c.getComment_id()+")\"\">Sửa</a><a href=\"javascript:void(0)\" onclick=\"delP("+c.getComment_id()+")\"\">Xóa</a></h4>\r\n" + 
																"<p id=\"updateP"+c.getComment_id()+"\">"+c.getComment_content()+"</p>\r\n" +
																"<textarea id=\"commentcs-"+c.getComment_id()+"\" class=\"form-control\" style=\"padding-left: 0.2rem;margin-bottom: 0.5rem;display: none;\">"+c.getComment_content()+"</textarea>\r\n" + 
																"<button id=\"commentcss-"+c.getComment_id()+"\" onclick=\"updateP('"+c.getComment_id()+"')\" class=\"btn btn-primary\" style = \"display: none;\">Sửa</button>" +
																"<textarea id=\"commentc-"+c.getComment_id()+"\" class=\"form-control\" style=\"padding-left: 0.2rem;margin-bottom: 0.5rem;display: none;\" >@"+c.getName_user()+"</textarea>\r\n" + 
																"<button id=\"commentcc-"+c.getComment_id()+"\" onclick=\"rp('"+c.getComment_id()+"')\" class=\"btn btn-primary\" style=\"display: none;\">Phản hồi</button>" +
																"</div>\r\n" +
																"<div id="+c.getComment_id()+"></div>" +
																"<c:set var=\"idp\" value=\""+c.getComment_id() + "\" />\r\n" + 
																"\r\n" + 
																"\r\n" + 
																"</div>"
															  );
				response.getWriter().print(
						"<div id="+"pcmt" +"></div>" +
						"</div>"
					 );
				}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@PostMapping(URLConstant.FRESH_FRUIT_DETAIL_COMMENT_UPDATE_PARENT)
	public void updateP(@RequestParam Integer id, @RequestParam String cmt, HttpServletRequest request, HttpServletResponse response) {
		commentsDAO.updateCommentP(cmt,id);
		try {
			response.getWriter().print("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@PostMapping(URLConstant.FRESH_FRUIT_DETAIL_COMMENT_DELETE_PARENT)
	public void delP(@RequestParam Integer id, HttpServletRequest request, HttpServletResponse response) {
		commentService.delCommentP(id);
		try {
			response.getWriter().print("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@PostMapping(URLConstant.FRESH_FRUIT_DETAIL_COMMENT_INSERT_CHILDREN)
	public void commentchildren(@RequestParam int idc, @RequestParam int id, @RequestParam String name, @RequestParam String content, @RequestParam int detail_id, HttpServletRequest request,HttpServletResponse response) {
		User user = (User)request.getSession().getAttribute("user");
		commentsChildrenDAO.insert(idc, id, name, content, detail_id);
		try {
			request.setCharacterEncoding("UTF-8");response.setContentType("text/html");response.setCharacterEncoding("UTF-8");
			if(commentsChildrenDAO.getAll(idc) != null) {
				for(CommentChildren cc : commentsChildrenDAO.getAll(idc)) {
					if(user.getId() == cc.getId_user()) {
						response.getWriter().print(
								"<div id=\"cc-"+cc.getComment_children_id()+"\" class=\"single-comment-body child\">\r\n" + 
								"<div class=\"comment-user-avater\">\r\n" + 
								"<img src=\"/freshfruit/resources/img/"+userService.findById(cc.getId_user()).getImage()+"\" alt=\"\">\r\n" + 
								"</div>\r\n" + 
								"<div class=\"comment-text-body\">\r\n" + 
								"<h4>"+cc.getName_user()+" <span class=\"comment-date\">"+cc.getComment_children_datecreated()+"</span> <a href=\"javascript:void(0)\" onclick=\"hcc("+cc.getComment_children_id()+")\">Trả lời</a><a href=\"javascript:void(0)\" onclick=\"hccss("+cc.getComment_children_id()+")\">Sửa</a><a href=\"javascript:void(0)\" onclick=\"delC("+cc.getComment_children_id()+")\">Xóa</a></h4>\r\n" + 
								"<p id=\"updateC"+cc.getComment_children_id()+"\">"+cc.getComment_children_content()+"</p>\r\n" + 
								"<textarea id=\"commentccs-"+cc.getComment_children_id()+"\" class=\"form-control\" style=\"padding-left: 0.2rem;margin-bottom: 0.5rem;display: none;\" >"+cc.getComment_children_content()+"</textarea>\r\n" + 
								"<button id=\"commentccss-"+cc.getComment_children_id()+"\" onclick=\"updateC('"+cc.getComment_children_id()+"')\" class=\"btn btn-primary\" style = \"display: none;\">Sửa</button>" +
								"<textarea id=\"commentccc-"+cc.getComment_children_id()+"\" class=\"form-control\" style=\"padding-left: 0.2rem;margin-bottom: 0.5rem;display: none;\" >@"+cc.getName_user()+"</textarea>\r\n" + 
								"<button id=\"commentcccc-"+cc.getComment_children_id()+"\" onclick=\"rpc('"+cc.getComment_id()+","+cc.getComment_children_id()+"')\" class=\"btn btn-primary\" style=\"display:none;\">Phản hồi</button>" +
								"</div>\r\n" + 
								"</div>"
							 );
					}else {
						response.getWriter().print(
								"<div id=\"cc-"+cc.getComment_children_id()+"\" class=\"single-comment-body child\">\r\n" + 
								"<div class=\"comment-user-avater\">\r\n" + 
								"<img src=\"/freshfruit/resources/img/"+userService.findById(cc.getId_user()).getImage()+"\" alt=\"\">\r\n" + 
								"</div>\r\n" + 
								"<div class=\"comment-text-body\">\r\n" + 
								"<h4>"+cc.getName_user()+" <span class=\"comment-date\">"+cc.getComment_children_datecreated()+"</span> <a href=\"javascript:void(0)\" onclick=\"hcc("+cc.getComment_children_id()+")\">Trả lời</a></h4>\r\n" + 
								"<p>"+cc.getComment_children_content()+"</p>\r\n" + 
								"<textarea id=\"commentccc-"+cc.getComment_children_id()+"\" class=\"form-control\" style=\"padding-left: 0.2rem;margin-bottom: 0.5rem;display: none;\" >@"+cc.getName_user()+"</textarea>\r\n" + 
								"<button id=\"commentcccc-"+cc.getComment_children_id()+"\" onclick=\"rpc('"+cc.getComment_id()+","+cc.getComment_children_id()+"')\" class=\"btn btn-primary\" style=\"display:none;\">Phản hồi</button>" +
								"</div>\r\n" + 
								"</div>"
							 );
					}
				}
			}
			response.getWriter().print("");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@PostMapping(URLConstant.FRESH_FRUIT_DETAIL_COMMENT_UPDATE_CHILDREN)
	public void updateC(@RequestParam Integer id, @RequestParam String cmt, HttpServletRequest request, HttpServletResponse response) {
		commentsChildrenDAO.updateCommentC(cmt,id);
		try {
			response.getWriter().print("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@PostMapping(URLConstant.FRESH_FRUIT_DETAIL_COMMENT_DELETE_CHILDREN)
	public void delC(@RequestParam Integer id, HttpServletRequest request, HttpServletResponse response) {
		commentsChildrenDAO.delCommentC(id);
		try {
			response.getWriter().print("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
