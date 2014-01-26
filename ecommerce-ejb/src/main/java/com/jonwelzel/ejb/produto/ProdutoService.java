package com.jonwelzel.ejb.produto;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jonwelzel.persistence.dao.IDAO;
import com.jonwelzel.persistence.dao.produto.ProdutoDAO;
import com.jonwelzel.persistence.entities.Produto;

@Stateless(mappedName = IProdutoService.JNDI_NAME)
public class ProdutoService implements IProdutoService {

	private ProdutoDAO dao;

	@PersistenceContext(unitName = "javaDB")
	private EntityManager em;

	private Logger log = LoggerFactory.getLogger(getClass());

	public ProdutoService() {

	}

	@Override
	public List<Produto> efetuarVenda(List<Produto> produtos) {
		// TODO Auto-generated method stub
		log.info("Iniciando venda...");
		return null;
	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

	@Override
	public IDAO<Long, Produto> getDAO() {
		return dao == null ? new ProdutoDAO(em) : dao;
	}

	@Override
	public Produto salvar(Produto produto) {
		return getDAO().save(produto);
	}

}
