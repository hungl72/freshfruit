package project.controllers.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestPassController {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@GetMapping("create-pass")
	@ResponseBody
	public String index() {
		return bCryptPasswordEncoder.encode("12345678");
	}
	
}
