package com.jonwelzel.persistence.entities;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

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
		@NamedQuery(name = "Produto.quantidadeTotalInicial", query = "SELECT SUM(p.quantidadeInicial) FROM Produto p WHERE p.custoCompra IS NOT NULL AND p.custoCompra > 0"),
		@NamedQuery(name = "Produto.quantidadeTotalAtual", query = "SELECT SUM(p.quantidadeAtual) FROM Produto p WHERE p.custoCompra IS NOT NULL AND p.custoCompra > 0"),
		@NamedQuery(name = "Produto.quantidadeTotalAtualPorProduto", query = "SELECT SUM(p.quantidadeAtual) FROM Produto p WHERE p.codigo = :codigo"),
		@NamedQuery(name = "Produto.econtrarPorCodigo", query = "SELECT p FROM Produto p WHERE p.codigo = :codigo"),
		@NamedQuery(name = "Produto.encontrarPorCodigos", query = "SELECT p FROM Produto p WHERE p.quantidadeAtual > 0 AND p.codigo IN :codigos") })
public class Produto extends Bean<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 13, nullable = false, unique = true)
	private String codigo;

	@Column(length = 50, nullable = false)
	private String nome;

	@Column(length = 80, nullable = false)
	private String marca;

	@Column(length = 200)
	private String descricao;

	@Column
	private String arquivoFoto;

	@Column(scale = 2, precision = 10)
	private BigDecimal custoCompra = BigDecimal.ZERO;

	@Column
	private BigDecimal valorVenda = BigDecimal.ZERO;

	@Transient
	private String valorTexto;

	@Transient
	private String valorTotalTexto;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Categorias categoria;

	@Column(nullable = false)
	private Integer quantidadeAtual = 0;

	@Column(nullable = false)
	private Integer quantidadeInicial = 0;

	public Produto() {
	}

	public Produto(String codigo, String nome, String descricao, String marca, String arquivoFoto,
			BigDecimal custoCompra, Categorias categoria, Integer quantidadeAtual, Integer quantidadeInicial) {
		this.codigo = codigo;
		this.nome = nome;
		this.descricao = descricao;
		this.marca = marca;
		this.arquivoFoto = arquivoFoto;
		this.custoCompra = custoCompra;
		this.categoria = categoria;
		this.quantidadeAtual = quantidadeAtual;
		this.quantidadeInicial = quantidadeInicial;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

	public Integer getQuantidadeAtual() {
		return quantidadeAtual;
	}

	public void setQuantidadeAtual(Integer quantidade) {
		this.quantidadeAtual = quantidade;
	}

	public Integer getQuantidadeInicial() {
		return quantidadeInicial;
	}

	public void setQuantidadeInicial(Integer quantidadeInicial) {
		this.quantidadeInicial = quantidadeInicial;
	}

	public String getValorTexto() {
		return NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(valorVenda);
	}

	public void setValorTexto(String valorTexto) {
		this.valorTexto = valorTexto;
	}

	public String getValorTotalTexto() {
		return valorTotalTexto;
	}

	public void setValorTotalTexto(String valorTotalTexto) {
		this.valorTotalTexto = valorTotalTexto;
	}

}
