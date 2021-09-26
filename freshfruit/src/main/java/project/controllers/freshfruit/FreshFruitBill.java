package project.controllers.freshfruit;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import project.constant.URLConstant;
import project.controllers.model.Bill;
import project.controllers.model.User;
import project.controllers.service.BillService;
import project.controllers.service.NewsService;

@Controller
public class FreshFruitBill {
	
	@Autowired
	private BillService billService;
	
	@Autowired
	private NewsService newsService;
	
	@GetMapping(URLConstant.FRESH_FRUIT_BILL)
	public String getBill(HttpServletRequest request, Model model) {
		User user = (User)request.getSession().getAttribute("user");
		List<Bill> listBill = billService.getAll(user.getId());
		if(listBill != null && listBill.size() > 0) {
			model.addAttribute("listBill", listBill);
		}else {
			model.addAttribute("billEmpty", "Bạn chưa mua sản phẩm nào nên chưa có hóa đơn !");
		}
		model.addAttribute("listNews", newsService.getAll());
		return "freshfruit.bill";
	}
}
