package com.jonwelzel.persistence.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

import com.jonwelzel.persistence.enumerations.Categorias;

/**
 * Produto vendido na loja virtual.
 * 
 * @author jwelzel
 * 
 */
@Entity
@NamedQueries(value = {
		@NamedQuery(name = "Produto.listarAtivos", query = "SELECT p FROM Produto p WHERE p.custoCompra IS NOT NULL AND p.custoCompra > 0 ORDER BY p.custoCompra ASC"),
		@NamedQuery(name = "Produto.quantidadeTotal", query = "SELECT SUM(p.quantidade) FROM Produto p") })
public class Produto extends Bean<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 50, nullable = false)
	private String nome;

	@Column(length = 80, nullable = false)
	private String marca;

	@Column(length = 200)
	private String descricao;

	@Column
	private String arquivoFoto;

	@Column(scale = 2, precision = 10)
	private BigDecimal custoCompra;

	@Column
	private BigDecimal valorVenda;

	@Transient
	private String valorTexto;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Categorias categoria;

	@Column(nullable = false)
	private Integer quantidade = 0;

	public Produto() {
	}

	public Produto(String nome, String descricao, String marca, String arquivoFoto, BigDecimal custoCompra,
			Categorias categoria, Integer quantidade) {
		this.nome = nome;
		this.descricao = descricao;
		this.marca = marca;
		this.arquivoFoto = arquivoFoto;
		this.custoCompra = custoCompra;
		this.categoria = categoria;
		this.quantidade = quantidade;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getArquivoFoto() {
		return arquivoFoto;
	}

	public void setArquivoFoto(String arquivoFoto) {
		this.arquivoFoto = arquivoFoto;
	}

	public BigDecimal getCustoCompra() {
		return custoCompra;
	}

	public void setCustoCompra(BigDecimal custoCompra) {
		this.custoCompra = custoCompra;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Categorias getCategoria() {
		return categoria;
	}

	public void setCategoria(Categorias categoria) {
		this.categoria = categoria;
	}

	public BigDecimal getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(BigDecimal custoVenda) {
		this.valorVenda = custoVenda;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getValorTexto() {
		return valorTexto;
	}

	public void setValorTexto(String valorTexto) {
		this.valorTexto = valorTexto;
	}

}
