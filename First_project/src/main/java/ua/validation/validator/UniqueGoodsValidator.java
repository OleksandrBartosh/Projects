package ua.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import ua.repository.GoodsRepository;
import ua.validation.annotation.UniqueGoods;

@Component
public class UniqueGoodsValidator implements ConstraintValidator<UniqueGoods, String>{

	private final GoodsRepository repository;
	
	public UniqueGoodsValidator(GoodsRepository repository) {
		this.repository = repository;
	}

	@Override
	public void initialize(UniqueGoods arg0) {}

	@Override
	public boolean isValid(String name, ConstraintValidatorContext context) {
		return repository.findByName(name.trim())==null;
	}

}