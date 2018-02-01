package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.model.filter.TransporterFilter;
import ua.model.request.ChooseCargoRequest;
import ua.model.request.TransporterCarRequest;
import ua.model.request.TransporterStatusRequest;
import ua.model.view.CargoView;
import ua.model.view.TransporterIndexView;
import ua.model.view.TransporterView;

public interface TransporterService {

	List<TransporterIndexView> findAllView();
	
	Page<TransporterIndexView> findAllPage(Pageable pageable);
	
	//
	Page<TransporterIndexView> findAll(TransporterFilter filter, Pageable pageable);
	
	TransporterView findById(Integer id);

	void saveNewStatus(TransporterStatusRequest request, String email);
	
	void saveNewCar(TransporterCarRequest carRequest, String email);

	List<CargoView> cargoViews(String email);

	void saveOfferToTransporter(ChooseCargoRequest request, String email);
	
	List<String> findAllModels();
	
	List<String> findAllBrands();
	
	List<String> findAllCities();
}
