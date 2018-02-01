package ua.controller.user;

import java.security.Principal;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.model.filter.CargoFilter;
import ua.service.CargoService;
import ua.service.OfferService;

@Controller
@RequestMapping("/cargo")
public class CargoController {

	private final CargoService service;
	
	private final OfferService offerService;

	public CargoController(CargoService service, OfferService offerService) {
		this.service = service;
		this.offerService = offerService;
	}

	@ModelAttribute("cargoFilter")
	public CargoFilter cargoFilter() {
		return new CargoFilter();
	}
	
	@GetMapping 
	public String show(Model model, @ModelAttribute("cargoFilter") CargoFilter cargoFilter, @PageableDefault Pageable pageable) {
		model.addAttribute("goodses", service.findAllGoods());
		model.addAttribute("cities", service.findAllCity());
		model.addAttribute("cargos", service.findAllView(cargoFilter ,pageable));
		return "cargo";
	}	
	
	@GetMapping("/{id}")
	public String showId(@PathVariable Integer id, Model model) {
		model.addAttribute("cargo", service.findById(id));
		model.addAttribute("owner", service.findThisOwner(id));
		return "cargo-id";
	}	
	
	@GetMapping("/{id}/get")
	public String showId(@PathVariable Integer id, Principal principal) {
		offerService.getOffer(principal.getName(), id);
		return "redirect:/cargo";
	}
}