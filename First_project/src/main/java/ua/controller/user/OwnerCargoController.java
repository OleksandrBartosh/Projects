package ua.controller.user;

import java.security.Principal;
import java.util.Iterator;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ua.entity.Offer;
import ua.entity.OfferStatus;
import ua.model.request.CargoRequest;
import ua.service.CargoService;
import ua.service.OfferService;

@Controller
@RequestMapping("/mycargo")
@SessionAttributes("cargo")
public class OwnerCargoController {

	private final CargoService service;

	private final OfferService offerService;

	public OwnerCargoController(CargoService service, OfferService offerService) {
		this.service = service;
		this.offerService = offerService;
	}

	@GetMapping
	public String show(Model model, Principal principal, Pageable pageable) {
		model.addAttribute("cargos", service.findOwnerCargo(principal.getName(), pageable));
		model.addAttribute("cities", service.findAllCity());
		model.addAttribute("goodss", service.findAllGoods());
		return "mycargo";
	}

	@GetMapping("/{id}")
	public String showId(@PathVariable Integer id, Model model) {
		model.addAttribute("cargo", service.findById(id));
		List<Offer> offer = service.checkTransporterConfirm(id);
		Iterator<Offer> iterator = offer.iterator();
		while (iterator.hasNext()) {
			Offer o = (Offer) iterator.next();
			if (o.getOwnerConfirm() == 1) {
				model.addAttribute("transp", service.findInWayTransporter(id));
			} else if (o.getOfferStatus() == OfferStatus.ACCEPT) {
				model.addAttribute("transporter", service.findAcceptTransporter(id));
			} else {
				model.addAttribute("transporters", service.findOffersById(id));
			}
		}
		return "cargo-offers-transporters";
	}

	@GetMapping("/{id_cargo}/accept/{id_transporter}")
	public String accept(@PathVariable("id_cargo") Integer id_cargo,
			@PathVariable("id_transporter") Integer id_transporter, Model model, SessionStatus status) {
		offerService.changeToAccept(id_cargo, id_transporter);
		cancel(status);
		return "redirect:/mycargo";
	}

	@GetMapping("/{id_cargo}/decline/{id_transporter}")
	public String decline(@PathVariable("id_cargo") Integer id_cargo,
			@PathVariable("id_transporter") Integer id_transporter, Model model, SessionStatus status) {
		offerService.changeToDecline(id_cargo, id_transporter);
		cancel(status);
		return "redirect:/mycargo";
	}

	@GetMapping("/{id_cargo}/confirm/{id_transporter}")
	public String confirm(@PathVariable("id_cargo") Integer id_cargo, @PathVariable("id_transporter") Integer id_transporter, Model model, SessionStatus status) {
		offerService.transporterChangeToConfirm(id_cargo, id_transporter);
		cancel(status);
		return "redirect:/mycargo";
	}

	@ModelAttribute("cargo")
	public CargoRequest getForm() {
		return new CargoRequest();
	}

	@PostMapping
	public String save(@ModelAttribute("cargo") CargoRequest request, Principal principal, SessionStatus status) {
		service.save(request, principal.getName());
		status.setComplete();
		return "redirect:/mycargo";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		service.delete(id);
		return "redirect:/mycargo";
	}

	@GetMapping("/update/{id}")
	public String update(@PathVariable Integer id, Model model, Principal principal, Pageable pageable) {
		model.addAttribute("cargo", service.findOne(id));
		return show(model, principal, pageable);
	}

	@GetMapping("/cancel")
	public String cancel(SessionStatus status) {
		status.setComplete();
		return "redirect:/mycargo";
	}
}