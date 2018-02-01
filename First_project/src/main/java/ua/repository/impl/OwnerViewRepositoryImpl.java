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

import ua.entity.Owner;
import ua.entity.Owner_;
import ua.model.filter.OwnerFilter;
import ua.model.view.OwnerView;
import ua.repository.OwnerViewRepository;

@Repository
public class OwnerViewRepositoryImpl implements OwnerViewRepository{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Page<OwnerView> findAllView(OwnerFilter filter, Pageable pageable) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<OwnerView> cq = cb.createQuery(OwnerView.class);
		Root<Owner> root = cq.from(Owner.class);
		cq.multiselect(root.get(Owner_.id), root.get(Owner_.rate), root.get(Owner_.photoUrl), root.get(Owner_.version), root.get(Owner_.name), root.get(Owner_.surname),
				root.get(Owner_.phone), root.get(Owner_.count), root.get(Owner_.address), root.get(Owner_.registrationDate));

		PredicateBuilder builder = new PredicateBuilder(filter, root, cb);
		Predicate predicate = builder.toPredicate();
		if (predicate != null)
			cq.where(predicate);
		cq.orderBy(toOrders(pageable.getSort(), root, cb));
		List<OwnerView> content = em.createQuery(cq).setFirstResult(pageable.getPageNumber())
				.setMaxResults(pageable.getPageSize()).getResultList();
		CriteriaBuilder cbCount = em.getCriteriaBuilder();
		CriteriaQuery<Long> cqCount = cb.createQuery(Long.class);
		Root<Owner> rootCount = cq.from(Owner.class);
		cqCount.select(cb.count(root));
		CountPredicateBuilder countBuilder = new CountPredicateBuilder(filter, rootCount, cbCount);
		Predicate countPredicate = countBuilder.toPredicate();
		if (countPredicate != null)
			cq.where(countPredicate);
		return PageableExecutionUtils.getPage(content, pageable, () -> em.createQuery(cqCount).getSingleResult());
	}
	
	private static class PredicateBuilder {
		
		final OwnerFilter filter;
		
		final Root<Owner> root;
		
		final CriteriaBuilder cb;

		final List<Predicate> predicates = new ArrayList<>();

		public PredicateBuilder(OwnerFilter filter, Root<Owner> root, CriteriaBuilder cb) {
			this.filter = filter;
			this.root = root;
			this.cb = cb;
		}
		
		void filterByMinRate() {
			if (!filter.getMinRate().isEmpty()) {
				predicates.add(cb.ge(root.get(Owner_.rate), new BigDecimal(filter.getMinRate().replace(',', '.'))));
			}
		}

		void filterByMaxRate() {
			if (!filter.getMaxRate().isEmpty()) {
				predicates.add(cb.le(root.get(Owner_.rate), new BigDecimal(filter.getMaxRate().replace(',', '.'))));
			}
		}
		
		void filterMinCount() {
			if(!filter.getMinCount().isEmpty()) {
				predicates.add(cb.ge(root.get(Owner_.count), new Integer(filter.getMinCount())));
			}
		}
		
		void filterMaxCount() {
			if(!filter.getMaxCount().isEmpty()) {
				predicates.add(cb.le(root.get(Owner_.count), new Integer(filter.getMaxCount())));
			}
		}
		Predicate toPredicate() {
			filterByMinRate();
			filterByMaxRate();
			filterMinCount();
			filterMaxCount();
			return predicates.isEmpty() ? null : cb.and(predicates.stream().toArray(Predicate[]::new));
		}
	}

	private static class CountPredicateBuilder {
		
		final OwnerFilter filter;
		
		final Root<Owner> root;
		
		final CriteriaBuilder cb;

		final List<Predicate> predicates = new ArrayList<>();

		public CountPredicateBuilder(OwnerFilter filter, Root<Owner> root, CriteriaBuilder cb) {
			this.filter = filter;
			this.root = root;
			this.cb = cb;
		}
		void filterByMinRate() {
			if (!filter.getMinRate().isEmpty()) {
				predicates.add(cb.ge(root.get(Owner_.rate), new BigDecimal(filter.getMinRate().replace(',', '.'))));
			}
		}

		void filterByMaxRate() {
			if (!filter.getMaxRate().isEmpty()) {
				predicates.add(cb.le(root.get(Owner_.rate), new BigDecimal(filter.getMaxRate().replace(',', '.'))));
			}
		}
		
		void filterMinCount() {
			if(!filter.getMinCount().isEmpty()) {
				predicates.add(cb.ge(root.get(Owner_.count), new Integer(filter.getMinCount())));
			}
		}
		
		void filterMaxCount() {
			if(!filter.getMaxCount().isEmpty()) {
				predicates.add(cb.le(root.get(Owner_.count), new Integer(filter.getMaxCount())));
			}
		}
		
		Predicate toPredicate() {
			filterByMinRate();
			filterByMaxRate();
			filterMinCount();
			filterMaxCount();
			return predicates.isEmpty() ? null : cb.and(predicates.stream().toArray(Predicate[]::new));
		}
	}
}
