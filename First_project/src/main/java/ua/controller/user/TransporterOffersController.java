package ua.controller.user;

import java.security.Principal;
import java.util.Iterator;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import ua.entity.Offer;
import ua.entity.OfferStatus;
import ua.service.OfferService;

@Controller
@RequestMapping("/myoffers")
public class TransporterOffersController {

	private final OfferService offerService;
	
	public TransporterOffersController(OfferService offerService) {
		this.offerService = offerService;
	}

	@GetMapping
	public String showOffers(Model model, Principal principal, Pageable pageable) {
		model.addAttribute("cargos", offerService.myOffers(principal.getName()));
		return "transporter-offers";
	}
	
	@GetMapping("/{id}")
	public String showOffer(@PathVariable Integer id, Model model) {
		model.addAttribute("owner", offerService.findThisOwner(id));
		List<Offer> offers = offerService.offersTransporter(id);
		Iterator<Offer> iterator = offers.iterator();
		while (iterator.hasNext()) {
			Offer offer = (Offer) iterator.next();
			if (offer.getTransporterConfirm()==1) {
				model.addAttribute("cargoc", offerService.checkConfirm(id));
			} else if(offer.getOfferStatus()==OfferStatus.ACCEPT){
				model.addAttribute("cargo", offerService.findAcceptOwner(id));
			} else {
				model.addAttribute("cargos", offerService.findById(id));
			}
		}
		return "transporter-offer";
	}

	@GetMapping("/decline/{id_cargo}")
	public String decline(@PathVariable("id_cargo") Integer id_cargo, Principal principal, Model model) {
		offerService.changeToDecline(id_cargo, principal.getName());
		return "redirect:/myoffers";
	}

	@GetMapping("/accept/{id_cargo}")
	public String accept(@PathVariable("id_cargo") Integer id_cargo, Principal principal, Model model) {
		offerService.changeToAccept(id_cargo, principal.getName());
		return "redirect:/myoffers";
	}
	
	@GetMapping("/confirm/{id_cargo}")
	public String confirm(@PathVariable("id_cargo") Integer id_cargo, Principal principal, Model model, SessionStatus status) {
		offerService.ownerChangeToConfirm(id_cargo, principal.getName());
		status.setComplete();
		return "redirect:/mycargo";
	}
}
