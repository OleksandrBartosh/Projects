package ua.service;

import java.util.List;

import ua.entity.Role;
import ua.model.request.OwnerRequest;
import ua.model.request.TransporterRequest;
import ua.model.view.CargoView;
import ua.model.view.OwnerProfileView;
import ua.model.view.TransporterProfileView;

public interface UserService{

	void save(TransporterRequest request);
	
	void save(OwnerRequest request);

	List<String> findAllBrands();

	List<String> findAllModels();

	List<String> findAllCity();
	
	List<String> findAllGoods();
	
	List<CargoView> findAllCargo();
	
	Role determineRole(String email);

	OwnerProfileView findOwner(String email);

	TransporterProfileView findTransporter(String email);
	
	CargoView getCurrentCargo(String email);
	
}