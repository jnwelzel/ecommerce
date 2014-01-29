package com.jonwelzel.ejb.produto;

import java.util.List;

import javax.ejb.Local;

import com.jonwelzel.ejb.IService;
import com.jonwelzel.persistence.entities.Produto;

@Local
public interface IProdutoService extends IService<Long, Produto> {

	/**
	 * Método responsável por finalizar uma venda.
	 * 
	 * @param produtos Produtos que estão no carrinho do cliente.
	 * @return Lista dos produtos vendidos.
	 */
	public List<Produto> efetuarVenda(List<Produto> produtos);

	public static final String JNDI_NAME = "ProdutoService";

	public Produto salvar(Produto produto);

	public Produto encontrar(Long id);

	public void excluir(Produto produto);

	public List<Produto> listar();

	public List<Produto> listarAtivos();

	public Integer getQuantidadeTotal();

}
