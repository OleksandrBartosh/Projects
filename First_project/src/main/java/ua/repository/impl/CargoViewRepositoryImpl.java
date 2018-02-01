package ua.repository.impl;

import static org.springframework.data.jpa.repository.query.QueryUtils.toOrders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import ua.entity.Cargo;
import ua.entity.Cargo_;
import ua.entity.City_;
import ua.entity.Goods_;
import ua.model.filter.CargoFilter;
import ua.model.view.CargoView;
import ua.repository.CargoViewRepository;

@Repository
public class CargoViewRepositoryImpl implements CargoViewRepository{

	@PersistenceContext
	private EntityManager em;

	@Override
	public Page<CargoView> findAllView(CargoFilter filter, Pageable pageable) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<CargoView> cq = cb.createQuery(CargoView.class);
		Root<Cargo> root = cq.from(Cargo.class);
		cq.multiselect(root.get(Cargo_.id), root.join(Cargo_.goods).get(Goods_.name),
						root.get(Cargo_.weight), root.get(Cargo_.height), root.get(Cargo_.width), root.get(Cargo_.length),
						root.join(Cargo_.cityFrom).get(City_.name),
						root.join(Cargo_.cityTo).get(City_.name),
						root.get(Cargo_.price), root.get(Cargo_.creationDate));
		PredicateBuilder builder = new PredicateBuilder(filter, root, cb);
		Predicate predicate = builder.toPredicate();
		if (predicate != null)
			cq.where(predicate);
		cq.orderBy(toOrders(pageable.getSort(), root, cb));
		List<CargoView> content = em.createQuery(cq).setFirstResult(pageable.getPageNumber())
				.setMaxResults(pageable.getPageSize()).getResultList();
		CriteriaBuilder cbCount = em.getCriteriaBuilder();
		CriteriaQuery<Long> cqCount = cb.createQuery(Long.class);
		Root<Cargo> rootCount = cq.from(Cargo.class);
		cqCount.select(cb.count(root));
		CountPredicateBuilder countBuilder = new CountPredicateBuilder(filter, rootCount, cbCount);
		Predicate countPredicate = countBuilder.toPredicate();
		if (countPredicate != null)
			cq.where(countPredicate);
		return PageableExecutionUtils.getPage(content, pageable, () -> em.createQuery(cqCount).getSingleResult());
	}

	private static class PredicateBuilder {
		
		final CargoFilter filter; 
		
		final Root<Cargo> root;

		final CriteriaBuilder cb;

		final List<Predicate> predicates = new ArrayList<>();
		
		public PredicateBuilder(CargoFilter filter, Root<Cargo> root, CriteriaBuilder cb) {
			this.filter = filter;
			this.root = root;
			this.cb = cb;
		}
		
		void findByGoodsIds() {
			if(!filter.getGoodsIds().isEmpty()) {
				predicates.add(root.join(Cargo_.goods).get(Goods_.name).in(filter.getGoodsIds()));
			}
		}
		
		void filterByMinWeight() {
			if(!filter.getMinWeight().isEmpty()) {
				predicates.add(cb.ge(root.get(Cargo_.weight), Integer.parseInt(filter.getMinWeight())));
			}
		}
		
		void filterByMaxWeight() {
			if(!filter.getMaxWeight().isEmpty()) {
				predicates.add(cb.le(root.get(Cargo_.weight), new Integer(filter.getMaxWeight())));
			}
		}
		
		void filterByMinHeight() {
			if(!filter.getMinHeight().isEmpty()) {
				predicates.add(cb.ge(root.get(Cargo_.height), new Integer(filter.getMinHeight())));
			}
		}
		
		void filterByMaxHeight() {
			if(!filter.getMaxHeight().isEmpty()) {
				predicates.add(cb.le(root.get(Cargo_.height), new Integer(filter.getMaxHeight())));
			}
		}
		
		void filterByMinWidth() {
			if(!filter.getMinWidth().isEmpty()) {
				predicates.add(cb.ge(root.get(Cargo_.width), new Integer(filter.getMinWidth())));
			}
		}
		
		void filterByMaxWidth() {
			if(!filter.getMaxWidth().isEmpty()) {
				predicates.add(cb.le(root.get(Cargo_.width), new Integer(filter.getMaxWidth())));
			}
		}
		
		void filterByMinLength() {
			if(!filter.getMinLength().isEmpty()) {
				predicates.add(cb.ge(root.get(Cargo_.length), new Integer(filter.getMinLength())));
			}
		}
		
		void filterByMaxLength() {
			if(!filter.getMaxLength().isEmpty()) {
				predicates.add(cb.le(root.get(Cargo_.length), new Integer(filter.getMaxLength())));
			}
		}
		
		void findCityFrom() {
			if(!filter.getCityFrom().isEmpty()) {
				predicates.add(root.join(Cargo_.cityFrom).get(City_.name).in(filter.getCityFrom()));
			}
		}
		
		void findCityTo() {
			if(!filter.getCityTo().isEmpty()) {
				predicates.add(root.join(Cargo_.cityTo).get(City_.name).in(filter.getCityTo()));
			}
		}
		
		void filterByMinPrice() {
			if(!filter.getMinPrice().isEmpty()) {
				predicates.add(cb.ge(root.get(Cargo_.price), new BigDecimal(filter.getMinPrice().replace(',', '.'))));
			}
		}
		
		void filterByMaxPrice() {
			if(!filter.getMaxPrice().isEmpty()) {
				predicates.add(cb.le(root.get(Cargo_.price), new BigDecimal(filter.getMaxPrice().replace(',', '.'))));
			}
		}
		

		void filterByF() {
			predicates.add(cb.equal(root.get(Cargo_.status), 1));
		}
		
		Predicate toPredicate() {
			findByGoodsIds();
			filterByMinWeight();
			filterByMaxWeight();
			filterByMinHeight();
			filterByMaxHeight();
			filterByMinWidth();
			filterByMaxWidth();
			filterByMinLength();
			filterByMaxLength();
			findCityFrom();
			findCityTo();
			filterByMinPrice();
			filterByMaxPrice();
			filterByF();
			return predicates.isEmpty() ? null : cb.and(predicates.stream().toArray(Predicate[]::new));
		}
	}
	
	private static class CountPredicateBuilder {
		
final CargoFilter filter; 
		
		final Root<Cargo> root;

		final CriteriaBuilder cb;

		final List<Predicate> predicates = new ArrayList<>();
		
		public CountPredicateBuilder(CargoFilter filter, Root<Cargo> root, CriteriaBuilder cb) {
			this.filter = filter;
			this.root = root;
			this.cb = cb;
		}
		
		void findByGoodsIds() {
			if(!filter.getGoodsIds().isEmpty()) {
				predicates.add(root.join(Cargo_.goods).get(Goods_.name).in(filter.getGoodsIds()));
			}
		}
		
		void filterByMinWeight() {
			if(!filter.getMinWeight().isEmpty()) {
				predicates.add(cb.ge(root.get(Cargo_.weight), Integer.parseInt(filter.getMinWeight())));
			}
		}
		
		void filterByMaxWeight() {
			if(!filter.getMaxWeight().isEmpty()) {
				predicates.add(cb.le(root.get(Cargo_.weight), new Integer(filter.getMaxWeight())));
			}
		}
		
		void filterByMinHeight() {
			if(!filter.getMinHeight().isEmpty()) {
				predicates.add(cb.ge(root.get(Cargo_.height), new Integer(filter.getMinHeight())));
			}
		}
		
		void filterByMaxHeight() {
			if(!filter.getMaxHeight().isEmpty()) {
				predicates.add(cb.le(root.get(Cargo_.height), new Integer(filter.getMaxHeight())));
			}
		}
		
		void filterByMinWidth() {
			if(!filter.getMinWidth().isEmpty()) {
				predicates.add(cb.ge(root.get(Cargo_.width), new Integer(filter.getMinWidth())));
			}
		}
		
		void filterByMaxWidth() {
			if(!filter.getMaxWidth().isEmpty()) {
				predicates.add(cb.le(root.get(Cargo_.width), new Integer(filter.getMaxWidth())));
			}
		}
		
		void filterByMinLength() {
			if(!filter.getMinLength().isEmpty()) {
				predicates.add(cb.ge(root.get(Cargo_.length), new Integer(filter.getMinLength())));
			}
		}
		
		void filterByMaxLength() {
			if(!filter.getMaxLength().isEmpty()) {
				predicates.add(cb.le(root.get(Cargo_.length), new Integer(filter.getMaxLength())));
			}
		}
		
		void findCityFrom() {
			if(!filter.getCityFrom().isEmpty()) {
				predicates.add(root.join(Cargo_.cityFrom).get(City_.name).in(filter.getCityFrom()));
			}
		}
		
		void findCityTo() {
			if(!filter.getCityTo().isEmpty()) {
				predicates.add(root.join(Cargo_.cityTo).get(City_.name).in(filter.getCityTo()));
			}
		}
		
		void filterByMinPrice() {
			if(!filter.getMinPrice().isEmpty()) {
				predicates.add(cb.ge(root.get(Cargo_.price), new BigDecimal(filter.getMinPrice().replace(',', '.'))));
			}
		}
		
		void filterByMaxPrice() {
			if(!filter.getMaxPrice().isEmpty()) {
				predicates.add(cb.le(root.get(Cargo_.price), new BigDecimal(filter.getMaxPrice().replace(',', '.'))));
			}
		}
		
		void filterByF() {
			predicates.add(cb.equal(root.get(Cargo_.status), 1));
		}
		
		Predicate toPredicate() {
			findByGoodsIds();
			filterByMinWeight();
			filterByMaxWeight();
			filterByMinHeight();
			filterByMaxHeight();
			filterByMinWidth();
			filterByMaxWidth();
			filterByMinLength();
			filterByMaxLength();
			findCityFrom();
			findCityTo();
			filterByMinPrice();
			filterByMaxPrice();
			filterByF();
			return predicates.isEmpty() ? null : cb.and(predicates.stream().toArray(Predicate[]::new));
		}
	}
}
