package module.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping({"/","/home/index"})
	public String home() {
		return "redirect:/product/list";
	}
	
	@RequestMapping({"/admin","/admin/home/index"})
	public String admin() {
		return "redirect:/assets/admin/index.html";
	}
	
	@RequestMapping("home/contact")
	public String contact() {
		return "layout/contact";
	}
	
	@RequestMapping("home/about")
	public String about() {
		return "layout/about";
	}
	
}
