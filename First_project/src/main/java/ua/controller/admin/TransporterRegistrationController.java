package ua.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ua.model.request.TransporterRequest;
import ua.service.UserService;

@Controller
@RequestMapping("/registration/transporter")
@SessionAttributes("transporter")
public class TransporterRegistrationController {

	private final UserService service;
	
	public TransporterRegistrationController(UserService service) {
		this.service = service;
	}

	@ModelAttribute("transporter")
	public TransporterRequest getForm() {
		return new TransporterRequest();
	}

	@GetMapping
	public String show(Model model) {
		model.addAttribute("brands", service.findAllBrands());
		model.addAttribute("models", service.findAllModels());
		model.addAttribute("cities", service.findAllCity());
		return "transporter-registration";
	}
	
	@PostMapping
	public String save(@ModelAttribute("transporter") TransporterRequest request, SessionStatus status) {
		service.save(request);
		status.setComplete();
		return "redirect:/login";
	}
}