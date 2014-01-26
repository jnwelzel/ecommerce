package com.jonwelzel.persistence;

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

import com.jonwelzel.ejb.produto.IProdutoService;
import com.jonwelzel.persistence.entities.Produto;

public class ProdutoCrudTest {

	private static EJBContainer ejbContainer;
	private static IProdutoService service;

	@BeforeClass
	public static void setUpClass() throws Exception {
		System.out.println("Creating the container");

		Map<String, Object> properties = new HashMap<String, Object>();
		//		properties.put(EJBContainer.APP_NAME, "foo");
		properties.put(EJBContainer.MODULES, new File("target/classes"));
		properties.put("org.glassfish.ejb.embedded.glassfish.installation.root", "./src/test/glassfish");
		properties.put("org.glassfish.ejb.embedded.glassfish.configuration.file",
				"./src/test/glassfish/domains/domain1/config/domain.xml");

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
		System.out.println("Starting test");
		assertNotNull("ProdutoService não encontrado.", service);
		Produto produto = new Produto("Smartphone Motorola Moto X XT1058", "16GB 4G/3G Tela 4.5 10MP 2GB RAM", "",
				new BigDecimal(1200));
		assertTrue("Id do produto está nulo.", service.salvar(produto).getId() != null);
	}
}
