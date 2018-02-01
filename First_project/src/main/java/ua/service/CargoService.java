package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.Offer;
import ua.entity.Owner;
import ua.model.filter.CargoFilter;
import ua.model.request.CargoRequest;
import ua.model.view.CargoView;
import ua.model.view.OwnerView;
import ua.model.view.TransporterIndexView;

public interface CargoService {

	List<String> findAllGoods();
	
	List<String> findAllCity();
	
	List<CargoView> findAllView();
	
	Page<CargoView> findAllView(Pageable pageable);
	
//	List<CargoView> findOwnerCargo(String email);
	
	Page<CargoView> findOwnerCargo(String email, Pageable pageable);
	
	CargoView findById(Integer id);
	
	CargoRequest findOne(Integer id);
	
	void save(CargoRequest request, String email);

	void delete(Integer id);
	
	Owner findOwner(String email);
	
	OwnerView findThisOwner(Integer id);
	
	List<TransporterIndexView> findOffersById(Integer id);
	
	List<Offer> checkTransporterConfirm(Integer id);
	
	List<TransporterIndexView> findAcceptTransporter(Integer id);
	
	List<TransporterIndexView> findInWayTransporter(Integer id);
//
	Page<CargoView> findAllView(CargoFilter cargoFilter, Pageable pageable);
}
