package com.jonwelzel.persistence.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.jonwelzel.persistence.entities.Bean;

public class GenericDAO<PK extends Serializable, T extends Bean<PK>> implements IDAO<PK, T> {

	protected EntityManager em;
	private final Class<T> clazz;

	public GenericDAO(Class<T> clazz, EntityManager em) {
		super();
		this.clazz = clazz;
		this.em = em;
	}

	@Override
	public T save(T t) {
		if (t.getId() != null) {
			t = update(t);
		} else {
			persist(t);
		}
		return t;
	}

	@Override
	public List<T> findAll() {
		TypedQuery<T> q = em.createQuery("select e from " + clazz.getSimpleName() + " e", clazz);
		return q.getResultList();
	}

	@Override
	public T find(PK id) {
		return em.getReference(clazz, id);
	}

	@Override
	public void remove(T t) {
		t = em.getReference(clazz, t.getId());
		em.remove(t);
	}

	/**
	 * Persist new itens in database.
	 * 
	 * @param t
	 * @return
	 */
	protected T persist(T t) {
		em.persist(t);
		return t;
	}

	/**
	 * Update itens on database.
	 * 
	 * @param t
	 * @return
	 */
	protected T update(T t) {
		return em.merge(t);
	}

}
