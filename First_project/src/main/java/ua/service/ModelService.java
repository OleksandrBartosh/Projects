package ua.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.Model;
import ua.model.filter.SimpleFilter;

public interface ModelService extends CrudService<Model, Integer> {

	Model findByName(String name);
	
	Page<Model> findAll(Pageable pageable, SimpleFilter filter);

}
