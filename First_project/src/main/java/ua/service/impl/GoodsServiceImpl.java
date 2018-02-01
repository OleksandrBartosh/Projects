package ua.service.impl;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import ua.entity.Goods;
import ua.model.filter.SimpleFilter;
import ua.repository.GoodsRepository;
import ua.service.GoodsService;

@Service
public class GoodsServiceImpl extends CrudServiceImpl<Goods, Integer> implements GoodsService  {

	private final GoodsRepository repository;

	@Override
	public Goods findByName(String name) {
		return repository.findByName(name);
	}
	
	public GoodsServiceImpl(GoodsRepository repository) {
		super(repository);
		this.repository = repository;
	}
	@Override
	public Page<Goods> findAll(Pageable pageable, SimpleFilter filter) {
		return repository.findAll(filter(filter), pageable);
	}
	
	private Specification<Goods> filter(SimpleFilter filter){
		return (root, query, cb) -> {
			if(filter.getSearch().isEmpty()) return null;
			return cb.like(root.get("name"), filter.getSearch()+"%");
		};
	}
}