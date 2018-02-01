package ua.controller.user;

import java.security.Principal;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import ua.model.filter.OwnerFilter;
import ua.model.request.CommentRequest;
import ua.service.CommentsService;
import ua.service.OwnerService;

@Controller
@RequestMapping("/owner")
public class OwnerController {

	private final OwnerService service;
	
	private final CommentsService commentsService;

	public OwnerController(OwnerService service, CommentsService commentsService) {
		this.service = service;
		this.commentsService = commentsService;
	}

	@GetMapping
	public String show(Model model, @ModelAttribute("ownerFilter") OwnerFilter filter, Pageable pageable) {
		model.addAttribute("owners", service.findAllView(filter, pageable));
		return "owner";
	}
	
	@ModelAttribute("comment")
	public CommentRequest getForm() {
		return new CommentRequest();
	}
	@ModelAttribute("ownerFilter")
	public OwnerFilter getFilter() {
		return new OwnerFilter();
	}
	
	@GetMapping("/{id}")
	public String showId(@PathVariable Integer id, Model model, Pageable pageable) {
		model.addAttribute("owner", service.findById(id));
		model.addAttribute("cargos", service.findAllCargo(id, pageable));
		model.addAttribute("comments", commentsService.commentsPageOwner(id, pageable));
		return "owner-id";
	}
	
	@PostMapping("/{id}")
	public String saveComment(@ModelAttribute("comment") CommentRequest request, Principal principal, @PathVariable Integer id, SessionStatus status) {
			commentsService.saveCommentByTransporter(request, principal.getName(), id);
			status.setComplete();
			return "redirect:/owner/{id}";
	}
}
