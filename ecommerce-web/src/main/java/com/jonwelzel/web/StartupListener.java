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

	@PostConstruct
	public void doStartup() {
		log.info("Inserindo produtos no banco de dados...");
		Produto produto1 = new Produto("Moto X XT1058", "16GB 4G/3G Tela 4.5 10MP 2GB RAM", "Motorola",
				"resources/img/moto-x.jpg", new BigDecimal(800), Categorias.SMARTPHONE);
		produtoService.salvar(produto1);

		Produto produto2 = new Produto("G2", "16/32 GB 4G/3G Tela 5.2 13MP 2GB RAM", "LG", "resources/img/lg-g2.jpg",
				new BigDecimal(900), Categorias.SMARTPHONE);
		produtoService.salvar(produto2);

		Produto produto3 = new Produto("Galaxy S4", "16/32 GB 4G/3G Tela 5 13MP 2GB RAM", "Samsung",
				"resources/img/galaxy-s4.jpg", new BigDecimal(1000), Categorias.SMARTPHONE);
		produtoService.salvar(produto3);
	}

}