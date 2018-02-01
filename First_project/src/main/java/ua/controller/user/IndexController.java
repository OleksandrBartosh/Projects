package ua.controller.user;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.service.TransporterService;

@Controller
@RequestMapping("/")
public class IndexController {

	private final TransporterService service;

	public IndexController(TransporterService service) {
		this.service = service;
	}

	@GetMapping
	public String show(Model model, Principal principal) {
		model.addAttribute("transporters", service.findAllView());
		if(principal!=null) {
			model.addAttribute("message", "Hello, "+principal.getName()+ "!");
		}else {
			model.addAttribute("message", "Hello, Unauthorize user");
		}
		return "index";
	}

	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}
}
