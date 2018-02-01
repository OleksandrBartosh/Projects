package ua.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import ua.entity.Cargo;
import ua.entity.Transporter;
import ua.model.view.CargoView;
import ua.model.view.TransporterIndexView;
import ua.model.view.TransporterView;

public interface TransporterRepository extends JpaNameRepository<Transporter, Integer>{

	@Query(value="SELECT new ua.model.view.TransporterIndexView(t.id, t.rate, t.count, t.photoUrl, t.version, t.name, b.name, m.name, t.maxWeight) FROM Transporter t JOIN t.model m JOIN m.brand b WHERE t.status = ua.entity.Status.FREE",
			countQuery="SELECT count(t.id) FROM Transporter t JOIN t.model m JOIN m.brand b WHERE t.status = ua.entity.Status.FREE")
	Page<TransporterIndexView> findAllPage(Pageable pageable);
	
	@Query("SELECT new ua.model.view.TransporterIndexView(t.id, t.rate, t.count, t.photoUrl, t.version, t.name, b.name, m.name, t.maxWeight) FROM Transporter t JOIN t.model m JOIN m.brand b WHERE t.count > 10 ORDER BY t.rate DESC")
	List<TransporterIndexView> findAllView();
	
	@Query("SELECT new ua.model.view.TransporterView(t.id, t.rate, t.count, t.photoUrl, t.version, t.name, t.surname, t.age, t.phone, b.name, m.name, t.maxWeight, t.carAge, c.name, t.dateArrive, t.registrationDate, t.status) FROM Transporter t JOIN t.model m JOIN t.cityArrive c JOIN m.brand b WHERE t.id=?1")
	TransporterView findById(Integer id);
	
	@Query("SELECT t FROM Transporter t JOIN t.user u WHERE u.email=?1")
	Transporter getTransporter(String email);
	
	@Query("SELECT new ua.model.view.CargoView(c.id, g.name, c.weight, c.height, c.width, c.length, cFrom.name, cTo.name, c.price, c.creationDate) FROM Cargo c JOIN c.owner.user u JOIN c.goods g JOIN c.cityFrom cFrom JOIN c.cityTo cTo WHERE u.email=?1 AND c.status = ua.entity.Status.FREE")
	List<CargoView> cargoViews(String email);

	@Query("SELECT t FROM Transporter t JOIN t.user u WHERE u.email=?1")
	Transporter getTansporter(String email);
	
	@Query("SELECT c FROM Cargo c WHERE c.id=?1")
	Cargo cargo(Integer id);
	
	@Query("SELECT name FROM Model")
	List<String> findAllModels();
	
	@Query("SELECT name FROM Brand")
	List<String> findAllBrands();
	
	@Query("SELECT name FROM City")
	List<String> findAllCities();
}