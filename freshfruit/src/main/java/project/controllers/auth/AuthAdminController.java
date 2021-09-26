package project.controllers.auth;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import project.constant.URLConstant;
import project.controllers.model.User;

@Controller
@RequestMapping(URLConstant.URL_AUTH_ADMIN)
public class AuthAdminController {

	@GetMapping()
	public String login(@RequestParam(required = false, value = "msg") String error, Model model) {
		if(error != null) {
			model.addAttribute("msg", "msg");
		}
		return "auth.login.admin";
	}
	
	@PostMapping()
	public String login(@ModelAttribute User user) {
		return "auth.login.admin";
	}
}
