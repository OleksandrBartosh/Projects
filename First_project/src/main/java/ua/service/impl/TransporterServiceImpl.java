package ua.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.entity.Cargo;
import ua.entity.Offer;
import ua.entity.OfferStatus;
import ua.entity.Transporter;
import ua.model.filter.TransporterFilter;
import ua.model.request.ChooseCargoRequest;
import ua.model.request.TransporterCarRequest;
import ua.model.request.TransporterStatusRequest;
import ua.model.view.CargoView;
import ua.model.view.TransporterIndexView;
import ua.model.view.TransporterView;
import ua.repository.OfferRepository;
import ua.repository.TransporterRepository;
import ua.repository.TransporterViewRepository;
import ua.service.TransporterService;

@Service
public class TransporterServiceImpl implements TransporterService{

	private final TransporterRepository repository;
	
	private final OfferRepository offerRepository;
	
	private final TransporterViewRepository transporterViewRepository;
	
	public TransporterServiceImpl(TransporterRepository repository, OfferRepository offerRepository, TransporterViewRepository transporterViewRepository) {
		this.repository = repository;
		this.offerRepository = offerRepository;
		this.transporterViewRepository = transporterViewRepository;
	}

	@Override
	public Page<TransporterIndexView> findAllPage(Pageable pageable) {
		return repository.findAllPage(pageable);
	}
	
	@Override
	public List<TransporterIndexView> findAllView() {
		return repository.findAllView();
	}

	@Override
	public TransporterView findById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public void saveNewStatus(TransporterStatusRequest request, String email) {
		Transporter transporter = repository.getTransporter(email);	
		transporter.setStatus(request.getStatus());
		transporter.setCityArrive(request.getCityArrive());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = formatter.parse(request.getDateArrive());
			transporter.setDateArrive(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		repository.save(transporter);
	}
	
	@Override
	public void saveNewCar(TransporterCarRequest carRequest, String email) {
		Transporter transporter = repository.getTransporter(email);	
		transporter.setModel(carRequest.getModel());
		transporter.setCarAge(carRequest.getCarAge());
		transporter.setMaxWeight(carRequest.getMaxWeight());
		repository.save(transporter);
	}

	@Override
	public List<CargoView> cargoViews(String email) {
		return repository.cargoViews(email);
	}
	
	@Override
	public void saveOfferToTransporter(ChooseCargoRequest request, String email) {
		Offer offer = new Offer();
		offer.setTransporter(repository.getTansporter(email));
		Integer id = request.getCargo().getId();
		Cargo cargo = repository.cargo(id);
		offer.setCargo(cargo);
		offer.setLeftBy(1);
		offer.setOfferStatus(OfferStatus.WAITING);
			offerRepository.save(offer);
	}

	@Override
	public List<String> findAllModels() {
		return repository.findAllModels();
	}

	@Override
	public List<String> findAllBrands() {
		return repository.findAllBrands();
	}

	@Override
	public List<String> findAllCities() {
		return repository.findAllCities();
	}

	@Override
	public Page<TransporterIndexView> findAll(TransporterFilter filter, Pageable pageable) {
		return transporterViewRepository.findAll(filter, pageable);
	}
}