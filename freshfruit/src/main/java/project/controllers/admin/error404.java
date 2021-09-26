package project.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import project.constant.URLConstant;

@Controller
@RequestMapping(URLConstant.DENIED_PAGE)
public class error404 {
	
	@GetMapping()
	public String index() {
		return "erroradmin404";
	}
	
}
