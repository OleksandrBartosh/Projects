package ua.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import ua.entity.Model;
import ua.model.filter.SimpleFilter;
import ua.repository.ModelRepository;
import ua.service.ModelService;

@Service
public class ModelServiceImpl extends CrudServiceImpl<Model, Integer> implements ModelService{

	private final ModelRepository repository;

	public ModelServiceImpl(ModelRepository repository) {
		super(repository);
		this.repository = repository;
	}

	@Override
	public Model findByName(String name) {
		return repository.findByName(name);
	}
	
	@Override
	public Page<Model> findAll(Pageable pageable, SimpleFilter filter) {
		return repository.findAll(filter(filter), pageable);
	}
	
	private Specification<Model> filter(SimpleFilter filter){
		return (root, query, cb) -> {
			if(filter.getSearch().isEmpty()) return null;
			return cb.like(root.get("name"), filter.getSearch()+"%");
		};
	}
}
