package ua.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ua.model.request.OwnerRequest;
import ua.service.UserService;

@Controller
@RequestMapping("/registration/owner")
@SessionAttributes("owner")
public class OwnerRegistrationController {

	private final UserService service;

	public OwnerRegistrationController(UserService service) {
		this.service = service;
	}
	
	@GetMapping
	public String show() {
		return "owner-registration";
	}
	
	@ModelAttribute("owner")
	public OwnerRequest getForm() {
		return new OwnerRequest();
	}

	@PostMapping
	public String save(@ModelAttribute("owner") OwnerRequest request, SessionStatus status) {
		service.save(request);
		status.setComplete();
		return "redirect:/login";
	}
}
