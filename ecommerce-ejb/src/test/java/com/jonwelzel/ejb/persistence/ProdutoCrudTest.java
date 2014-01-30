package com.jonwelzel.ejb.persistence;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jonwelzel.ejb.produto.ProdutoBean;
import com.jonwelzel.ejb.produto.ProdutoRemoteBusiness;
import com.jonwelzel.persistence.entities.Produto;

public class ProdutoCrudTest {

	private static EJBContainer ejbContainer;
	private static ProdutoRemoteBusiness service;
	private Logger log = LoggerFactory.getLogger(getClass());

	@BeforeClass
	public static void setUpClass() throws Exception {
		System.out.println("Creating the container");

		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put(EJBContainer.MODULES, new File("target/classes"));

		ejbContainer = EJBContainer.createEJBContainer(properties);
		service = (ProdutoRemoteBusiness) ejbContainer.getContext().lookup(
				"java:global/classes/" + ProdutoBean.EJB_NAME);
	}

	@AfterClass
	public static void tearDown() {
		ejbContainer.close();
		System.out.println("Closing the container");
	}

	@Test
	public void test() throws NamingException {
		log.info("Starting \"CRUD\" test");
		String codigo = "7896547891597";
		Produto produto = service.encontrarPorCodigo(codigo);
		assertNotNull("Produto com codigo \"" + codigo + "\" não foi encontrado.", produto);

		assertTrue("Quantidade total inicial não está correta.", service.getQuantidadeTotalInicial() == 2500);
		assertTrue("Quantidade total atual não está correta.", service.getQuantidadeTotalAtual() == 2500);

		String novaDescricao = "Nova descrição";
		produto.setDescricao(novaDescricao);
		produto.setQuantidadeAtual(400);
		produto = service.salvar(produto);
		assertTrue("Não foi possível editar a descrição do produto", produto.getDescricao().equals(novaDescricao));

		assertTrue("Quantidade total atual do \"Moto X\" não está correta.",
				service.getQuantidadeTotalAtualPorProduto(produto) == 400);

		assertTrue(
				"Não foi possivel encontrar os produtos usando seus codigos.",
				service.encontrarPorCodigos(Arrays.asList(new String[] { "7896547891597", "7890286781035" })).size() == 2);

		service.excluir(produto);
	}

}
