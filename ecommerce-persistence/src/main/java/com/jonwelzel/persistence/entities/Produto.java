package com.jonwelzel.persistence.entities;

import java.math.BigDecimal;
import java.text.NumberFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.jonwelzel.persistence.enumerations.Categorias;

/**
 * 
 * @author jwelzel
 * 
 */
@Entity
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

	@Column
	private BigDecimal custoCompra;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Categorias categoria;

	public Produto() {
	}

	public Produto(String nome, String descricao, String marca, String arquivoFoto, BigDecimal custoCompra,
			Categorias categoria) {
		this.nome = nome;
		this.descricao = descricao;
		this.marca = marca;
		this.arquivoFoto = arquivoFoto;
		this.custoCompra = custoCompra;
		this.categoria = categoria;
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

	public String getCustoFormatado() {
		return custoCompra != null ? NumberFormat.getCurrencyInstance().format(custoCompra) : "R$ 0,00";
	}

}
