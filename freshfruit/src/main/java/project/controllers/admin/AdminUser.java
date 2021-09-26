package project.controllers.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import project.constant.GlobalConstant;
import project.constant.URLConstant;
import project.controllers.model.User;
import project.controllers.service.UserService;
import project.util.PageUtil;

@Controller
@RequestMapping(URLConstant.ADMIN)
public class AdminUser {

	@Autowired
	private UserService userService;
	
	@GetMapping(URLConstant.ADMIN_USER)
	public String index(Model model, @PathVariable Integer page) {
		model.addAttribute("listUser", userService.getAll());
		if (page == null) {
			page = 1;
		}
		int offset = PageUtil.getOffset(page);
		List<User> listUser = userService.pagination(offset, GlobalConstant.TOTAL_ROW);
		if(listUser == null || listUser.size() == 0) {
			model.addAttribute("admin404", "admin404");
			return "error404";
		}else {
			model.addAttribute("currentPage", page);
			model.addAttribute("totalPage", PageUtil.getTotalpage(userService.totalRow()));
			model.addAttribute("listUser", listUser);
			return "admin.user";
		}
	}
	
	@PostMapping(URLConstant.ADMIN_USER_STATUS)
	public String status(@RequestParam Integer page, @RequestParam Integer id, HttpServletRequest request, HttpServletResponse response) {
		int offset = (page -1) * 3;
		int status = userService.checkStatus(id).getEnabled();
		try {
			request.setCharacterEncoding("UTF-8");response.setContentType("text/html");response.setCharacterEncoding("UTF-8");
			if(status == 1) {
				userService.updateStatus(0, id);
				loadChange(id, request, response);
			}else {
				userService.updateStatus(1, id);
				loadChange(id, request, response);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		try {
			request.setCharacterEncoding("UTF-8");response.setContentType("text/html");response.setCharacterEncoding("UTF-8");
			response.getWriter().print("");
			if(userService.getAll(offset) == null || userService.getAll(offset).size() == 0) {
				response.getWriter().print("<td><h2>Page này đã bị xóa hết !</h2></td>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@PostMapping(URLConstant.ADMIN_USER_DEL)
	public String del(@RequestParam Integer page, @RequestParam Integer id, HttpServletRequest request, HttpServletResponse response) {
		int offset = (page -1) * 3;
		userService.del(id);
		try {
			request.setCharacterEncoding("UTF-8");response.setContentType("text/html");response.setCharacterEncoding("UTF-8");
			response.getWriter().print("");
			if(userService.getAll(offset) == null || userService.getAll(offset).size() == 0) {
				response.getWriter().print("<td><h2>Page này đã bị xóa hết !</h2></td>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void loadChange(int id, HttpServletRequest request, HttpServletResponse response) {
		try {
		User u = userService.findById(id);
		response.getWriter().print(
														"<td>"+u.getId()+"</td>\r\n" + 
														"<td>"+u.getUsername()+"</td>\r\n" + 
														"<td>"+u.getFullname()+"</td>\r\n" + 
														"<td>"+u.getInformation()+"</td>\r\n" + 
														"<td><img src=\"/freshfruit/resources/img/"+u.getImage()+"\" style=\"width: 200px;height: 150px;\"/></td>\r\n"
													 );
			if(u.getRole_id() == 1) {
				response.getWriter().print("<td>Admin</td>");
			}else if(u.getRole_id() == 2) {
				response.getWriter().print("<td>Moderator</td>");
			}else {
				response.getWriter().print("<td>User</td>");
			}
			if(u.getEnabled() == 1 && u.getRole_id() == 1) {
				response.getWriter().print("<td></td><td></td>");
			}
			if(u.getEnabled() == 1 && u.getRole_id() == 2) {
				request.setCharacterEncoding("UTF-8");response.setContentType("text/html");response.setCharacterEncoding("UTF-8");
				response.getWriter().print(
																"<td width=\"14%\"><button class=\"btn btn-primary\">Đang hoạt động</button></td>\r\n" + 
																"<td width=\"15%\">\r\n" + 
																"<a href=\"javascript:void(0)\" onclick=\"status("+u.getId()+")\"><button class=\"btn btn-danger\">Khóa</button></a>\r\n" + 
																"<a href=\"javascript:void(0)\" onclick=\"del('"+u.getId()+"')\"><button class=\"btn btn-danger\">Xóa</button></a>\r\n" + 
																"</td>"
															);
			}
			if(u.getEnabled() == 1 && u.getRole_id() == 3) {
				request.setCharacterEncoding("UTF-8");response.setContentType("text/html");response.setCharacterEncoding("UTF-8");
				response.getWriter().print(
																"<td width=\"14%\"><button class=\"btn btn-primary\">Đang hoạt động</button></td>\r\n" + 
																"<td width=\"15%\">\r\n" + 
																"<a href=\"javascript:void(0)\" onclick=\"status("+u.getId()+")\"><button class=\"btn btn-danger\">Khóa</button></a>\r\n" + 
																"<a href=\"javascript:void(0)\" onclick=\"del('"+u.getId()+"')\"><button class=\"btn btn-danger\">Xóa</button></a>\r\n" + 
																"</td>"
															 );
			}
			if(u.getEnabled() == 0) {
				request.setCharacterEncoding("UTF-8");response.setContentType("text/html");response.setCharacterEncoding("UTF-8");
				response.getWriter().print(
																"<td width=\"14%\"><button class=\"btn btn-danger\">Đang bị khóa</button></td>\r\n" + 
																"<td width=\"15%\">\r\n" + 
																"<a href=\"javascript:void(0)\" onclick=\"status("+u.getId()+")\"><button class=\"btn btn-primary\">Mở khóa</button></a>\r\n" + 
																"<a href=\"javascript:void(0)\" onclick=\"del('"+u.getId()+"')\"><button class=\"btn btn-danger\">Xóa</button></a>\r\n" + 
																"</td>"
															 );
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
