package com.jonwelzel.web.servlets;

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

@WebServlet(urlPatterns = { "/produtos" })
public class ProdutosServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Inject
	private IProdutoService service;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Produto> produtos = service.listar();
		request.setAttribute("produtos", produtos);
		getServletContext().getRequestDispatcher("/produtos.jsp").forward(request, response);
	}

}
