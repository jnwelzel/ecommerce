package com.jonwelzel.ejb;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jonwelzel.ejb.produto.ProdutoRemoteBusiness;
import com.jonwelzel.persistence.entities.Produto;
import com.jonwelzel.persistence.enumerations.Categorias;

@Singleton(name = StartupBean.EJB_NAME)
@Startup
public class StartupBean {

	public static final String EJB_NAME = "StartupEJB";

	@EJB
	private ProdutoRemoteBusiness produtoService;

	private Logger log = LoggerFactory.getLogger(getClass());

	private BigDecimal valorDespesasTotais = new BigDecimal(400);
	private BigDecimal percentualLucro = new BigDecimal(25);
	private BigDecimal rateioDespesas = null;

	@PostConstruct
	public void doStartup() {
		log.info("Inserindo produtos no banco de dados...");
		Produto produto1 = new Produto("7896547891597", "Moto X XT1058", "16GB 4G/3G Tela 4.5 10MP 2GB RAM",
				"Motorola", "resources/img/moto-x.jpg", new BigDecimal(846.65), Categorias.SMARTPHONE, 500, 500);
		produtoService.salvar(produto1);

		Produto produto2 = new Produto("7890286781035", "G2", "16/32 GB 4G/3G Tela 5.2 13MP 2GB RAM", "LG",
				"resources/img/lg-g2.jpg", new BigDecimal(912.2), Categorias.SMARTPHONE, 300, 300);
		produtoService.salvar(produto2);

		Produto produto3 = new Produto("7896543570254", "Galaxy S4", "16/32 GB 4G/3G Tela 5.0 13MP 2GB RAM", "Samsung",
				"resources/img/galaxy-s4.jpg", new BigDecimal(1013.25), Categorias.SMARTPHONE, 800, 800);
		produtoService.salvar(produto3);

		Produto produto4 = new Produto("7894866657149", "Moto G Colors Edition XT1033",
				"8/16GB 3G Tela 4.5 5MP 1GB RAM", "Motorola", "resources/img/moto-g.jpg", new BigDecimal(455.6),
				Categorias.SMARTPHONE, 400, 400);
		produtoService.salvar(produto4);

		Produto produto5 = new Produto("7896654703284", "iPhone 5S", "16/32/64GB 3G/4G Tela 4.0 8MP 1GB RAM", "Apple",
				"resources/img/iphone.jpg", new BigDecimal(1551.99), Categorias.SMARTPHONE, 400, 400);
		produtoService.salvar(produto5);

		Produto produto6 = new Produto("7896541470644", "Lumia 1520", "16GB 3G/4G Tela 6.0 20MP 2GB RAM", "Nokia",
				"resources/img/lumia.jpg", new BigDecimal(1270.5), Categorias.SMARTPHONE, 100, 100);
		produtoService.salvar(produto6);

		Integer quantidade = produtoService.getQuantidadeTotalInicial();
		rateioDespesas = valorDespesasTotais.divide(new BigDecimal(quantidade));

		atualizarPrecos();
	}

	public void alterarConfiguracoes(BigDecimal percentualLucro, BigDecimal valorDespesasTotais) {
		this.percentualLucro = percentualLucro;
		this.valorDespesasTotais = valorDespesasTotais;
		rateioDespesas = valorDespesasTotais.divide(new BigDecimal(produtoService.getQuantidadeTotalInicial()));

		atualizarPrecos();
	}

	@Asynchronous
	public void atualizarPrecos() {
		List<Produto> produtos = produtoService.listarAtivos();
		for (Produto produto : produtos) {
			BigDecimal valorVenda = produto.getCustoCompra().add(rateioDespesas);
			if (percentualLucro.compareTo(BigDecimal.ZERO) == 1) {
				BigDecimal percentual = percentualLucro.divide(new BigDecimal(100));
				valorVenda = valorVenda.add(valorVenda.multiply(percentual));
			}
			produto.setValorVenda(valorVenda);
			produto.setValorTexto(NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(valorVenda));
			produtoService.salvar(produto);
		}
	}

	public BigDecimal getValorDespesasTotais() {
		return valorDespesasTotais;
	}

	public BigDecimal getPercentualLucro() {
		return percentualLucro;
	}

	public BigDecimal getRateioDespesas() {
		return rateioDespesas;
	}

}