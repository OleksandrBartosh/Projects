package ua.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import ua.entity.City;
import ua.model.filter.SimpleFilter;
import ua.repository.CityRepository;
import ua.service.CityService;

@Service
public class CityServiceImpl extends CrudServiceImpl<City, Integer> implements CityService {

	private final CityRepository repository;
	
	public CityServiceImpl(CityRepository repository) {
		super(repository);
		this.repository = repository;
	}

	@Override
	public City findByName(String name) {
		return repository.findByName(name);
	}

	@Override
	public Page<City> findAll(Pageable pageable, SimpleFilter filter) {
		return repository.findAll(filter(filter), pageable);
	}
	
	private Specification<City> filter(SimpleFilter filter){
		return (root, query, cb) -> {
			if(filter.getSearch().isEmpty()) return null;
			return cb.like(root.get("name"), filter.getSearch()+"%");
		};
	}
}