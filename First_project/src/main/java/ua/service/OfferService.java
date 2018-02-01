package ua.service;

import java.util.List;

import ua.entity.Offer;
import ua.model.view.CargoView;
import ua.model.view.OwnerView;

public interface OfferService {
	
	void getOffer(String email, Integer id);
	
	void changeToAccept(Integer id_cargo, Integer id_transporter);

	void changeToDecline(Integer id_cargo, Integer id_transporter);

	void transporterChangeToConfirm(Integer id_cargo, Integer id_transporter);

	List<CargoView> myOffers(String email);

	void changeToDecline(Integer id_cargo, String email);

	void changeToAccept(Integer id_cargo, String name);

	List<Offer> offersTransporter(Integer id);
	
	List<CargoView> findAcceptOwner(Integer id);
	
	OwnerView findThisOwner(Integer id);
	
	List<CargoView>findById(Integer id);
	
	List<CargoView> checkConfirm(Integer id);

	void ownerChangeToConfirm(Integer id_cargo, String email);
}
