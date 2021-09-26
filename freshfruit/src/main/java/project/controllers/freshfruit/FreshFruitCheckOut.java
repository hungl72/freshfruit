package project.controllers.freshfruit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import project.constant.URLConstant;
import project.controllers.service.NewsService;

@Controller
public class FreshFruitCheckOut {

	@Autowired
	private NewsService newsService;
	
	@PostMapping(URLConstant.FRESH_FRUIT_CHECKOUT)
	public String index(Model model) {
		model.addAttribute("listNews", newsService.getAll());
		return "freshfruit.checkout";
	}
}
