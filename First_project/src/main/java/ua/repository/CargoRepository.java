package ua.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ua.entity.Cargo;
import ua.entity.Offer;
import ua.entity.Owner;
import ua.model.view.CargoView;
import ua.model.view.OwnerView;
import ua.model.view.TransporterIndexView;

public interface CargoRepository extends JpaRepository<Cargo, Integer>{

	@Query("SELECT g.name FROM Goods g")
	List<String> findAllGoods();
	
	@Query(value="SELECT new ua.model.view.CargoView(c.id, g.name, c.weight, c.height, c.width, c.length, cFrom.name, cTo.name, c.price, c.creationDate) FROM Cargo c JOIN c.owner.user u JOIN c.goods g JOIN c.cityFrom cFrom JOIN c.cityTo cTo WHERE u.email=?1",
			countQuery="SELECT (c.id) FROM Cargo c JOIN c.owner.user u JOIN c.goods g JOIN c.cityFrom cFrom JOIN c.cityTo cTo WHERE u.email=?1")
	Page<CargoView> findOwnerCargo(String email, Pageable pageable);
	
	@Query("SELECT c.name FROM City c")
	List<String> findAllCity();
	
	@Query("SELECT new ua.model.view.CargoView(c.id, g.name, c.weight, c.height, c.width, c.length, cFrom.name, cTo.name, c.price, c.creationDate) FROM Cargo c JOIN c.goods g JOIN c.cityFrom cFrom JOIN c.cityTo cTo")
	List<CargoView> findAllView();
	
	@Query(value="SELECT new ua.model.view.CargoView(c.id, g.name, c.weight, c.height, c.width, c.length, cFrom.name, cTo.name, c.price, c.creationDate) FROM Cargo c JOIN c.goods g JOIN c.cityFrom cFrom JOIN c.cityTo cTo WHERE c.status = ua.entity.Status.FREE",
	countQuery="SELECT (c.id) FROM Cargo c JOIN c.goods g JOIN c.cityFrom cFrom JOIN c.cityTo cTo WHERE c.status = ua.entity.Status.FREE"		)
	Page<CargoView> findAllView(Pageable pageable);
	
	@Query("SELECT new ua.model.view.CargoView(c.id, g.name, c.weight, c.height, c.width, c.length, cFrom.name, cTo.name, c.price, c.creationDate) FROM Cargo c JOIN c.goods g JOIN c.cityFrom cFrom JOIN c.cityTo cTo WHERE c.id=?1")
	CargoView findById(Integer id);

	@Query("SELECT c FROM Cargo c JOIN FETCH c.cityTo cTo JOIN FETCH c.cityFrom cFrom JOIN FETCH c.goods g LEFT JOIN FETCH c.owner o WHERE c.id=?1")
	Cargo findOneRequest(Integer id);
	
	@Query("SELECT o FROM User u JOIN u.owner o WHERE u.email=?1")
	Owner findOwner(String email);
	
	@Query("SELECT new ua.model.view.OwnerView(o.id, o.rate, o.photoUrl, o.version, o.name, o.surname, o.phone, o.count, o.address, o.registrationDate) FROM Cargo c JOIN c.owner o WHERE c.id=?1")
	OwnerView findThisOwner(Integer id);
	
	@Query("SELECT new ua.model.view.TransporterIndexView(t.id, t.rate, t.count, t.photoUrl, t.version, t.name, b.name, m.name, t.maxWeight) FROM Offer o JOIN o.cargo c JOIN o.transporter t JOIN t.model m JOIN m.brand b WHERE c.id=?1 AND o.offerStatus <> ua.entity.OfferStatus.DECLINE")
	List<TransporterIndexView> findOffersById(Integer id);
	
	@Query("SELECT o FROM Offer o JOIN o.transporter t JOIN o.cargo c WHERE c.id=?1")
	List<Offer> checkTransporterConfirm(Integer id);
	
	@Query("SELECT new ua.model.view.TransporterIndexView(t.id, t.rate, t.count, t.photoUrl, t.version, t.name, b.name, m.name, t.maxWeight) FROM Offer o JOIN o.cargo c JOIN o.transporter t JOIN t.model m JOIN m.brand b WHERE c.id=?1 AND o.offerStatus = ua.entity.OfferStatus.ACCEPT")
	List<TransporterIndexView> findAcceptTransporter(Integer id);
	
	@Query("SELECT new ua.model.view.TransporterIndexView(t.id, t.rate, t.count, t.photoUrl, t.version, t.name, b.name, m.name, t.maxWeight) FROM Offer o JOIN o.cargo c JOIN o.transporter t JOIN t.model m JOIN m.brand b WHERE c.id=?1 AND o.offerStatus = ua.entity.OfferStatus.COMPLETE")
	List<TransporterIndexView> findInWayTransporter(Integer id);
	
	@Query("SELECT new ua.model.view.CargoView(c.id, g.name, c.weight, c.height, c.width, c.length, cFrom.name, cTo.name, c.price, c.creationDate) FROM Offer o JOIN o.cargo c JOIN c.goods g JOIN c.cityFrom cFrom JOIN c.cityTo cTo WHERE c.id=?1 AND o.offerStatus = ua.entity.OfferStatus.ACCEPT AND o.transporterConfirm = 0")
	List<CargoView> findAcceptOwner(Integer id);
	
	@Query("SELECT new ua.model.view.CargoView(c.id, g.name, c.weight, c.height, c.width, c.length, cFrom.name, cTo.name, c.price, c.creationDate) FROM Offer o JOIN o.cargo c JOIN c.goods g JOIN c.cityFrom cFrom JOIN c.cityTo cTo WHERE c.id=?1 AND o.offerStatus = ua.entity.OfferStatus.COMPLETE")
	List<CargoView> checkConfirm(Integer id);
}