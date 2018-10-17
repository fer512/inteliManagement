package ar.com.intelimanagement.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.LongSupplier;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import ar.com.intelimanagement.domain.Approvals;
import ar.com.intelimanagement.domain.Approvals_;
import ar.com.intelimanagement.domain.User;
import ar.com.intelimanagement.domain.Variation;
import ar.com.intelimanagement.domain.Variation_;
import ar.com.intelimanagement.domain.enumeration.ApprovalsStatusType;
import ar.com.intelimanagement.service.dto.VariationDTO;

import org.hibernate.Criteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;

/**
 * Spring Data repository for the Variation entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional(readOnly = true)
public class VariationCustomizedRepositoryImpl implements VariationCustomizedRepository {

	private final EntityManager em;

	public VariationCustomizedRepositoryImpl(EntityManager entityManager) {
		Assert.notNull(entityManager, "EntityManager must not be null!");
		this.em = entityManager;
	}

	@Override
	@Transactional
	public Page<Variation> getPending(Pageable pageable, User u) {
		Set<User> team = u.getTeam();
		List<User> teamL = team.stream().collect(Collectors.toList());
		List<Variation> pendientes = this.obtenerPendientes(pageable, u ,teamL);
		LongSupplier count = this.contarPendientes(pageable, u , teamL);
		return PageableExecutionUtils.getPage(pendientes, pageable, count);
	}


	private LongSupplier contarPendientes(Pageable pageable, User u, List<User> team) {

		if (team != null && team.size() > 0) {
			// CREO EL BUILDER DEL CRITERIA
			CriteriaBuilder builder = em.getCriteriaBuilder();

			// ARMO EL CRITERIA DE LA CLASE Long
			CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
			Root<Variation> pRoot = criteria.from(Variation.class);

			// ARMO EL SELECT
			criteria.select(builder.count(pRoot));

			// JOINEO CON APPROVALS
			Join<Variation, Approvals> approvalsJoin = pRoot.join("approvals");
			Predicate conjunction = builder.conjunction();

			// ARMO LA CONSULTA IN
			Path<User> parentExpression = pRoot.get(Variation_.creationUser);
			Predicate parentPredicate = parentExpression.in();
			// ARMO EL WHERE
			criteria.where(builder.and(parentPredicate,
					builder.or(builder.equal(approvalsJoin.get(Approvals_.status), ApprovalsStatusType.PENDING),
							builder.equal(approvalsJoin.get(Approvals_.status), ApprovalsStatusType.CREATE))));

			TypedQuery<Long> query = em.createQuery(criteria);

			LongSupplier supplier = () -> query.getSingleResult();
			return supplier;
		}

		Long total = (long) 0;
		LongSupplier supplier = () -> total;
		return supplier;
	}


	private List<Variation> obtenerPendientes(Pageable pageable, User u, List<User> team) {

		if (team != null && team.size() > 0) {
			// CREO EL BUILDER DEL CRITERIA
			CriteriaBuilder builder = em.getCriteriaBuilder();

			// ARMO EL CRITERIA DE LA CLASE VARIACION
			CriteriaQuery<Variation> criteria = builder.createQuery(Variation.class);
			Root<Variation> pRoot = criteria.from(Variation.class);

			// ARMO EL SELECT
			criteria.select(pRoot);

			// JOINEO CON APPROVALS
			Join<Variation, Approvals> approvalsJoin = pRoot.join("approvals");
			Predicate conjunction = builder.conjunction();

			// ARMO LA CONSULTA IN
			Path<User> parentExpression = pRoot.get(Variation_.creationUser);
			Predicate parentPredicate = parentExpression.in(u.getTeam().stream().collect(Collectors.toList()));

			// ARMO EL WHERE
			criteria.where(builder.and(parentPredicate,
					builder.or(builder.equal(approvalsJoin.get(Approvals_.status), ApprovalsStatusType.PENDING),
							builder.equal(approvalsJoin.get(Approvals_.status), ApprovalsStatusType.CREATE))));

			TypedQuery<Variation> query = em.createQuery(criteria);

			if (pageable.isPaged()) {
				query.setFirstResult((int) pageable.getOffset());
				query.setMaxResults(pageable.getPageSize());
			}

			return query.getResultList();

		}
		
		return new ArrayList<Variation>();
	}

}
