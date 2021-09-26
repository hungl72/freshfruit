package project.controllers.freshfruit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import project.constant.GlobalConstant;
import project.constant.URLConstant;
import project.controllers.service.NewsDetailService;
import project.controllers.service.NewsService;
import project.controllers.service.ProductService;
import project.util.PageUtil;

@Controller
public class FreshFruitSearch {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	NewsDetailService detailServices;
	
	@Autowired
	NewsService newsService;
	
	@GetMapping(URLConstant.FRESH_FRUIT_SEARCH)
	public String search(@RequestParam("search") String search, @RequestParam("query") String query, Model model, @RequestParam("page") Integer page) {
		if (page == null) {
			page = 1;
		}
		int offset = PageUtil.getOffset(page);
		if(query.equals("product")) {
			model.addAttribute("resultProduct", productService.search(search, offset, GlobalConstant.TOTAL_ROW));
			model.addAttribute("search", search);
			model.addAttribute("query", query);
			model.addAttribute("currentPage", page);
			model.addAttribute("totalPage", PageUtil.getTotalpage(productService.totalRowForSearch(search)));
		}else {
			model.addAttribute("resultDetail", detailServices.search(search, offset, GlobalConstant.TOTAL_ROW));
			model.addAttribute("search", search);
			model.addAttribute("query", query);
			model.addAttribute("currentPage", page);
			model.addAttribute("totalPage", PageUtil.getTotalpage(detailServices.totalRowForSearch(search)));
		}
		model.addAttribute("listNews", newsService.getAll());
		return "freshfruit.search";
	}
	
}
