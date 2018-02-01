package ua.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.model.filter.CargoFilter;
import ua.model.view.CargoView;

public interface CargoViewRepository {

	Page<CargoView> findAllView(CargoFilter cargoFilter, Pageable pageable);
}
