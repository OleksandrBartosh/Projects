package validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import ua.repository.ModelRepository;
import validation.annotation.UniqueModel;

@Component
public class UniqueModelValidator implements ConstraintValidator<UniqueModel, String> {

	private final ModelRepository repository;
	
	public UniqueModelValidator(ModelRepository repository) {
		this.repository = repository;
	}

	@Override
	public void initialize(UniqueModel arg0) {}

	@Override
	public boolean isValid(String name, ConstraintValidatorContext context) {
		return repository.findByName(name.trim())==null;
	}

}