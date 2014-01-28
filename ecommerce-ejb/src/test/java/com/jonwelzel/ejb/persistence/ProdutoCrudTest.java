package com.jonwelzel.ejb.persistence;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jonwelzel.ejb.produto.IProdutoService;
import com.jonwelzel.persistence.entities.Produto;
import com.jonwelzel.persistence.enumerations.Categorias;

public class ProdutoCrudTest {

	private static EJBContainer ejbContainer;
	private static IProdutoService service;
	private Logger log = LoggerFactory.getLogger(getClass());

	@BeforeClass
	public static void setUpClass() throws Exception {
		System.out.println("Creating the container");

		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put(EJBContainer.MODULES, new File("target/classes"));

		ejbContainer = EJBContainer.createEJBContainer(properties);
		service = (IProdutoService) ejbContainer.getContext().lookup("java:global/classes/ProdutoService");
	}

	@AfterClass
	public static void tearDown() {
		ejbContainer.close();
		System.out.println("Closing the container");
	}

	@Test
	public void test() throws NamingException {
		log.info("Starting \"CRUD\" test");
		assertNotNull("ProdutoService não encontrado.", service);
		Produto produto = new Produto("Moto X XT1058", "16GB 4G/3G Tela 4.5 10MP 2GB RAM", "Motorola",
				"resources/img/moto-x.jpg", new BigDecimal(800), Categorias.SMARTPHONE);
		Long produtoId = service.salvar(produto).getId();
		assertTrue("Id do produto está nulo.", produtoId != null);

		produto = null;
		produto = service.encontrar(produtoId);
		assertNotNull("Produto com id \"" + produtoId + "\" não foi encontrado.", produto);

		String novaDescricao = "Nova descrição";
		produto.setDescricao(novaDescricao);
		produto = service.salvar(produto);
		assertTrue("Não foi possível editar a descrção do produto", produto.getDescricao().equals(novaDescricao));

		service.excluir(produto);
	}

}
