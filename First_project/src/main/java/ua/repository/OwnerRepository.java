package ua.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import ua.entity.Owner;
import ua.model.view.CargoView;
import ua.model.view.OwnerView;

public interface OwnerRepository extends JpaNameRepository<Owner, Integer> {

	@Query("SELECT new ua.model.view.OwnerView(o.id, o.rate, o.photoUrl, o.version, o.name, o.surname, o.phone, o.count, o.address, o.registrationDate) FROM Owner o")
	List<OwnerView> findAllView();
	
	@Query(value="SELECT new ua.model.view.OwnerView(o.id, o.rate, o.photoUrl, o.version, o.name, o.surname, o.phone, o.count, o.address, o.registrationDate) FROM Owner o",
			countQuery="SELECT count(o.id) FROM Owner o")
	Page<OwnerView> findAllView(Pageable pageable);
	
	@Query("SELECT new ua.model.view.OwnerView(o.id, o.rate, o.photoUrl, o.version, o.name, o.surname, o.phone, o.count, o.address, o.registrationDate) FROM Owner o WHERE o.id=?1")
	OwnerView findById(Integer id);
	
	@Query(value="SELECT new ua.model.view.CargoView(c.id, g.name, c.weight, c.height, c.width, c.length, cFrom.name, cTo.name, c.price, c.creationDate) FROM Cargo c JOIN c.goods g JOIN c.cityFrom cFrom JOIN c.cityTo cTo JOIN c.owner o WHERE c.status = ua.entity.Status.FREE AND o.id=?1",
	countQuery="SELECT (c.id) FROM Cargo c JOIN c.goods g JOIN c.cityFrom cFrom JOIN c.cityTo cTo JOIN c.owner o WHERE c.status = ua.entity.Status.FREE AND o.id=?1")
	Page<CargoView> findAllCargo(Integer id, Pageable pageable);

}
