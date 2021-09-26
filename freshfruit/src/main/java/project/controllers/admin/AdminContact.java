package project.controllers.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import project.constant.GlobalConstant;
import project.constant.URLConstant;
import project.controllers.model.Contact;
import project.controllers.service.ContactService;
import project.util.PageUtil;

@Controller
@RequestMapping(URLConstant.ADMIN)
public class AdminContact {

	@Autowired
	private ContactService contactService;
	
	@GetMapping(URLConstant.ADMIN_CONTACT)
	public String index(Model model, @PathVariable Integer page) {
		if (page == null) {
			page = 1;
		}
		int offset = PageUtil.getOffset(page);
		List<Contact> listContact = contactService.pagination(offset, GlobalConstant.TOTAL_ROW);
		if(listContact == null || listContact.size() == 0) {
			model.addAttribute("admin404", "admin404");
			return "error404";
		}else {
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPage", PageUtil.getTotalpage(contactService.totalRow()));
		model.addAttribute("listContact", listContact);
		return "admin.contact";
		}
	}
	
	@PostMapping(URLConstant.ADMIN_CONTACT_DEL)
	public String del(@RequestParam Integer id, @RequestParam Integer page, HttpServletRequest request, HttpServletResponse response) {
		int offset = (page -1) * 3;
		contactService.del(id);
		try {
			request.setCharacterEncoding("UTF-8");response.setContentType("text/html");response.setCharacterEncoding("UTF-8");
			response.getWriter().print("");
			if(contactService.getAll(offset) == null || contactService.getAll(offset).size() == 0) {
				response.getWriter().print("<td><h2>Page này đã bị xóa hết !</h2></td>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
