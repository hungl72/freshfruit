package project.controllers.admin;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import project.constant.URLConstant;
import project.controllers.model.Menu;
import project.controllers.service.MenuService;

@Controller
@RequestMapping(URLConstant.ADMIN)
public class AdminMenu {
	
	@Autowired
	private MenuService menuService;

	@PostMapping(URLConstant.ADMIN_MENU_PARENT_ADD_P)
	public String addP(@RequestParam String x, HttpServletRequest request, HttpServletResponse response) {
		menuService.save(new Menu(0, x, 0));
		load(request, response);
		return null;
	}
	
	@PostMapping(URLConstant.ADMIN_MENU_CHILDREN_ADD_C)
	public String addC(@RequestParam String x, @RequestParam String y, HttpServletRequest request, HttpServletResponse response) {
		menuService.save(new Menu(0, x, Integer.parseInt(y)));
		load(request, response);
		return null;
	}
	
	@PostMapping(URLConstant.ADMIN_MENU_PARENT_DEL_P)
	public String delP(@RequestParam String x, HttpServletRequest request, HttpServletResponse response) {
		menuService.del(Integer.parseInt(x));
		load(request, response);
		return null;
	}
	
	@PostMapping(URLConstant.ADMIN_MENU_CHILDREN_DEL_C)
	public String delC(@RequestParam String x, HttpServletRequest request, HttpServletResponse response) {
		menuService.del(Integer.parseInt(x));
		load(request, response);
		return null;
	}
	
	public void load(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");response.setContentType("text/html");response.setCharacterEncoding("UTF-8");
					response.getWriter().print(
																	" <div class=\"col-md-5\">\r\n" + 
																	"<button class=\"btn btn-primary\" onclick=\"addP()\" style=\"margin-bottom: 1rem;\">Thêm danh mục cha</button>\r\n" + 
																	"<input id=\"P\" class=\"form-control\" value=\"\"/>\r\n" + 
																	"</div>\r\n" + 
																	"<div style=\"margin: 1rem;border: 0.5px solid #7460ee;\"></div>\r\n" + 
																	"<div class=\"col-md-5\">\r\n" + 
																	"<button class=\"btn btn-primary\" onclick=\"addC()\" style=\"margin-bottom: 1rem;\">Thêm danh mục con</button>\r\n" + 
																	"<input class=\"form-control\" id=\"C\" value=\"\"/>\r\n" + 
																	"<div class=\"form-group\" style=\"margin-top: 1rem;\">\r\n" + 
																	"<label>Chọn danh mục cha</label>\r\n" +
																	"<select class=\"form-select shadow-none col-12\" id=\"S\" required=\"required\">"
																 );
					for(Menu m  : menuService.getAll()) {
						response.getWriter().print(
																		"<option value=\""+m.getMenu_id()+"\">"+m.getMenu_name()+"</option>\r\n"); 
																	 }
					response.getWriter().print(
																	"</select>\r\n" + 
																	" </div>\r\n" + 
																	"</div>\r\n" + 
																	" <div style=\"margin: 1rem;border: 0.5px solid #7460ee;\"></div>\r\n" + 
																	"<div class=\"col-md-12\">\r\n" + 
																	"<button class=\"btn btn-primary\" style=\"margin-bottom: 1rem;\">Chọn danh mục muốn xóa ?</button>\r\n" + 
																	"<div class=\"form-group col-md-6\">\r\n" + 
																	"<label>Danh mục cha</label>\r\n"
																 );
					if(menuService.getAll() != null && menuService.getAll().size() > 0) {
						response.getWriter().print(
																		"<ul class=\"dropdown\" style=\"max-height:  150px;overflow: scroll;\">\r\n"
																	 );
							for(Menu m  : menuService.getAll()) {
								response.getWriter().print(
																				"<li class=\"dropdown-item\"><a href=\"javascript:void(0)\" onclick=\"delP("+m.getMenu_id()+")\">"+m.getMenu_name()+"</a></li>\r\n"
																			 );
							}
							response.getWriter().print("</ul>\r\n");
					}
					response.getWriter().print(
																	"</div>\r\n" + 
																	"<div class=\"form-group col-md-6\" style=\"margin-top: 1rem;\">\r\n" + 
																	"<label>Danh mục con</label>\r\n"
																 );
					if(menuService.getAll(0) != null && menuService.getAll(0).size() > 0) {
						response.getWriter().print(
																		"<ul class=\"dropdown\" style=\"max-height:  150px;overflow: scroll;\">\r\n"
																	 );
					for(Menu c : menuService.getAll(0)) {
						response.getWriter().print(
																		"<li class=\"dropdown-item\"><a href=\"javascript:void(0)\" onclick=\"delC("+c.getMenu_id()+")\">"+c.getMenu_name()+"</a></li>\r\n"
																	 ); 
					}
					response.getWriter().print("</ul>\r\n"); 
					}
					response.getWriter().print(
																	"</div>\r\n" + 
																	"</div>"
																 );
		} catch (IOException e) {
			e.printStackTrace();
		};
	}
	
}
