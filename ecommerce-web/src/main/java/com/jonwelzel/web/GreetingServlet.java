package com.jonwelzel.web;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jonwelzel.ejb.produto.IProdutoService;
import com.jonwelzel.persistence.entities.Produto;

/**
 * Greeting servlet
 */
@WebServlet(urlPatterns = { "/greeting" })
public class GreetingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private IProdutoService produtoService;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		String name = request.getParameter("name");
		//		request.setAttribute("greeting", greeting);

		List<Produto> produtos = produtoService.listar();
		if (produtos == null || produtos.isEmpty()) {
			throw new RuntimeException("Não criou os produtos na inicialização.");
		} else {
			request.setAttribute("greeting", produtos.size());
		}

		getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		processRequest(request, response);
	}

}
