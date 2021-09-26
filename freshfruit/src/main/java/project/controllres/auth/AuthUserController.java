package project.controllres.auth;

import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import project.constant.URLConstant;
import project.controllers.model.User;
import project.controllers.service.UserService;

@Controller
@RequestMapping(URLConstant.URL_AUTH)
public class AuthUserController {

	@Resource
	protected MessageSource messageSource;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder  bCryptPasswordEncoder;
	
	@GetMapping(URLConstant.URL_AUTH_LOGIN)
	public String login() {
		return "auth.login.user";
	}
	
	@GetMapping(URLConstant.URL_AUTH_SIGNUP)
	public String signup() {
		return "auth.signup.user";
	}
	
	@PostMapping(URLConstant.URL_AUTH_SIGNUP)
	public String signup(Model model , RedirectAttributes ra, @RequestParam String username, @RequestParam String fullname, @RequestParam String password, @RequestParam String repassword) {
		if(password.length() < 8) {
			ra.addFlashAttribute("notification",messageSource.getMessage("signup.length", null, Locale.getDefault()));
			return "redirect:/auth/signup";
		}else if(password.equals(repassword)) {
			userService.signup(username, fullname, bCryptPasswordEncoder.encode(password));
			ra.addFlashAttribute("notification",messageSource.getMessage("signup.success", null, Locale.getDefault()));
			return "redirect:/auth/login";
		}else {
			ra.addFlashAttribute("notification",messageSource.getMessage("signup.pass", null, Locale.getDefault()));
			return "redirect:/auth/signup";
		}
	}
	
	@GetMapping(URLConstant.URL_AUTH_LOGOUT)
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		return "redirect:/index";
	}
	
	@PostMapping(URLConstant.URL_AUTH_LOGIN)
	public String login(Model model,@ModelAttribute User user,@RequestParam String param, HttpServletRequest request) {
		if(userService.findOne(user) != null && param.equals("")) {
			if(bCryptPasswordEncoder.matches(user.getPassword(),userService.findOne(user).getPassword())){
				HttpSession session = request.getSession();
				User u = userService.findOne(user);
				session.setAttribute("user", u);
				if(userService.check(u.getId()).getEnabled() == 0) {
					session.removeAttribute("user");
					model.addAttribute("notification","Tài khoản đã bị vô hiệu hóa !");
					return "auth.login.user";
				}else {
					return "redirect:/index";
				}
			}
		}else if(userService.findOne(user) != null && param.equals("paramforward")) {
			if(bCryptPasswordEncoder.matches(user.getPassword(),userService.findOne(user).getPassword())){
				HttpSession session = request.getSession();
				session.setAttribute("user", userService.findOne(user));
				return "redirect:/cart";
			}
		}else {
			model.addAttribute("notification","Sai tên đăng nhập hoặc mật khẩu !");
			return "auth.login.user";
		}
		return null;
	}
}
