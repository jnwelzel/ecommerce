package com.jonwelzel.persistence.entities;

import java.math.BigDecimal;
import java.text.NumberFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

	@Column(length = 200)
	private String descricao;

	@Column
	private String arquivoFoto;

	@Column
	private BigDecimal custoCompra;

	public Produto() {
	}

	public Produto(String nome, String descricao, String arquivoFoto, BigDecimal custoCompra) {
		this.nome = nome;
		this.descricao = descricao;
		this.arquivoFoto = arquivoFoto;
		this.custoCompra = custoCompra;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getCustoFormatado() {
		return custoCompra != null ? NumberFormat.getCurrencyInstance().format(custoCompra) : "R$ 0,00";
	}

}
