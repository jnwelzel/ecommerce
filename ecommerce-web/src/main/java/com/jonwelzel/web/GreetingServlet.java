package com.jonwelzel.web;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jonwelzel.ejb.HelloService;

/**
 * Greeting servlet
 */
@WebServlet(urlPatterns={"/greeting"})
public class GreetingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private HelloService helloService;
	
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String greeting = helloService.greet(name);
		request.setAttribute("greeting", greeting);
		getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
