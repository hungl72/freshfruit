package project.controllers.admin;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import project.constant.URLConstant;
import project.controllers.service.ContactService;
import project.controllers.service.MenuService;
import project.controllers.service.NewsDetailService;
import project.controllers.service.ProductService;
import project.controllers.service.UserService;

@Controller
@RequestMapping(URLConstant.ADMIN)
public class AdminIndex {
	
	@Autowired
	MenuService menuService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	NewsDetailService newsDetailService;
	
	@Autowired
	ContactService contactService;
	
	@GetMapping(URLConstant.ADMIN_INDEX)
	public String index(HttpSession session, Model model, Principal principal) {
        session.setAttribute("admin", principal.getName());
        model.addAttribute("numberUser", userService.getNumber());
        model.addAttribute("numberProduct", productService.getNumber());
        model.addAttribute("numberNewsDetail", newsDetailService.getNumber());
        model.addAttribute("numberContact", contactService.getNumber());
		return "admin.index";
	}
	
	@GetMapping(URLConstant.ADMIN_MENU)
	public String menu(Model model) {
		model.addAttribute("menuP", menuService.getAll());
		model.addAttribute("menuC", menuService.getAll(0));
		return "admin.menu";
	}
	
	
}
