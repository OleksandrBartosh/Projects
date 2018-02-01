package ua.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import ua.repository.CityRepository;
import ua.validation.annotation.UniqueCity;

@Component
public class UniqueCityValidator implements ConstraintValidator<UniqueCity, String>{
	
	private final CityRepository repository;
	
	public UniqueCityValidator(CityRepository repository) {
		this.repository = repository;
	}

	@Override
	public void initialize(UniqueCity arg0) {}

	@Override
	public boolean isValid(String name, ConstraintValidatorContext context) {
		return repository.findByName(name.trim())==null;
	}

}
