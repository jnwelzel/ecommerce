package com.jonwelzel.ejb.produto;

import java.util.List;

import com.jonwelzel.ejb.RemoteBusiness;
import com.jonwelzel.persistence.entities.Produto;

public interface ProdutoRemoteBusiness extends RemoteBusiness<Long, Produto> {

	/**
	 * Método responsável por finalizar uma venda.
	 * 
	 * @param produtos Produtos que estão no carrinho do cliente.
	 * @return Lista dos produtos vendidos.
	 */
	public List<Produto> efetuarVenda(List<Produto> produtos);

	public Produto salvar(Produto produto);

	public Produto encontrar(Long id);

	public Produto encontrarPorCodigo(String codigo);

	public void excluir(Produto produto);

	public List<Produto> listar();

	public List<Produto> listarAtivos();

	public Integer getQuantidadeTotalInicial();

	public Integer getQuantidadeTotalAtual();

	public Integer getQuantidadeTotalAtualPorProduto(Produto produto);

	public List<Produto> encontrarPorCodigos(List<String> codigos);

}
