package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.model.filter.OwnerFilter;
import ua.model.view.CargoView;
import ua.model.view.OwnerView;

public interface OwnerService {

	Page<OwnerView> findAllView(Pageable pageable);
	
	Page<CargoView> findAllCargo(Integer id, Pageable pageable);
	
	List<OwnerView> findAllView();

	OwnerView findById(Integer id);

	Page<OwnerView> findAllView(OwnerFilter filter, Pageable pageable);
}
