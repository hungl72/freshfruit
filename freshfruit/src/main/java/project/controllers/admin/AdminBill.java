package project.controllers.admin;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import project.constant.GlobalConstant;
import project.constant.URLConstant;
import project.controllers.model.Bill;
import project.controllers.service.BillService;
import project.util.PageUtil;

@Controller
@RequestMapping(URLConstant.ADMIN)
public class AdminBill {

	@Autowired
	private BillService billService;
	
	@GetMapping(URLConstant.ADMIN_BILL)
	public String index(Model model, @PathVariable Integer page, Principal principal) {
		if (page == null) {
			page = 1;
		}
		int offset = PageUtil.getOffset(page);
		List<Bill> listBill = billService.pagination(offset, GlobalConstant.TOTAL_ROW);
		if(listBill == null || listBill.size() == 0) {
			model.addAttribute("admin404", "admin404");
			return "error404";
		}else {
			model.addAttribute("currentPage", page);
			model.addAttribute("totalPage", PageUtil.getTotalpage(billService.totalRow()));
			model.addAttribute("listBill", listBill);
			model.addAttribute("admin", principal.getName());
			return "admin.bill";
		}
	}
}
