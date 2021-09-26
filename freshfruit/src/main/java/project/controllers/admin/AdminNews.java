package project.controllers.admin;

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
import project.controllers.model.News;
import project.controllers.service.NewsService;

@Controller
@RequestMapping(URLConstant.ADMIN)
public class AdminNews {
	
	@Autowired
	private NewsService newsService;
	
	@GetMapping(URLConstant.ADMIN_NEWS)
	public String index(Model model) {
		model.addAttribute("news", newsService.getAll());
		return "admin.news";
	}

	@PostMapping(URLConstant.ADMIN_NEWS_ADD)
	public String add(@RequestParam String x, HttpServletRequest request, HttpServletResponse response) {
		newsService.save(new News(0, x));
		load(request, response);
		return null;
	}
	
	@PostMapping(URLConstant.ADMIN_NEWS_DEL)
	public String del(@RequestParam String x, HttpServletRequest request, HttpServletResponse response) {
		newsService.del(Integer.parseInt(x));
		load(request, response);
		return null;
	}
	
	public void load(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");response.setContentType("text/html");response.setCharacterEncoding("UTF-8");
			response.getWriter().print(
															"<div class=\"col-md-5\">\r\n" + 
															"<button class=\"btn btn-primary\" onclick=\"add()\" style=\"margin-bottom: 1rem;\">Thêm danh mục</button>\r\n" + 
															"<input id=\"N\" class=\"form-control\" value=\"\"/>\r\n" + 
															"</div>\r\n" + 
															"<div style=\"margin: 1rem;border: 0.5px solid #7460ee;\"></div>\r\n" + 
															"<div class=\"col-md-12\">\r\n" + 
															"<button class=\"btn btn-primary\" style=\"margin-bottom: 1rem;\">Chọn danh mục muốn xóa ?</button>\r\n" + 
															"<div class=\"form-group col-md-6\">\r\n" + 
															"<label>Danh mục</label>\r\n"
														  );
					response.getWriter().print("<ul class=\"dropdown\" style=\"max-height:  150px;overflow: scroll;\">\r\n");
					for(News n : newsService.getAll()) {
					response.getWriter().print(" <li class=\"dropdown-item\"><a href=\"javascript:void(0)\" onclick=\"del("+n.getNews_id()+")\">"+n.getNews_name()+"</a></li>\r\n");
					}
					response.getWriter().print(
																	"</ul>\r\n" + 
															        "</div>\r\n" + 
																	"</div>"
															      );
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
