package com.jonwelzel.web.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jonwelzel.ejb.StartupBean;
import com.jonwelzel.ejb.produto.ProdutoRemoteBusiness;
import com.jonwelzel.persistence.entities.Produto;

@WebServlet(urlPatterns = { "/produtos" })
public class ProdutosServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@EJB
	private ProdutoRemoteBusiness service;

	@EJB
	private StartupBean startupListener;

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
		}
		request.setAttribute("produtos", produtos);
		getServletContext().getRequestDispatcher("/produtos.jsp").forward(request, response);
	}

	/**
	 * Esse método é pra adicionar um produto ao carrinho diretamente da página
	 * de produtos.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		HttpSession session = request.getSession(true); // Nova sessão caso ainda não exista uma
		String codigo = request.getParameter("codigo");
		if (session.getAttribute(codigo) != null) {
			// Produto já está no carrinho
			Integer quantidadeAtual = (Integer) session.getAttribute(codigo);
			session.setAttribute(codigo, quantidadeAtual + 1);
		} else {
			// Produto ainda n está no carrinho
			session.setAttribute(codigo, 1);
		}
		response.sendRedirect("carrinho");
	}
}
