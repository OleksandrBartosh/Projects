package ua.controller.user;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import ua.entity.Role;
import ua.entity.Status;
import ua.model.request.TransporterCarRequest;
import ua.model.request.TransporterStatusRequest;
import ua.service.CommentsService;
import ua.service.TransporterService;
import ua.service.UserService;

@Controller
@RequestMapping("/profile")
public class ProfileController {

	private final UserService service;
	
	private final CommentsService commentsService;
	
	private final TransporterService transporterService;

	public ProfileController(UserService service, CommentsService commentsService,
			TransporterService transporterService) {
		this.service = service;
		this.commentsService = commentsService;
		this.transporterService = transporterService;
	}

	@ModelAttribute("transporter")
	public TransporterStatusRequest getFormTransporter() {
		return new TransporterStatusRequest();
	}
	
	@GetMapping
	public String getAuthenticatedUser(Principal principal, Model model) {
		if (service.determineRole(principal.getName()) == Role.ROLE_OWNER) {
			model.addAttribute("owner", service.findOwner(principal.getName()));
			model.addAttribute("comments", commentsService.commentsPageOwnerByEmail(principal.getName()));
			return "profile-owner";
		} else {
			model.addAttribute("transporter", service.findTransporter(principal.getName()));
			model.addAttribute("cargo", service.getCurrentCargo(principal.getName()));
			model.addAttribute("comments", commentsService.commentsPageTransporterByEmail(principal.getName()));
			model.addAttribute("statuses", Status.values());
			model.addAttribute("cities", service.findAllCity());
			return "profile-transporter";
		}
	}
	
	@PostMapping
	public String save(@ModelAttribute("transporter") TransporterStatusRequest request, Principal principal, SessionStatus status){
		transporterService.saveNewStatus(request, principal.getName());
		status.setComplete();
		return "redirect:/profile";
	}
	
	@GetMapping("/edit")
	public String edit(Model model, Principal principal) {
		model.addAttribute("transporter", service.findTransporter(principal.getName()));
		model.addAttribute("brands", service.findAllBrands());
		model.addAttribute("models", service.findAllModels());
		return "profile-transporter-edit";
	}
	
	@ModelAttribute("transporterCar")
	public TransporterCarRequest getFormTransporterCar() {
		return new TransporterCarRequest();
	}
	
	@PostMapping("/edit")
	public String saveCar(@ModelAttribute("transporterCar") TransporterCarRequest request, Principal principal, SessionStatus status){
		transporterService.saveNewCar(request, principal.getName());
		status.setComplete();
		return "redirect:/profile";
	}
}