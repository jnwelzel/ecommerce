package com.jonwelzel.web.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jonwelzel.ejb.produto.ProdutoRemoteBusiness;
import com.jonwelzel.persistence.entities.Produto;

@WebServlet(urlPatterns = { "/carrinho" })
public class CarrinhoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@EJB
	private ProdutoRemoteBusiness service;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> codigos = new ArrayList<>();
		List<Produto> produtos = null;

		if (request.getSession() != null) {
			Enumeration<String> carrinho = request.getSession().getAttributeNames();
			while (carrinho.hasMoreElements()) {
				String string = carrinho.nextElement();
				if (string.startsWith("789")) {
					codigos.add(string);
				}
			}
			if (!codigos.isEmpty()) {
				produtos = service.encontrarPorCodigos(codigos);
				if (produtos != null && !produtos.isEmpty()) {
					for (Produto produto : produtos) {
						produto.setQuantidadeAtual((Integer) request.getSession().getAttribute(produto.getCodigo()));
						BigDecimal valorTotalProdutoCarrinho = produto.getValorVenda().multiply(
								new BigDecimal(produto.getQuantidadeAtual()));
						produto.setValorTotalTexto(NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(
								valorTotalProdutoCarrinho));
					}
				}
			}
		}

		request.setAttribute("produtos", produtos);
		getServletContext().getRequestDispatcher("/carrinho.jsp").forward(request, response);
	}

}
