package com.jonwelzel.web.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

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
		BigDecimal valorTotalVenda = BigDecimal.ZERO;

		if (request.getSession() != null) {
			for (Enumeration<String> enumeration = request.getSession().getAttributeNames(); enumeration
					.hasMoreElements();) {
				String string = enumeration.nextElement();
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
						valorTotalVenda = valorTotalVenda.add(valorTotalProdutoCarrinho);
					}
				}
			}
		}

		request.setAttribute("produtos", produtos);
		request.setAttribute("total", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(valorTotalVenda));
		getServletContext().getRequestDispatcher("/carrinho.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		List<Produto> produtos = new ArrayList<>();
		BigDecimal valorTotalVenda = BigDecimal.ZERO;

		for (Entry<String, String[]> param : request.getParameterMap().entrySet()) {
			if (param.getKey().startsWith("789")) {
				String newValue = param.getValue()[0]; // Valor que vem do campo tem q ser numerico
				if (newValue != null && StringUtils.isNotBlank(newValue) && StringUtils.isNumeric(newValue)
						&& Integer.valueOf(newValue) > 0) {
					Produto produto = service.encontrarPorCodigo(param.getKey());
					produto.setQuantidadeAtual(Integer.valueOf(newValue));
					BigDecimal valorTotalProdutoCarrinho = produto.getValorVenda().multiply(
							new BigDecimal(produto.getQuantidadeAtual()));
					produto.setValorTotalTexto(NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(
							valorTotalProdutoCarrinho));
					produtos.add(produto);
					valorTotalVenda = valorTotalVenda.add(valorTotalProdutoCarrinho);
				}
			}
		}

		if (produtos == null || produtos.isEmpty()) {
			// Pode zerar a sessão do cara se n tem produtos
			HttpSession session = request.getSession(false);
			if (session != null) {
				session.invalidate();
			}
			produtos = null;
		}

		request.setAttribute("produtos", produtos);
		request.setAttribute("total", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(valorTotalVenda));
		getServletContext().getRequestDispatcher("/carrinho.jsp").forward(request, response);
	}

}
