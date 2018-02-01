package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ua.entity.Role;
import ua.entity.User;
import ua.model.view.CargoView;
import ua.model.view.OwnerProfileView;
import ua.model.view.TransporterProfileView;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByEmail(String email);
	
	@Query("SELECT b.name FROM Brand b")
	List<String> findAllBrands();
	
	@Query("SELECT g.name FROM Goods g")
	List<String> findAllGoods();
	
	@Query("SELECT m.name FROM Model m")
	List<String> findAllModels();

	@Query("SELECT c.name FROM City c")
	List<String> findAllCity();
	
	@Query("SELECT new ua.model.view.CargoView(c.id, g.name, c.weight, c.height, c.width, c.length, cFrom.name, cTo.name, c.price, c.creationDate) FROM Cargo c JOIN c.goods g JOIN c.cityFrom cFrom JOIN c.cityTo cTo")
	List<CargoView> findAllCargo();

	@Query("SELECT new ua.model.view.CargoView(c.id, g.name, c.weight, c.height, c.width, c.length, cFrom.name, cTo.name, c.price, c.creationDate) FROM Cargo c JOIN c.goods g JOIN c.cityFrom cFrom JOIN c.cityTo cTo JOIN c.transporter.user u WHERE u.email=?1") 
	CargoView getCurrentCargo(String email);
	
	@Query("SELECT new ua.model.view.OwnerProfileView(o.id, o.rate, o.photoUrl, o.version, u.email, o.name, o.surname, o.phone, o.count, o.address, o.registrationDate) FROM User u JOIN u.owner o WHERE u.email=?1")
	OwnerProfileView findOwner(String email);
	
	@Query("SELECT new ua.model.view.TransporterProfileView(t.id, t.rate, t.count, t.photoUrl, t.version, u.email, t.name, t.surname, t.age, t.phone, b.name, m.name, t.maxWeight, t.carAge, c.name, t.dateArrive, t.registrationDate, t.status) FROM User u JOIN u.transporter t JOIN t.model m JOIN m.brand b JOIN t.cityArrive c WHERE u.email=?1")
	TransporterProfileView findTransporter(String email);

	@Query("SELECT u.role FROM User u WHERE u.email=?1")
	Role determineRole(String email);

}