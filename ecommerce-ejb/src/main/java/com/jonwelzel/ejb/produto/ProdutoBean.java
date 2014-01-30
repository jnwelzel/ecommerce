package com.jonwelzel.ejb.produto;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jonwelzel.ejb.StartupBean;
import com.jonwelzel.persistence.dao.IDAO;
import com.jonwelzel.persistence.dao.produto.ProdutoDAO;
import com.jonwelzel.persistence.entities.Produto;

@Stateless(name = ProdutoBean.EJB_NAME)
@Remote(ProdutoRemoteBusiness.class)
public class ProdutoBean implements ProdutoRemoteBusiness {

	public static final String EJB_NAME = "ProdutoEJB";
	private ProdutoDAO dao;

	@PersistenceContext(unitName = "javaDB")
	private EntityManager em;

	@EJB
	private StartupBean startupBean;

	private Logger log = LoggerFactory.getLogger(getClass());

	public ProdutoBean() {

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

	@Override
	public Produto encontrar(Long id) {
		return getDAO().find(id);
	}

	@Override
	public void excluir(Produto produto) {
		getDAO().remove(produto);
	}

	@Override
	public List<Produto> listar() {
		return getDAO().findAll();
	}

	@Override
	public List<Produto> listarAtivos() {
		return getEntityManager().createNamedQuery("Produto.listarAtivos", Produto.class).getResultList();
	}

	@Override
	public Integer getQuantidadeTotalInicial() {
		return ((Long) getEntityManager().createNamedQuery("Produto.quantidadeTotalInicial").getSingleResult())
				.intValue();
	}

	@Override
	public Integer getQuantidadeTotalAtual() {
		return ((Long) getEntityManager().createNamedQuery("Produto.quantidadeTotalAtual").getSingleResult())
				.intValue();
	}

	@Override
	public Integer getQuantidadeTotalAtualPorProduto(Produto produto) {
		return ((Long) getEntityManager().createNamedQuery("Produto.quantidadeTotalAtualPorProduto")
				.setParameter("codigo", produto.getCodigo()).getSingleResult()).intValue();
	}

	@Override
	public Produto encontrarPorCodigo(String codigo) {
		return getEntityManager().createNamedQuery("Produto.econtrarPorCodigo", Produto.class)
				.setParameter("codigo", codigo).getSingleResult();
	}

	@Override
	public List<Produto> encontrarPorCodigos(List<String> codigos) {
		return getEntityManager().createNamedQuery("Produto.encontrarPorCodigos", Produto.class)
				.setParameter("codigos", codigos).getResultList();
	}

}
