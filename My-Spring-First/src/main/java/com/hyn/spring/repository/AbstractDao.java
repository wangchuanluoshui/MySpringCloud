package com.hyn.spring.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractDao implements BatchDao{
    @PersistenceContext
    protected EntityManager em;

	@Override
	public void batchInsert(List list) {
		for (int i = 0; i < list.size(); i++) {
			em.persist(list.get(i));
			if (i % 50 == 0) {
				 em.flush();
				 em.clear();
			}
		}
	}
}
