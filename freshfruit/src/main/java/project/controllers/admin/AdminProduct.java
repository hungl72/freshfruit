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
import project.controllers.model.Menu;
import project.controllers.model.Product;
import project.controllers.service.MenuService;
import project.controllers.service.ProductService;
import project.util.FileUtil;
import project.util.PageUtil;

@Controller
@RequestMapping(URLConstant.ADMIN)
public class AdminProduct {
	
	@Resource
	protected MessageSource messageSource;
	
	@Autowired
	MenuService menuService;
	
	@Autowired
	ProductService productService;

	@GetMapping(URLConstant.ADMIN_PRODUCT)
	public String product(Model model, @PathVariable Integer page) {
		if (page == null) {
			page = 1;
		}
		int offset = PageUtil.getOffset(page);
		List<Product> listProduct = productService.pagination(offset, GlobalConstant.TOTAL_ROW);
		if(listProduct == null || listProduct.size() == 0) {
			model.addAttribute("admin404", "admin404");
			return "error404";
		}else {
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPage", PageUtil.getTotalpage(productService.totalRowNotMenuId()));
		model.addAttribute("listProduct", listProduct);
		return "admin.product";
		}
	}
	
	@GetMapping(URLConstant.ADMIN_PRODUCT_ADD)
	public String add(Model model) {
		model.addAttribute("menuC", menuService.getAll(0));
		return "admin.product.add";
	}
	
	@PostMapping(URLConstant.ADMIN_PRODUCT_ADD)
	public String add(@Valid @ModelAttribute("p") Product product,BindingResult result,@RequestParam("file") MultipartFile file,HttpServletRequest request, HttpServletResponse response,RedirectAttributes ra,ModelMap model) {
		try {
		request.setCharacterEncoding("UTF-8");response.setContentType("text/html");response.setCharacterEncoding("UTF-8");
		List<Product> listProduct = productService.getAll();
		model.addAttribute("listProduct", listProduct);
		if(result.hasErrors()) {
			model.addAttribute("menuP", menuService.getAll());
			model.addAttribute("menuC", menuService.getAll(0));
			return "admin.product.add";
		}
		product.setMenu(new Menu(0, "", product.getMenu_id()));
		String fileName = FileUtil.upload(file, request);
		product.setProduct_image(fileName);
		product.setParent_id(menuService.findById(product.getMenu_id()).getParent_id());
		if (productService.save(product) > 0) {
			ra.addFlashAttribute("msg", messageSource.getMessage("msg.success", null, Locale.getDefault()));
			return "redirect:/admin/product/page/1";
		}
		}catch(Exception e) {}
		return "admin.product.add";
	}
	
	@GetMapping(URLConstant.ADMIN_PRODUCT_EDIT)
	public String edit(@PathVariable String id,Model model) {
		model.addAttribute("menuC", menuService.getAll(0));
		model.addAttribute("p", productService.getProductByProductId(Integer.parseInt(id)));
		return "admin.product.edit";
	}
	
	@PostMapping(URLConstant.ADMIN_PRODUCT_EDIT)
	public String edit(@ModelAttribute("p") Product product,BindingResult result,@PathVariable int id,@RequestParam String menu_id,@RequestParam("file") MultipartFile file,HttpServletRequest request,RedirectAttributes ra,ModelMap modelMap) {
		if(Integer.parseInt(menu_id) != 0) {
			List<Product> listProduct = productService.getAll();
			modelMap.addAttribute("listProduct", listProduct);
			if(result.hasErrors()) {
				return "admin.product.edit";
			}
			Product oldProduct = productService.findById(id);
			String fileName = FileUtil.upload(file, request);
			if("".equals(fileName)) {
				product.setProduct_image(oldProduct.getProduct_image());
			}else {
				product.setProduct_image(fileName);
			}
			product.setMenu(new Menu(0,"",product.getMenu_id()));
			product.setProduct_id(id);
			product.setMenu_name(productService.findMenuName(product.getMenu_id()));
			if(productService.update(product)> 0) {
				ra.addFlashAttribute("msg",messageSource.getMessage("msg.success", null, Locale.getDefault()));
				return "redirect:/admin/product/page/1";
			}
		}
		return "admin.product.edit";
	}
	
	@PostMapping(URLConstant.ADMIN_PRODUCT_DEL)
	public String del(@RequestParam String id, @RequestParam Integer page, Model model,HttpServletResponse response, HttpServletRequest request) {
		int offset = (page -1) * 3;
		productService.del(Integer.parseInt(id));
		try {
			request.setCharacterEncoding("UTF-8");response.setContentType("text/html");response.setCharacterEncoding("UTF-8");
			response.getWriter().print(
															"<table class=\"table\">\r\n" + 
															"<thead>\r\n" + 
															"<tr>\r\n" + 
															"<th scope=\"col\">Mã sản phẩm</th>\r\n" + 
															"<th scope=\"col\">Tên sản phẩm</th>\r\n" + 
															"<th scope=\"col\">Đơn giá</th>\r\n" + 
															"<th scope=\"col\">Khuyến mãi (%)</th>\r\n" + 
															"<th scope=\"col\">Mô tả</th>\r\n" + 
															"<th scope=\"col\">Ảnh</th>\r\n" + 
															"<th scope=\"col\">Danh mục</th>\r\n" + 
															"<th scope=\"col\">Chức năng</th>\r\n" + 
															"</tr>\r\n" + 
															"</thead>\r\n" + 
															"<tbody>"
														  );
			if(productService.getAll(offset) == null || productService.getAll(offset).size() == 0) {
				response.getWriter().print("<tr><td><h2>Page này đã bị xóa hết !</h2></td></tr>");
			}else {
				for(Product p : productService.getAll(offset)) {
					response.getWriter().print(
																	"<tr>\r\n" + 
																	"<td>"+p.getProduct_id()+"</td>\r\n" + 
																	"<td width=\"12%;\">"+p.getProduct_name()+"</td>\r\n" + 
																	"<td>"+p.getProduct_price()+"</td>\r\n" + 
																	"<td>"+p.getProduct_promotion()+"</td>\r\n"
																  );
						if(p.getProduct_description().length() < 200) {
							response.getWriter().print("<td>"+p.getProduct_description()+"</td>");
						}else {
							response.getWriter().print("<td>"+p.getProduct_description().substring(0, 200)+" ...</td>");
						}
						response.getWriter().print(
							"<td><img src=\"/freshfruit/resources/img/"+p.getProduct_image()+"\" style=\"width: 200px;height: 150px;\"/></td>\r\n" + 
							"<td width=\"12%;\">"+p.getMenu_name()+"</td>\r\n" + 
							"<td width=\"12%;\">\r\n" + 
							"<a href=\"/freshfruit/admin/product/edit/"+p.getProduct_id()+"\"><button class=\"btn btn-primary\">Sửa</button></a>\r\n" + 
							"<a href=\"javascript:void(0)\" onclick=\"del('"+p.getProduct_id()+"')\"><button class=\"btn btn-primary\">Xóa</button></a>\r\n" + 
							"</td>\r\n" + 
							"</tr>\r\n");
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
