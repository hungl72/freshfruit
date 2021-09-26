package project.controllers.admin;

import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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

import project.constant.GlobalConstant;
import project.constant.URLConstant;
import project.controllers.model.NewsDetail;
import project.controllers.service.NewsDetailService;
import project.controllers.service.NewsService;
import project.util.FileUtil;
import project.util.PageUtil;

@Controller
@RequestMapping(URLConstant.ADMIN)
public class AdminNewsDetail {

	@Resource
	protected MessageSource messageSource;
	
	@Autowired
	private NewsService newsService;
	
	@Autowired
	private NewsDetailService newsDetailService;
	
	@GetMapping(URLConstant.ADMIN_NEWSDETAIL)
	public String detail(Model model, @PathVariable Integer page) {
		if (page == null) {
			page = 1;
		}
		int offset = PageUtil.getOffset(page);
		List<NewsDetail> listDetail = newsDetailService.pagination(offset, GlobalConstant.TOTAL_ROW);
		if(listDetail == null || listDetail.size() == 0) {
			model.addAttribute("admin404", "admin404");
			return "error404";
		}else {
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPage", PageUtil.getTotalpage(newsDetailService.totalRow()));
		model.addAttribute("listDetail", listDetail);
		return "admin.newsdetail";
		}
	}
	
	@GetMapping(URLConstant.ADMIN_NEWSDETAIL_ADD)
	public String add(Model model) {
		model.addAttribute("news", newsService.getAll());
		return "admin.newsdetail.add";
	}

	@PostMapping(URLConstant.ADMIN_NEWSDETAIL_ADD)
	public String add(@Valid @ModelAttribute("nd") NewsDetail newsdetail,BindingResult result,@RequestParam("file") MultipartFile file,HttpServletRequest request,RedirectAttributes ra,ModelMap model) {
		List<NewsDetail> listNewsDetail = newsDetailService.getAll();
		model.addAttribute("listNewsDetail", listNewsDetail);
		if(result.hasErrors()) {
			model.addAttribute("news", newsService.getAll());
			return "admin.newsdetail.add";
		}
		newsdetail.setNews_id(newsdetail.getNews_id());
		String fileName = FileUtil.upload(file, request);
		newsdetail.setNews_detail_image(fileName);
		if (newsDetailService.save(newsdetail) > 0) {
			ra.addFlashAttribute("msg", messageSource.getMessage("msg.success", null, Locale.getDefault()));
			return "redirect:/admin/newsdetail/page/1";
		}
		return "admin.newsdetail.add";
	}
	
	@GetMapping(URLConstant.ADMIN_NEWSDETAIL_EDIT)
	public String edit(@PathVariable String id,Model model) {
		model.addAttribute("news", newsService.getAll());
		model.addAttribute("nd", newsDetailService.findById(Integer.parseInt(id)));
		return "admin.newsdetail.edit";
	}
	
	@PostMapping(URLConstant.ADMIN_NEWSDETAIL_EDIT)
	public String edit(@ModelAttribute("nd") NewsDetail newsDetail, BindingResult result,@PathVariable int id,@RequestParam String news_id,@RequestParam("file") MultipartFile file,HttpServletRequest request,RedirectAttributes ra,ModelMap modelMap) {
		if(Integer.parseInt(news_id) != 0) {
			List<NewsDetail> listNewsDetail = newsDetailService.getAll();
			modelMap.addAttribute("listNewsDetail", listNewsDetail );
			if(result.hasErrors()) {
				return "admin.newsdetail.edit";
			}
			NewsDetail oldNewsDetail = newsDetailService.findById(id);
			String fileName = FileUtil.upload(file, request);
			if("".equals(fileName)) {
				newsDetail.setNews_detail_image(oldNewsDetail.getNews_detail_image());
			}else {
				newsDetail.setNews_detail_image(fileName);
			}
			newsDetail.setNews_id(newsDetail.getNews_id());
			newsDetail.setNews_detail_date(oldNewsDetail.getNews_detail_date());
			newsDetail.setNews_detail_id(id);
			newsDetail.setNews_name(newsService.findById(newsDetail.getNews_id()).getNews_name());
			if(newsDetailService.update(newsDetail)> 0) {
				ra.addFlashAttribute("msg",messageSource.getMessage("msg.success", null, Locale.getDefault()));
				return "redirect:/admin/newsdetail/page/1";
			}
		}
		return "admin.newsdetail.edit";
	}

	@PostMapping(URLConstant.ADMIN_NEWSDETAIL_DEL)
	public String del(@RequestParam String id, @RequestParam Integer page, Model model,HttpServletResponse response, HttpServletRequest request) {
		int offset = (page -1) * 3;
		newsDetailService.del(Integer.parseInt(id));
		try {
			request.setCharacterEncoding("UTF-8");response.setContentType("text/html");response.setCharacterEncoding("UTF-8");
			response.getWriter().print(
															"<table class=\"table\">\r\n" + 
															"<thead>\r\n" + 
															"<tr>\r\n" + 
															"<th scope=\"col\">Mã tin tức</th>\r\n" + 
															"<th scope=\"col\">Tên tin tức</th>\r\n" + 
															"<th scope=\"col\">Ngày đăng</th>\r\n" + 
															"<th scope=\"col\">Nội dung</th>\r\n" + 
															"<th scope=\"col\">Ảnh</th>\r\n" + 
															"<th scope=\"col\">Mã danh mục tin</th>\r\n" + 
															"<th scope=\"col\">Tên danh mục tin</th>\r\n" + 
															"<th scope=\"col\">Chức năng</th>\r\n" + 
															"</tr>\r\n" + 
															"</thead>\r\n" + 
															"<tbody>"
														  );
			if(newsDetailService.getAllOffset(offset) == null || newsDetailService.getAllOffset(offset).size() == 0) {
				response.getWriter().print("<tr><td><h2>Page này đã bị xóa hết !</h2></td></tr>");
			}else {
				for(NewsDetail nd : newsDetailService.getAllOffset(offset)) {
					response.getWriter().print(
																	"<tr>\r\n" + 
																	"<td>"+nd.getNews_detail_id()+"</td>\r\n" + 
																	"<td width=\"12%;\">"+nd.getNews_detail_name()+"</td>\r\n" +
																	"<td width=\"12%;\">"+nd.getNews_detail_date()+"</td>\r\n"
																 );
						if(nd.getNews_detail_description().length() < 200) {
							response.getWriter().print("<td>"+nd.getNews_detail_description()+"</td>");
						}else {
							response.getWriter().print("<td>"+nd.getNews_detail_description().substring(0, 200)+" ...</td>");
						}
						response.getWriter().print(
																		"<td><img src=\"/freshfruit/resources/img/"+nd.getNews_detail_image()+"\" style=\"width: 200px;height: 150px;\"/></td>\r\n" + 
																		"<td width=\"12%;\">"+nd.getNews_id()+"</td>\r\n" + 
																		"<td width=\"12%;\">"+nd.getNews_name()+"</td>" +
																		"<td width=\"12%;\">\r\n" + 
																		"<a href=\"/freshfruit/admin/newsdetail/edit/"+nd.getNews_detail_id()+"\"><button class=\"btn btn-primary\">Sửa</button></a>\r\n" + 
																		"<a href=\"javascript:void(0)\" onclick=\"del('"+nd.getNews_detail_id()+"')\"><button class=\"btn btn-primary\">Xóa</button></a>\r\n" + 
																		"</td>\r\n" + 
																		"</tr>\r\n"
																	 );
				}
				response.getWriter().print(
																"</tbody>\r\n" + 
																"</table>"
													     	 );
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
