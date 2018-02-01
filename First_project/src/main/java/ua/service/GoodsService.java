package ua.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.Goods;
import ua.model.filter.SimpleFilter;

public interface GoodsService extends CrudService<Goods, Integer>{

	Goods findByName(String name);
	
	Page<Goods> findAll(Pageable pageable, SimpleFilter filter);
}