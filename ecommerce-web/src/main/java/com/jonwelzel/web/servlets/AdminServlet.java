package com.jonwelzel.web.servlets;

import java.io.IOException;
import java.math.BigDecimal;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jonwelzel.web.StartupListener;

@WebServlet(urlPatterns = "/admin")
public class AdminServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Inject
	private StartupListener startupListener;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("despesasTotais", startupListener.getValorDespesasTotais());
		request.setAttribute("lucro", startupListener.getPercentualLucro());
		getServletContext().getRequestDispatcher("/admin.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		BigDecimal despesas = request.getParameter("despesasTotais") == null
				|| request.getParameter("despesasTotais").toString().trim().isEmpty() ? new BigDecimal(400)
				: new BigDecimal(request.getParameter("despesasTotais").toString());
		BigDecimal lucro = request.getParameter("lucro") == null
				|| request.getParameter("lucro").toString().trim().isEmpty() ? new BigDecimal(0) : new BigDecimal(
				request.getParameter("lucro").toString());
		startupListener.setPercentualLucro(lucro);
		startupListener.setValorDespesasTotais(despesas);
		request.setAttribute("despesasTotais", despesas);
		request.setAttribute("lucro", lucro);
		getServletContext().getRequestDispatcher("/admin.jsp").forward(request, response);
	}

}
