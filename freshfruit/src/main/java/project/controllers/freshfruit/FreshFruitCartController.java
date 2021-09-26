package project.controllers.freshfruit;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import project.constant.URLConstant;
import project.controllers.model.Bill;
import project.controllers.model.Cart;
import project.controllers.model.Item;
import project.controllers.model.News;
import project.controllers.model.Product;
import project.controllers.model.User;
import project.controllers.service.BillService;
import project.controllers.service.NewsService;
import project.controllers.service.ProductService;

@Controller
@RequestMapping(URLConstant.FRESH_FRUIT_CART)
public class FreshFruitCartController {

	@Autowired
	private ProductService productService;

	@Autowired
	private NewsService newsService;
	
	@Autowired
	BillService billService;
	
	@Autowired
	Email email;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping()
	public String index(HttpSession session,ModelMap model) {
		try {
		String bill_product = "";
		double totalTemp = 0, totalPromotionTemp = 0;
		model.addAttribute("listNews",newsService.getAll());
		ArrayList<Product> arrProduct = new ArrayList<Product>();
		Cart cart = (Cart)session.getAttribute("cart");
		double total = 0,totalPromotion = 0;
		for(Map.Entry<Integer, Item> c : cart.getCartItem().entrySet()) {
			arrProduct.add(new Product(
															c.getValue().getProduct().getProduct_id(),
															c.getValue().getProduct().getProduct_name(),
															c.getValue().getProduct().getProduct_price(),
															c.getValue().getProduct().getProduct_promotion(),
															c.getValue().getProduct().getProduct_description(),
															c.getValue().getProduct().getProduct_image(),
															c.getValue().getMass(),
															c.getValue().getProduct().getMenu_id()
															)
										);
			total += c.getValue().getProduct().getProduct_price()*c.getValue().getMass();
			totalPromotion += (c.getValue().getProduct().getProduct_price() -
											 (c.getValue().getProduct().getProduct_price() *
											  c.getValue().getProduct().getProduct_promotion())/100) *
											  c.getValue().getMass();
			bill_product += c.getValue().getProduct().getProduct_name() + " - " + c.getValue().getProduct().getProduct_price() + " x " + new DecimalFormat("#").format(c.getValue().getMass()) + ", ";
		}
		totalTemp = total;
		totalPromotionTemp = totalPromotion;
		model.addAttribute("arrProduct", arrProduct);
		model.addAttribute("total", total*100/100);
		model.addAttribute("totalPromotion", totalPromotion*100/100);
		}catch(Exception e) {}
		return "freshfruit.cart";
	}
	
	@PostMapping
	public @ResponseBody String insertCart(HttpSession session ,@ModelAttribute("x") String product_id, @ModelAttribute("y") String mass) {
		int id = Integer.parseInt(product_id);
		int ma = Integer.parseInt(mass);
		Product product = productService.getProductByProductId(id);
		Cart cart = (Cart)session.getAttribute("cart");
		if(cart.getCartItem().containsKey(id)) {
			cart.add(id, new Item(
												product,
												cart.getCartItem().get(id).getMass() + ma
												));
		}else {
			cart.add(id, new Item(
												product,
												ma
												 ));
		}
		session.setAttribute("cart", cart);
		return "";
	}
	
	@PostMapping(URLConstant.FRESH_FRUIT_CART_DEL)
	public void del(@RequestParam String id, Model model, HttpSession session, HttpServletResponse response, HttpServletRequest request) {
		String bill_product = "";
		List<News> listNews = new ArrayList<News>();
		listNews = newsService.getAll();
		model.addAttribute("listNews",listNews);
		ArrayList<Product> arrProduct = new ArrayList<Product>();
		Cart cart = (Cart)session.getAttribute("cart");
		double total = 0,totalPromotion = 0;
		cart.del(Integer.parseInt(id));
		for(Map.Entry<Integer, Item> c : cart.getCartItem().entrySet()) {
			arrProduct.add(new Product(
															c.getValue().getProduct().getProduct_id(),
															c.getValue().getProduct().getProduct_name(),
															c.getValue().getProduct().getProduct_price(),
															c.getValue().getProduct().getProduct_promotion(),
															c.getValue().getProduct().getProduct_description(),
															c.getValue().getProduct().getProduct_image(),
															c.getValue().getMass(),
															c.getValue().getProduct().getMenu_id()
															)
										);
			total += c.getValue().getProduct().getProduct_price()*c.getValue().getMass();
			totalPromotion += (c.getValue().getProduct().getProduct_price() -
											 (c.getValue().getProduct().getProduct_price() *
											  c.getValue().getProduct().getProduct_promotion())/100) *
											  c.getValue().getMass();
			bill_product += c.getValue().getProduct().getProduct_name() + " - " + c.getValue().getProduct().getProduct_price() + " x " + new DecimalFormat("#").format(c.getValue().getMass()) + ", ";
		}
		try {
			request.setCharacterEncoding("UTF-8");response.setContentType("text/html");response.setCharacterEncoding("UTF-8");
			response.getWriter().print("<div class=\"cart-section mt-150 mb-150\">\r\n" + 
					"		<div class=\"container\">\r\n" + 
					"			<div class=\"row\">\r\n" + 
					"				<div class=\"col-lg-8 col-md-12\">\r\n" + 
					"					<div class=\"cart-table-wrap\">\r\n" + 
					"						<table class=\"cart-table\">\r\n" + 
					"							<thead class=\"cart-table-head\">\r\n" + 
					"								<tr class=\"table-head-row\">\r\n" + 
					"									<th class=\"product-image\">Ảnh sản phẩm</th>\r\n" + 
					"									<th class=\"product-name\">Tên sản phẩm</th>\r\n" + 
					"									<th class=\"product-total\">Khối lượng (Kg)</th>\r\n" + 
					"									<th class=\"product-total\">Khuyến mãi (%)</th>\r\n" + 
					"									<th class=\"product-price\">Đơn giá</th>\r\n" + 
					"									<th class=\"product-price\">Thành tiền</th>\r\n" + 
					"									<th class=\"product-remove\"></th>\r\n" + 
					"								</tr>\r\n" + 
					"							</thead>\r\n" + 
					"							<tbody>\r\n");
			for(Product p : arrProduct) {
					response.getWriter().print(
					"									<tr class=\"table-body-row\">\r\n" + 
					"										<td class=\"product-image\"><img src=\"/freshfruit/resources/img/"+p.getProduct_image()+"\" alt=\"\"></td>\r\n" + 
					"										<td class=\"product-name\">"+p.getProduct_name()+"</td>\r\n" + 
					"										<td class=\"product-total\">"+p.getProduct_mass()+"</td>\r\n" + 
					"										<td class=\"product-total\">"+p.getProduct_promotion()+"</td>\r\n" + 
					"										<td class=\"product-price\">"+(p.getProduct_price()-(p.getProduct_price()*p.getProduct_promotion())/100)+"</td>\r\n" + 
					"										<c:set var=\"money\" value=\""+(p.getProduct_price()-(p.getProduct_price()*p.getProduct_promotion())/100)*p.getProduct_mass()+"\"></c:set>\r\n" + 
					"										<td class=\"product-price\">"+(p.getProduct_price()-(p.getProduct_price()*p.getProduct_promotion())/100)*p.getProduct_mass()+"</td>\r\n" + 
					"										<td class=\"product-remove\"><a href=\"javascript:void(0)\" onclick=\"del(${arr.product_id})\"><i class=\"far fa-window-close\"></i></a></td>\r\n" + 
					"									</tr>\r\n");
			}
					response.getWriter().print(
					"							</tbody>\r\n" + 
					"						</table>\r\n" + 
					"					</div>\r\n" + 
					"				</div>\r\n" + 
					"				<div class=\"col-lg-4\">\r\n" + 
					"					<div class=\"total-section\">\r\n" + 
					"						<table class=\"total-table\">\r\n" + 
					"							<thead class=\"total-table-head\">\r\n" + 
					"								<tr class=\"table-total-row\">\r\n" + 
					"									<th>Chi tiết đơn</th>\r\n" + 
					"									<th>Giá</th>\r\n" + 
					"								</tr>\r\n" + 
					"							</thead>\r\n" + 
					"							<tbody>\r\n" + 
					"								<tr class=\"total-data\">\r\n" + 
					"									<td><strong>Tổng tiền cho đơn hàng (Chưa trừ khuyến mãi)	: </strong></td>\r\n" + 
					"												<td class=\"product-price\">"+total+"</td>\r\n" + 
					"								</tr>\r\n" + 
					"								<tr class=\"total-data\">\r\n" + 
					"									<td><strong>Số tiền cần thanh toán (Đã trừ khuyến mãi) : </strong></td>\r\n" + 
					"												<td class=\"product-price\">"+totalPromotion+"</td>\r\n" + 
					"								</tr>\r\n" + 
					"							</tbody>\r\n" + 
					"						</table>\r\n" + 
					"						<div class=\"cart-buttons\">\r\n" + 
					"									<form action=\"https://www.sandbox.paypal.com/cgi-bin/webscr\" method=\"post\">\r\n" + 
					"							            <input type=\"hidden\" name=\"business\" value=\"hungpbc99@gmail.com\">\r\n" + 
					"							            <input type=\"hidden\" name=\"cmd\" value=\"_xclick\">\r\n" + 
					"							            <input type=\"hidden\" name=\"item_name\" value=\"HoaDonMuaHang\">\r\n" + 
					"							            <input type=\"number\" hidden=\"hidden\" name=\"amount\" placeholder=\"Nhập số tiền vào\" value=\""+totalPromotion+"\">\r\n" + 
					"							            <input type=\"hidden\" name=\"currency_code\" value=\"USD\">\r\n" + 
					"							            <input type=\"hidden\" name=\"return\" value=\"http://localhost:8080/freshfruit/cart/payment/success\">\r\n" + 
					"							            <input type=\"hidden\" name=\"cancel_return\" value=\"http://localhost/demothanhtoanonline/loi.html\">\r\n" + 
					"							            <input type=\"submit\" name=\"submit\" value=\"Thanh toán Paypal\">\r\n" + 
					"									</form>\r\n" + 
					"						</div>\r\n" + 
					"					</div>\r\n" + 
					"				</div>\r\n" + 
					"			</div>\r\n" + 
					"		</div>\r\n" + 
					"	</div>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping(URLConstant.FRESH_FRUIT_PAYMENT_SUCCESS)
	public String paymentSuccess(HttpSession session, Model model, HttpServletRequest request) {
		User user = (User)request.getSession().getAttribute("user");
		String bill_product = "";
		double totalTemp = 0, totalPromotionTemp = 0;
		ArrayList<Product> arrProduct = new ArrayList<Product>();
		Cart cart = (Cart)session.getAttribute("cart");
		double total = 0,totalPromotion = 0;
		for(Map.Entry<Integer, Item> c : cart.getCartItem().entrySet()) {
			arrProduct.add(new Product(
															c.getValue().getProduct().getProduct_id(),
															c.getValue().getProduct().getProduct_name(),
															c.getValue().getProduct().getProduct_price(),
															c.getValue().getProduct().getProduct_promotion(),
															c.getValue().getProduct().getProduct_description(),
															c.getValue().getProduct().getProduct_image(),
															c.getValue().getMass(),
															c.getValue().getProduct().getMenu_id()
															)
										);
			total += c.getValue().getProduct().getProduct_price()*c.getValue().getMass();
			totalPromotion += (c.getValue().getProduct().getProduct_price() -
											 (c.getValue().getProduct().getProduct_price() *
											  c.getValue().getProduct().getProduct_promotion())/100) *
											  c.getValue().getMass();
			bill_product += c.getValue().getProduct().getProduct_name() + " - " + c.getValue().getProduct().getProduct_price() + " x " + new DecimalFormat("#").format(c.getValue().getMass()) + ", ";
		}
		billService.save(new Bill(0, new SimpleDateFormat("yyyy/MM/dd").format(new Date().getTime()), bill_product, total, totalPromotion, user.getInformation(), user.getId(), user.getFullname()));
		session.removeAttribute("cart");
		email.sendSimpleEmail(user.getEmail(), totalPromotion);
		return "payment.success";
	}
}
