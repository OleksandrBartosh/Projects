package ua.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ua.entity.Brand;
import ua.repository.BrandRepository;

@Component
public class BrandConverter implements Converter<String, Brand>{

	private final BrandRepository repository;
	
	public BrandConverter(BrandRepository repository) {
		this.repository = repository;
	}

	@Override
	public Brand convert(String source) {
		return repository.findByName(source);
	}
}