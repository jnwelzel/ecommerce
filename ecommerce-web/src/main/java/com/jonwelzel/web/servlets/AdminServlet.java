package com.jonwelzel.web.servlets;

import java.io.IOException;
import java.math.BigDecimal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jonwelzel.ejb.StartupBean;

@WebServlet(urlPatterns = "/admin")
public class AdminServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@EJB
	private StartupBean startupBean;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("despesasTotais", startupBean.getValorDespesasTotais());
		request.setAttribute("lucro", startupBean.getPercentualLucro());
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

		startupBean.alterarConfiguracoes(lucro, despesas);

		request.setAttribute("despesasTotais", despesas);
		request.setAttribute("lucro", lucro);
		getServletContext().getRequestDispatcher("/admin.jsp").forward(request, response);
	}

}
