package com.jonwelzel.web.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jonwelzel.ejb.produto.IProdutoService;
import com.jonwelzel.persistence.entities.Produto;
import com.jonwelzel.web.StartupListener;

@WebServlet(urlPatterns = { "/produtos" })
public class ProdutosServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Inject
	private IProdutoService service;

	@Inject
	private StartupListener startupListener;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Produto> produtos = service.listarAtivos();
		for (Produto produto : produtos) {
			BigDecimal valorVenda = produto.getCustoCompra().add(startupListener.getRateioDespesas());
			if (startupListener.getPercentualLucro().compareTo(BigDecimal.ZERO) == 1) {
				BigDecimal percentual = startupListener.getPercentualLucro().divide(new BigDecimal(100));
				valorVenda = valorVenda.add(valorVenda.multiply(percentual));
			}
			produto.setValorVenda(valorVenda);
			produto.setValorTexto(NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(valorVenda));
		}
		request.setAttribute("produtos", produtos);
		getServletContext().getRequestDispatcher("/produtos.jsp").forward(request, response);
	}

}
