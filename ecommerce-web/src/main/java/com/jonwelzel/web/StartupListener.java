package com.jonwelzel.web;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jonwelzel.ejb.produto.IProdutoService;
import com.jonwelzel.persistence.entities.Produto;
import com.jonwelzel.persistence.enumerations.Categorias;

@Singleton
@Startup
public class StartupListener {

	@Inject
	private IProdutoService produtoService;
	private Logger log = LoggerFactory.getLogger(getClass());

	private BigDecimal valorDespesasTotais = new BigDecimal(400);
	private BigDecimal percentualLucro = new BigDecimal(25);
	private BigDecimal rateioDespesas = null;

	@PostConstruct
	public void doStartup() {
		log.info("Inserindo produtos no banco de dados...");
		Produto produto1 = new Produto("Moto X XT1058", "16GB 4G/3G Tela 4.5 10MP 2GB RAM", "Motorola",
				"resources/img/moto-x.jpg", new BigDecimal(846.65), Categorias.SMARTPHONE, 500);
		produtoService.salvar(produto1);

		Produto produto2 = new Produto("G2", "16/32 GB 4G/3G Tela 5.2 13MP 2GB RAM", "LG", "resources/img/lg-g2.jpg",
				new BigDecimal(912.2), Categorias.SMARTPHONE, 300);
		produtoService.salvar(produto2);

		Produto produto3 = new Produto("Galaxy S4", "16/32 GB 4G/3G Tela 5.0 13MP 2GB RAM", "Samsung",
				"resources/img/galaxy-s4.jpg", new BigDecimal(1013.25), Categorias.SMARTPHONE, 800);
		produtoService.salvar(produto3);

		Produto produto4 = new Produto("Moto G Colors Edition XT1033", "8/16GB 3G Tela 4.5 5MP 1GB RAM", "Motorola",
				"resources/img/moto-g.jpg", new BigDecimal(455.6), Categorias.SMARTPHONE, 400);
		produtoService.salvar(produto4);

		Produto produto5 = new Produto("iPhone 5S", "16/32/64GB 3G/4G Tela 4.0 8MP 1GB RAM", "Apple",
				"resources/img/iphone.jpg", new BigDecimal(1551.99), Categorias.SMARTPHONE, 400);
		produtoService.salvar(produto5);

		Produto produto6 = new Produto("Lumia 1520", "16GB 3G/4G Tela 6.0 20MP 2GB RAM", "Nokia",
				"resources/img/lumia.jpg", new BigDecimal(1270.5), Categorias.SMARTPHONE, 100);
		produtoService.salvar(produto6);

		Integer quantidade = produtoService.getQuantidadeTotal();
		rateioDespesas = valorDespesasTotais.divide(new BigDecimal(quantidade));
	}

	public BigDecimal getValorDespesasTotais() {
		return valorDespesasTotais;
	}

	public void setValorDespesasTotais(BigDecimal valorDespesasTotais) {
		this.valorDespesasTotais = valorDespesasTotais;
	}

	public BigDecimal getPercentualLucro() {
		return percentualLucro;
	}

	public void setPercentualLucro(BigDecimal percentualLucro) {
		this.percentualLucro = percentualLucro;
	}

	public BigDecimal getRateioDespesas() {
		return rateioDespesas;
	}

	public void setRateioDespesas(BigDecimal rateioDespesas) {
		this.rateioDespesas = rateioDespesas;
	}

}