package com.jonwelzel.persistence.dao.produto;

import javax.persistence.EntityManager;

import com.jonwelzel.persistence.dao.GenericDAO;
import com.jonwelzel.persistence.entities.Produto;

/**
 * DAO de {@link Produto}.
 * 
 * @author jwelzel
 * 
 */
public class ProdutoDAO extends GenericDAO<Long, Produto> {

	public ProdutoDAO(EntityManager em) {
		super(Produto.class, em);
	}

}
