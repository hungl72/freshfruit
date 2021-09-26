package project.controllers.freshfruit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import project.constant.GlobalConstant;
import project.constant.URLConstant;
import project.controllers.model.NewsDetail;
import project.controllers.service.NewsDetailService;
import project.controllers.service.NewsService;
import project.util.PageUtil;

@Controller
public class FreshFruitNewsController {

	@Autowired
	private NewsService newsService;
	
	@Autowired
	private NewsDetailService detailService;
	
	@GetMapping(URLConstant.FRESH_FRUIT_NEWS)
	public String index(Model model) {
		model.addAttribute("listNews", newsService.getAll());
		return "freshfruit.news";
	}
	
	@GetMapping(URLConstant.FRESH_FRUIT_NEWS_ID_PAGE)
	public String listNewsById (Model model, @PathVariable(required = false) Integer news_id, @PathVariable Integer page) {
		if (page == null) {
			page = 1;
		}
		int offset = PageUtil.getOffset(page);
		List<NewsDetail> listDetail = detailService.getAll(news_id, offset, GlobalConstant.TOTAL_ROW);
		model.addAttribute("listNews", newsService.getAll());
		model.addAttribute("list_news_id_news_detail_id", listDetail);;
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPage", PageUtil.getTotalpage(detailService.totalRow(news_id)));
		model.addAttribute("news_id", news_id);
		model.addAttribute("getNewsName", newsService.findById(news_id).getNews_name());
		return "freshfruit.news";
	}
}
