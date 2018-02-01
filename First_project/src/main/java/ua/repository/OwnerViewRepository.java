package ua.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.model.filter.OwnerFilter;
import ua.model.view.OwnerView;

public interface OwnerViewRepository {

	Page<OwnerView> findAllView(OwnerFilter filter, Pageable pageable);
}
