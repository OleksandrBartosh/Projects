package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import ua.entity.Cargo;
import ua.entity.Offer;
import ua.entity.Transporter;
import ua.model.view.CargoView;
import ua.model.view.OwnerView;

public interface OfferRepository extends JpaRepository<Offer, Integer>{

	@Query("SELECT c FROM Cargo c WHERE c.id=?1")
	Cargo getCargo(Integer id);
	
	@Query("SELECT t FROM User u JOIN u.transporter t WHERE  u.email=?1")
	Transporter getTransporter(String email);
	
	@Query("SELECT t FROM Transporter t WHERE t.id=?1")
	Transporter getTransporterId(Integer id);
	
	@Query("SELECT o FROM Offer o JOIN o.cargo c WHERE c.id=?1")
	List<Offer> transportersOffers(Integer id_cargo); 
	
	@Query("SELECT o FROM Offer o JOIN o.cargo c WHERE c.id=?1")
	List<Offer> ownersOffers(Integer id); 
	
	@Query("SELECT o FROM Offer o JOIN o.transporter t JOIN o.cargo c WHERE t.id=?2 AND c.id=?1")
	Offer getCurrentOffer(Integer id_cargo, Integer id_transporter);
	
	@Query("SELECT t FROM Transporter t WHERE t.id=?1")
	Transporter getTransporter(Integer id_transporter);
	
	//offers to transporter from owners
	@Query("SELECT new ua.model.view.CargoView(c.id, g.name, c.weight, c.height, c.width, c.length, cFrom.name, cTo.name, c.price, c.creationDate) FROM Offer o JOIN o.transporter t JOIN o.cargo c JOIN t.user u JOIN c.goods g JOIN c.cityFrom cFrom JOIN c.cityTo cTo WHERE u.email=?1  AND o.offerStatus != ua.entity.OfferStatus.DECLINE")
	List<CargoView> myOffers(String email);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM Offer o WHERE o.id=?1")
	void deleteOffer(Integer id);
	
	@Query("SELECT o FROM Offer o JOIN o.transporter.user u JOIN o.cargo c WHERE c.id=?1 AND u.email=?2")
	Offer getCurrentOffer(Integer id_cargo, String email);
	
	//
	@Query("SELECT o FROM Offer o JOIN o.transporter t WHERE t.id=?1 AND o.offerStatus != ua.entity.OfferStatus.ACCEPT")
	List<Offer> offersToTransporter(Integer id);
	
	@Query("SELECT o FROM Offer o JOIN o.cargo c WHERE c.id=?1")
	List<Offer> offersTransporter(Integer id);
	
	@Query("SELECT new ua.model.view.OwnerView(o.id, o.rate, o.photoUrl, o.version, o.name, o.surname, o.phone, o.count, o.address, o.registrationDate) FROM Cargo c JOIN c.owner o WHERE c.id=?1")
	OwnerView findThisOwner(Integer id);
	
	@Query("SELECT new ua.model.view.CargoView(c.id, g.name, c.weight, c.height, c.width, c.length, cFrom.name, cTo.name, c.price, c.creationDate) FROM Offer o JOIN o.cargo c JOIN c.goods g JOIN c.cityFrom cFrom JOIN c.cityTo cTo WHERE c.id=?1 AND o.leftBy = 1")
	List<CargoView> findById(Integer id);
}