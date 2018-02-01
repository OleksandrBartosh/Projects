package ua.service.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import ua.entity.Cargo;
import ua.entity.Offer;
import ua.entity.OfferStatus;
import ua.entity.Status;
import ua.entity.Transporter;
import ua.model.view.CargoView;
import ua.model.view.OwnerView;
import ua.repository.CargoRepository;
import ua.repository.OfferRepository;
import ua.repository.TransporterRepository;
import ua.service.OfferService;

@Service
public class OfferServiceImpl implements OfferService {

	private final OfferRepository repository;
	
	private final TransporterRepository transporterRepository;
	
	private final CargoRepository cargoRepository;
	
	public OfferServiceImpl(OfferRepository repository, TransporterRepository transporterRepository,
			CargoRepository cargoRepository) {
		this.repository = repository;
		this.transporterRepository = transporterRepository;
		this.cargoRepository = cargoRepository;
	}

	@Override
	public void getOffer(String email, Integer id) {
		Transporter transporter = repository.getTransporter(email);
		Cargo cargo = repository.getCargo(id);
		Offer offer = new Offer();
		offer.setTransporter(transporter);
		offer.setCargo(cargo);
		offer.setOfferStatus(OfferStatus.WAITING);
		offer.setLeftBy(2);
		repository.save(offer);
	}

	@Override
	public void changeToAccept(Integer id_cargo, Integer id_transporter) {
		Offer curoffer = repository.getCurrentOffer(id_cargo, id_transporter);
		curoffer.setOfferStatus(OfferStatus.ACCEPT);
		repository.save(curoffer);
		List<Offer> offersToTransporter = repository.offersToTransporter(curoffer.getTransporter().getId());
		// delete offers to transporter waiting and decline
		Iterator<Offer> iteratorTransporter = offersToTransporter.iterator();
		while (iteratorTransporter.hasNext()) {
			Offer offer = (Offer) iteratorTransporter.next();
			repository.deleteOffer(offer.getId());
		}
		//delete offers =! accept
		List<Offer> offers = repository.transportersOffers(id_cargo);
		Iterator<Offer> iterator = offers.iterator();
		while (iterator.hasNext()) {
			Offer offer = iterator.next();
			if(offer.getOfferStatus()!=OfferStatus.ACCEPT)
				repository.deleteOffer(offer.getId());
		}
	}
	
	@Override
	public void changeToAccept(Integer id_cargo, String email) {
		Offer curoffer = repository.getCurrentOffer(id_cargo, email);
		curoffer.setOfferStatus(OfferStatus.ACCEPT);
		repository.save(curoffer);
		List<Offer> offers = repository.ownersOffers(id_cargo);
		Iterator<Offer> iterator = offers.iterator();
		while (iterator.hasNext()) {
			Offer offer = iterator.next();
			if(offer.getOfferStatus()!=OfferStatus.ACCEPT)
				repository.deleteOffer(offer.getId());
		}
	}
	@Override
	public void changeToDecline(Integer id_cargo, Integer id_transporter) {
		Offer curoffer = repository.getCurrentOffer(id_cargo, id_transporter);
		curoffer.setOfferStatus(OfferStatus.DECLINE);
		repository.save(curoffer);
	}

	@Override
	public void transporterChangeToConfirm(Integer id_cargo, Integer id_transporter) {
		Offer curoffer = repository.getCurrentOffer(id_cargo, id_transporter);
		curoffer.setOwnerConfirm(1);
		if(curoffer.getTransporterConfirm()==1 && curoffer.getOwnerConfirm()==1) {
			Transporter transporter = repository.getTransporter(id_transporter);
			transporter.setCount(transporter.getCount()+1);
			transporter.setStatus(Status.IN_THE_WAY);
			//set cargo
			Cargo cargo = repository.getCargo(id_cargo);
			cargo.setTransporter(transporter);
			cargo.setStatus(Status.IN_THE_WAY);
			curoffer.setOfferStatus(OfferStatus.COMPLETE);
			cargoRepository.save(cargo);
			transporterRepository.save(transporter);
		}
		repository.save(curoffer);
	}

	@Override
	public List<CargoView> myOffers(String email) {
		return	repository.myOffers(email);
	}

	@Override
	public void changeToDecline(Integer id_cargo, String email) {
		Offer curoffer = repository.getCurrentOffer(id_cargo, email);
		curoffer.setOfferStatus(OfferStatus.DECLINE);
		repository.save(curoffer);
	}

	@Override
	public OwnerView findThisOwner(Integer id) {
		return repository.findThisOwner(id);
	}

	@Override
	public List<CargoView> findById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public List<Offer> offersTransporter(Integer id) {
		return repository.offersTransporter(id);
	}

	@Override
	public List<CargoView> checkConfirm(Integer id) {
		return cargoRepository.checkConfirm(id);
	}

	@Override
	public List<CargoView> findAcceptOwner(Integer id) {
		return cargoRepository.findAcceptOwner(id);
	}

	@Override
	public void ownerChangeToConfirm(Integer id_cargo, String email) {
		Offer curoffer = repository.getCurrentOffer(id_cargo, email);
		curoffer.setTransporterConfirm(1);
		if(curoffer.getTransporterConfirm()==1 && curoffer.getOwnerConfirm()==1) {
			Transporter transporter = repository.getTransporter(email);
			transporter.setCount(transporter.getCount()+1);
			transporter.setStatus(Status.IN_THE_WAY);
			Cargo cargo = repository.getCargo(id_cargo);
			cargo.setTransporter(transporter);
			curoffer.setOfferStatus(OfferStatus.COMPLETE);
			transporter.setCityArrive(cargo.getCityTo());
			cargo.setStatus(Status.IN_THE_WAY);
			cargoRepository.save(cargo);
			transporterRepository.save(transporter);
		}
		repository.save(curoffer);
		
	}
}