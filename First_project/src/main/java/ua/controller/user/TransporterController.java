package ua.controller.user;

import java.security.Principal;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import ua.model.filter.TransporterFilter;
import ua.model.request.ChooseCargoRequest;
import ua.model.request.CommentRequest;
import ua.service.CommentsService;
import ua.service.TransporterService;

@Controller
@RequestMapping("/transporter")
public class TransporterController {

	private final TransporterService service;
	
	private final CommentsService commentsService;

	public TransporterController(TransporterService service, CommentsService commentsService) {
		this.service = service;
		this.commentsService = commentsService;
	}

	@GetMapping
	public String show(Model model,@ModelAttribute("transporterFilter") TransporterFilter filter, @PageableDefault(sort="rate", direction=Sort.Direction.DESC) Pageable pageable) {
		model.addAttribute("models", service.findAllModels());
		model.addAttribute("brands", service.findAllBrands());
		model.addAttribute("cities", service.findAllCities());
		model.addAttribute("transporters", service.findAll(filter, pageable));
		return "transporters";
	}
	
	@ModelAttribute("comment")
	public CommentRequest getForm() {
		return new CommentRequest();
	}
	
	@ModelAttribute("cargo")
	public ChooseCargoRequest getFormCargo() {
		return new ChooseCargoRequest();
	}
	
	@ModelAttribute("transporterFilter")
	public TransporterFilter getFilter() {
		return new TransporterFilter();
	}
	
	@PostMapping("/{id}/offer")
	public String saveCargo(@ModelAttribute("cargo") ChooseCargoRequest request, Principal principal,SessionStatus status) {
			service.saveOfferToTransporter(request, principal.getName());
			status.setComplete();
			return "redirect:/transporter/{id}";
	}
	
	@GetMapping("/{id}")
	public String showId(@PathVariable Integer id, Model model,  Principal principal,Pageable pageable) {
		model.addAttribute("transporters", service.findById(id));
		model.addAttribute("comments", commentsService.commentsPageTransporter(id, pageable));
		model.addAttribute("cargos", service.cargoViews(principal.getName()));
		return "transporter-id";
	}
	
	@PostMapping("/{id}")
	public String saveComment(@ModelAttribute("comment") CommentRequest request, Principal principal, @PathVariable Integer id, Model model,Pageable pageable, SessionStatus status, BindingResult br) {
		if(br.hasErrors()) return showId(id, model, principal, pageable);
			commentsService.saveCommentByOwner(request, principal.getName(), id);
			status.setComplete();
			return "redirect:/transporter/{id}";
	}
}