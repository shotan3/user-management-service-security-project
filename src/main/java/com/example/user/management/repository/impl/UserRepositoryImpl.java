package com.example.user.management.repository.impl;

import com.example.user.management.dto.request.UserFilterRequest;
import com.example.user.management.entity.User;
import com.example.user.management.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepositoryImpl implements UserRepository {

    private final EntityManager entityManager;

    @Value("${user.config.paging.default-size}")
    private int defaultPageSize;

    @Value("${user.config.paging.max-size}")
    private int maxPageSize;

    @Override
    public List<User> filterUserBy(UserFilterRequest filter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);


        criteriaQuery.where(getPredicates(filter, criteriaBuilder, root));
        validatePaging(filter);


        return entityManager.createQuery(criteriaQuery)
                .setFirstResult((filter.getPage() - 1))
                .setMaxResults(filter.getPageSize())
                .getResultList();
    }

    private Predicate[] getPredicates(UserFilterRequest filter, CriteriaBuilder cb, Root<User> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (filter.getFromDateOfBirth() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("dateOfBirth"), filter.getFromDateOfBirth()));
        }
        if (filter.getToDateOfBirth() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("dateOfBirth"), filter.getToDateOfBirth()));
        }
        if (filter.getGender() != null) {
            predicates.add(cb.equal(root.get("gender"), filter.getGender()));
        }
        if (filter.getCountryOfResidence() != null) {
            predicates.add(cb.equal(root.get("countryOfResidence"), filter.getCountryOfResidence()));
        }
        if (filter.getCityOfResidence() != null) {
            predicates.add(cb.equal(root.get("cityOfResidence"), filter.getCityOfResidence()));
        }

        return predicates.toArray(new Predicate[]{});
    }

    private void validatePaging(UserFilterRequest filter) {
        Integer page = filter.getPage();
        Integer pageSize = filter.getPageSize();

        pageSize = (pageSize != null) ? pageSize : defaultPageSize;
        page = (page != null) ? page : 1;

        if (pageSize > maxPageSize) {
            log.warn("Requested page size={} exceeded max allowed page size! max={}", pageSize, maxPageSize);
            pageSize = defaultPageSize;
        }
        filter.setPage(page);
        filter.setPageSize(pageSize);
    }
}

