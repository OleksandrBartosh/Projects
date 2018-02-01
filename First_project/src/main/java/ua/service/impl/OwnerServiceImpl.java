package ua.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.model.filter.OwnerFilter;
import ua.model.view.CargoView;
import ua.model.view.OwnerView;
import ua.repository.OwnerRepository;
import ua.repository.OwnerViewRepository;
import ua.service.OwnerService;

@Service
public class OwnerServiceImpl implements OwnerService{

	private final OwnerRepository repository;
	
	private final OwnerViewRepository viewRepository;
	
	public OwnerServiceImpl(OwnerRepository repository, OwnerViewRepository viewRepository) {
		this.repository = repository;
		this.viewRepository = viewRepository;
	}

	@Override
	public List<OwnerView> findAllView() {
		return repository.findAllView();
	}

	@Override
	public OwnerView findById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public Page<OwnerView> findAllView(Pageable pageable) {
		return repository.findAllView(pageable);
	}

	@Override
	public Page<CargoView> findAllCargo(Integer id, Pageable pageable) {
		return repository.findAllCargo(id, pageable);
	}

	@Override
	public Page<OwnerView> findAllView(OwnerFilter filter, Pageable pageable) {
		return viewRepository.findAllView(filter, pageable);
	}

}
