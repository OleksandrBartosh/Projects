package ua.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.entity.Cargo;
import ua.entity.Offer;
import ua.entity.Owner;
import ua.entity.Status;
import ua.model.filter.CargoFilter;
import ua.model.request.CargoRequest;
import ua.model.view.CargoView;
import ua.model.view.OwnerView;
import ua.model.view.TransporterIndexView;
import ua.repository.CargoRepository;
import ua.repository.CargoViewRepository;
import ua.service.CargoService;

@Service
public class CargoServiceImpl implements CargoService{
	
	private final CargoRepository repository;
	
	private final CargoViewRepository viewRepository;

	public CargoServiceImpl(CargoRepository repository, CargoViewRepository viewRepository) {
		this.repository = repository;
		this.viewRepository = viewRepository;
	}

	@Override
	public List<String> findAllGoods() {
		return repository.findAllGoods();
	}
	
	@Override
	public Page<CargoView> findAllView(CargoFilter cargoFilter, Pageable pageable) {
		return viewRepository.findAllView(cargoFilter, pageable);
	}
	
	@Override
	public Page<CargoView> findOwnerCargo(String email, Pageable pageable) {
		return repository.findOwnerCargo(email, pageable);
	}

	@Override
	public List<String> findAllCity() {
		return repository.findAllCity();
	}

	@Override
	public List<CargoView> findAllView() {
		return repository.findAllView();
	}

	@Override
	public Page<CargoView> findAllView(Pageable pageable) {
		return repository.findAllView(pageable);
	}
	
	@Override
	public CargoView findById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public void save(CargoRequest request, String email) {
		Cargo cargo = new Cargo();
		cargo.setCityFrom(request.getCityFrom());
		cargo.setCityTo(request.getCityTo());
		cargo.setGoods(request.getGoods());
		cargo.setHeight(Integer.valueOf(request.getHeight()));
		cargo.setId(request.getId());
		cargo.setLength(Integer.valueOf(request.getLength()));
		cargo.setOwner(findOwner(email));
		cargo.setPrice(new BigDecimal(request.getPrice().replace(',', '.')));
		cargo.setWeight(Integer.valueOf(request.getWeight()));
		cargo.setWidth(Integer.valueOf(request.getWidth()));
		cargo.setStatus(Status.FREE);
		cargo.setCreationDate(LocalDateTime.now());
		repository.save(cargo);
	}

	@Override
	public Owner findOwner(String email) {
		Owner owner = repository.findOwner(email);
		return owner;
	}
	
	@Override
	public void delete(Integer id) {
		repository.delete(id);
	}

	@Override
	public CargoRequest findOne(Integer id) {
		Cargo cargo = repository.findOneRequest(id);
		CargoRequest request = new CargoRequest();
		request.setCityFrom(cargo.getCityFrom());
		request.setCityTo(cargo.getCityTo());
		request.setGoods(cargo.getGoods());
		request.setHeight(String.valueOf(cargo.getHeight()));
		request.setId(cargo.getId());
		request.setLength(String.valueOf(cargo.getLength()));
		request.setOwner(cargo.getOwner());
		request.setPrice(String.valueOf(cargo.getPrice()));
		request.setWeight(String.valueOf(cargo.getWeight()));
		request.setWidth(String.valueOf(cargo.getWidth()));
		return request;
	}

	@Override
	public OwnerView findThisOwner(Integer id) {
		return  repository.findThisOwner(id);
	}

	@Override
	public List<TransporterIndexView> findOffersById(Integer id) {
		return repository.findOffersById(id);
	}

	@Override
	public List<Offer> checkTransporterConfirm(Integer id) {
		return repository.checkTransporterConfirm(id);
	}

	@Override
	public List<TransporterIndexView> findAcceptTransporter(Integer id) {
		return repository.findAcceptTransporter(id);
	}

	@Override
	public List<TransporterIndexView> findInWayTransporter(Integer id) {
		return repository.findInWayTransporter(id);
	}
}