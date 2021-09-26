package project.controllers.freshfruit;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import project.constant.URLConstant;
import project.controllers.model.Contact;
import project.controllers.model.News;
import project.controllers.service.ContactService;
import project.controllers.service.NewsService;

@Controller
@RequestMapping(URLConstant.FRESH_FRUIT_CONTACT)
public class FreshFruitContactController {

	@Autowired
	private ContactService contactService;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private NewsService newsService;
	
	@GetMapping()
	public String index(Model model) {
		model.addAttribute("listNews",newsService.getAll());
		return "freshfruit.contact";
	}
	
	@PostMapping
	public String insertContact(@Valid @ModelAttribute("c") Contact contact, BindingResult bs, RedirectAttributes ra, Model model) {
		if(bs.hasErrors()) {
			List<News> listNews = new ArrayList<News>();
			listNews = newsService.getAll();
			model.addAttribute("listNews",listNews);
			return "freshfruit.contact";
		}
		if(contactService.save(contact) > 0) {
			ra.addFlashAttribute("msg", messageSource.getMessage("msg.success", null, Locale.getDefault()));
			return "redirect:/contact";
		}else {
			ra.addFlashAttribute("msg", messageSource.getMessage("msg.error", null, Locale.getDefault()));
			return "redirect:/contact";
		}
	}
}
