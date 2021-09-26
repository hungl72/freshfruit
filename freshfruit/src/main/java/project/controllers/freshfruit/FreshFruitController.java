package project.controllers.freshfruit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import project.constant.URLConstant;
import project.controllers.service.NewsDetailService;
import project.controllers.service.NewsService;
import project.controllers.service.ProductService;

@Controller
public class FreshFruitController {
	
	@Autowired
	private NewsService newsService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private NewsDetailService newsDetailService;
	
	@GetMapping(URLConstant.FRESH_FRUIT_INDEX)
	public String index(Model model) {
		model.addAttribute("listNews", newsService.getAll());
		model.addAttribute("listProduct", productService.newProductThree());
		model.addAttribute("listNewsDetail", newsDetailService.newDetailThree());
		return "freshfruit.index";
	}

}
