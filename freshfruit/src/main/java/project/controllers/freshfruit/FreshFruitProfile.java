package project.controllers.freshfruit;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import project.constant.URLConstant;
import project.controllers.model.News;
import project.controllers.model.User;
import project.controllers.service.NewsService;
import project.controllers.service.UserService;
import project.util.FileUtil;

@Controller
@RequestMapping(URLConstant.FRESH_FRUIT_PROFILE)
public class FreshFruitProfile {

	@Resource
	protected MessageSource messageSource;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private NewsService newsService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping(URLConstant.FRESH_FRUIT_PROFILE_ID)
	public String index(Model model, HttpServletRequest request, @PathVariable Integer id) {
		User user = (User)request.getSession().getAttribute("user");
		if(id != user.getId()) {
			model.addAttribute("user404", "user404");
			return "error404";
		}else {
			model.addAttribute("u", userService.findById(user.getId()));
		}
		List<News> listNews = new ArrayList<News>();
		listNews = newsService.getAll();
		model.addAttribute("listNews",listNews);
		return "freshfruit.profile";
	}
	
	@PostMapping(URLConstant.FRESH_FRUIT_PROFILE_UPDATE)
	public String update(@Valid @ModelAttribute("u") User user,BindingResult result, @RequestParam("file") MultipartFile file,HttpServletRequest request, HttpServletResponse response, RedirectAttributes ra,ModelMap model) {
		try {
		request.setCharacterEncoding("UTF-8");response.setContentType("text/html");response.setCharacterEncoding("UTF-8");
		if(result.hasErrors()) {
			System.out.println(result.getAllErrors());
			ra.addFlashAttribute("msg", messageSource.getMessage("msg.false", null, Locale.getDefault()));
			model.addAttribute("u", userService.findById(user.getId()));
			return "redirect:/profile/"+user.getId();
		} 
		User u = (User)request.getSession().getAttribute("user");
		User oldUser = userService.findById(u.getId());
		String fileName = FileUtil.upload(file, request);
		String password = user.getPassword();
		if(password.length() >= 8) {
			if("".equals(fileName)) {
				fileName = oldUser.getImage();
			}
			if("".equals(password)){
				password = oldUser.getPassword();
			}else {
				password = bCryptPasswordEncoder.encode(password);
			}
			if(userService.update(new User(u.getId(), oldUser.getUsername(), user.getFullname(), password, user.getInformation(), fileName, user.getEmail(), oldUser.getRole_id(), oldUser.getEnabled())) > 0) {
				ra.addFlashAttribute("msg", messageSource.getMessage("profile.success", null, Locale.getDefault()));
				model.addAttribute("u", userService.findById(u.getId()));
				return "redirect:/profile/"+u.getId();
			}
		}else {
			ra.addFlashAttribute("msg", messageSource.getMessage("profile.password.length", null, Locale.getDefault()));
			model.addAttribute("u", userService.findById(u.getId()));
			return "redirect:/profile/"+u.getId();
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("listNews", newsService.getAll());
		return "freshfruit.profile";
	}
}
