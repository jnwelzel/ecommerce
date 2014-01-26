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

@Singleton
@Startup
public class StartupListener {

	@Inject
	private IProdutoService produtoService;
	private Logger log = LoggerFactory.getLogger(getClass());

	@PostConstruct
	public void doStartup() {
		log.info("Inserindo produtos no banco de dados...");
		Produto produto = new Produto("Smartphone Motorola Moto X XT1058", "16GB 4G/3G Tela 4.5 10MP 2GB RAM", "",
				new BigDecimal(1200));
		produtoService.salvar(produto).getId();
	}

}