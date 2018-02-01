package ua.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ua.entity.Owner;
import ua.entity.Role;
import ua.entity.Transporter;
import ua.entity.User;
import ua.model.request.OwnerRequest;
import ua.model.request.TransporterRequest;
import ua.model.view.CargoView;
import ua.model.view.OwnerProfileView;
import ua.model.view.TransporterProfileView;
import ua.repository.UserRepository;
import ua.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository repository;
	
	private final PasswordEncoder encoder;
	
	@Value("${file.path}")
	private String path;
	
	public UserServiceImpl(UserRepository repository, PasswordEncoder encoder) {
		this.repository = repository;
		this.encoder = encoder;
	}

	@Override
	public void save(TransporterRequest request) {
		User user = new User();
		user.setEmail(request.getEmail());
		user.setPassword(encoder.encode(request.getPassword()));
		user.setRole(Role.ROLE_TRANSPORTER);
		//
		Transporter transporter = new Transporter();
		transporter.setAge(Integer.valueOf(request.getAge()));
		transporter.setCarAge(Integer.valueOf(request.getCarAge()));
		transporter.setMaxWeight(Integer.valueOf(request.getMaxWeight()));
		transporter.setName(request.getName());
		transporter.setSurname(request.getSurname());
		transporter.setPhone(request.getPhone());
		transporter.setModel(request.getModel());
		transporter.setCount(0);
		transporter.setRate(new BigDecimal(0.00));
		transporter.setCityArrive(request.getCityTo());
		transporter.setStatus(ua.entity.Status.FREE);
		transporter.setRegistrationDate(LocalDateTime.now());
		Date date = new Date();
		transporter.setDateArrive(date);
		transporter.setUser(user);
		
		MultipartFile file = request.getFile();
		if(file!=null&&!file.isEmpty()) {
			String extention = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
			String fileName = UUID.randomUUID().toString()+extention;
			try {
				file.transferTo(new File(path+fileName));
				transporter.setPhotoUrl(fileName);
				transporter.setVersion(1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		user.setTransporter(transporter);
		repository.save(user);
	}
	
	@Override
	public void save(OwnerRequest request) {
		User user = new User();
		user.setEmail(request.getEmail());
		user.setPassword(encoder.encode(request.getPassword()));
		user.setRole(Role.ROLE_OWNER);
		Owner owner = new Owner();
		owner.setName(request.getName());
		owner.setSurname(request.getSurname());
		owner.setRate(new BigDecimal(0.00));
		owner.setPhone(request.getPhone());
		owner.setAddress(request.getAddress());
		owner.setCount(0);
		owner.setRegistrationDate(LocalDateTime.now());
		owner.setUser(user);
		
		MultipartFile file = request.getFile();
		if(file!=null&&!file.isEmpty()) {
			String extention = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
			String fileName = UUID.randomUUID().toString()+extention;
			try {
				file.transferTo(new File(path+fileName));
				owner.setPhotoUrl(fileName);
				owner.setVersion(1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		user.setOwner(owner);
		repository.save(user);
	}

	@Override
	public List<String> findAllBrands() {
		return repository.findAllBrands();
}

	@Override
	public List<String> findAllModels() {
		return repository.findAllModels();
	}

	@Override
	public List<String> findAllCity() {
		return repository.findAllCity();
	}

	@Override
	public Role determineRole(String email) {
		return repository.determineRole(email);
	}
	
	@Override
	public OwnerProfileView findOwner(String email) {
		return repository.findOwner(email);
	}

	@Override
	public TransporterProfileView findTransporter(String email) {
		return repository.findTransporter(email);
	}

	@Override
	public List<String> findAllGoods() {
		return repository.findAllGoods();
	}

	@Override
	public List<CargoView> findAllCargo() {
		return repository.findAllCargo();
	}

	@Override
	public CargoView getCurrentCargo(String email) {
		return repository.getCurrentCargo(email);
	}
}