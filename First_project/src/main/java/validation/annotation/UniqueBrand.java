package validation.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import validation.validator.UniqueBrandValidator;

@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy=UniqueBrandValidator.class)
public @interface UniqueBrand {

	String message() default "Not unique";
	
	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
