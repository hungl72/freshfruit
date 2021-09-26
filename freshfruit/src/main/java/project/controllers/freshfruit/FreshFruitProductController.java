package project.controllers.freshfruit;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import project.constant.GlobalConstant;
import project.constant.URLConstant;
import project.controllers.model.Menu;
import project.controllers.model.News;
import project.controllers.model.Product;
import project.controllers.service.MenuService;
import project.controllers.service.NewsService;
import project.controllers.service.ProductService;
import project.util.PageUtil;

@Controller
public class FreshFruitProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private NewsService newsService;
	
	@Autowired
	private MenuService menuService;
	
	@GetMapping(URLConstant.FRESH_FRUIT_PRODUCT)
	public String product(ModelMap model) {
		List<News> listNews = new ArrayList<News>();
		listNews = newsService.getAll();
		model.addAttribute("listNews",listNews);
		List<Product> listProduct = productService.getAll();
		model.addAttribute("listProduct",listProduct);
		return "freshfruit.product";
	}

	@GetMapping(URLConstant.FRESH_FRUIT_PRODUCT_ID)
	public String productByProductId(ModelMap model, @PathVariable Integer menu_id, @PathVariable Integer product_id) {
		Product product = productService.getProductByProductId(product_id);
		Menu menu = menuService.findById(menu_id);
		model.addAttribute("listNews", newsService.getAll());
		model.addAttribute("productByProductId", product);
		model.addAttribute("getNameMenu", menu.getMenu_name());
		model.addAttribute("getNameCategory", menuService.findById(menu.getParent_id()).getMenu_name());
		model.addAttribute("listProductSameCategory", productService.listProductSameCategory(product_id, menu_id));
		return "freshfruit.productdetail";
	}
	
	@GetMapping(URLConstant.FRESH_FRUIT_PRODUCT_MENU_ID_PAGE)
	public String productByMenuId(ModelMap model, @PathVariable Integer menu_id, @PathVariable Integer page) {
		if (page == null) {
			page = 1;
		}
		int offset = PageUtil.getOffset(page);
		List<Product> listProduct = productService.getAll(menu_id, offset, GlobalConstant.TOTAL_ROW);
		model.addAttribute("listNews", newsService.getAll());
		model.addAttribute("listProductByMenuId",listProduct);
		model.addAttribute("menu_id", menu_id);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPage", PageUtil.getTotalpage(productService.totalRow(menu_id)));
		return "freshfruit.product";
	}
	
}
