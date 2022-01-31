package com.test.projectcom.repository;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class TransactionPersistenceContext {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public <E> E save(E entity) {
        entityManager.persist(entity);
        return entity;
    }
}
