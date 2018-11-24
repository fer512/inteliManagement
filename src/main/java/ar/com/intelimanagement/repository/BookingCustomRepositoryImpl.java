package ar.com.intelimanagement.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.LongSupplier;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import ar.com.intelimanagement.domain.Approvals;
import ar.com.intelimanagement.domain.Approvals_;
import ar.com.intelimanagement.domain.Booking;
import ar.com.intelimanagement.domain.Booking_;
import ar.com.intelimanagement.domain.User;
import ar.com.intelimanagement.domain.Variation;
import ar.com.intelimanagement.domain.Variation_;
import ar.com.intelimanagement.domain.enumeration.ApprovalsStatusType;
import ar.com.intelimanagement.service.dto.BookingFullDTO;

@SuppressWarnings("unused")
@Repository
@Transactional(readOnly = true)
public class BookingCustomRepositoryImpl implements BookingCustomRepository {

	private final EntityManager em;

	public BookingCustomRepositoryImpl(EntityManager entityManager) {
		Assert.notNull(entityManager, "EntityManager must not be null!");
		this.em = entityManager;
	}

	@Override
	@Transactional
	public List<Booking> find(String value) {
		List<Booking> list = new ArrayList<>();
		if (value != null) {

			CriteriaBuilder builder = em.getCriteriaBuilder();

			// ARMO EL CRITERIA DE LA CLASE VARIACION
			CriteriaQuery<Booking> criteria = builder.createQuery(Booking.class);
			Root<Booking> pRoot = criteria.from(Booking.class);

			// ARMO EL SELECT
			criteria.select(pRoot);


			// ARMO EL WHERE
			criteria.where(builder.like(
					builder.upper(pRoot.get(Booking_.idTransaction)), 
				    "%"+value.toUpperCase()+"%"));

			TypedQuery<Booking> query = em.createQuery(criteria);


			return query.getResultList();
		}
		return list;
	}

}
