package com.example.controller.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.ultil.EscapeUtils;

@WebServlet(urlPatterns = { "/search-result" })
public class SearchController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher requestDispatcher;
		resp.setContentType("text/html;charset=UTF-8");
		resp.setCharacterEncoding("utf-8");

		try {
			String keyword = req.getParameter("keyword");

			// Filter prevent XSS
			keyword = EscapeUtils.escapeHtml(keyword);

			req.setAttribute("keyword", keyword);

			requestDispatcher = req.getRequestDispatcher("/views/web/search.jsp");
			requestDispatcher.forward(req, resp);
		} catch (Exception e) {
			requestDispatcher = req.getRequestDispatcher("/views/web/error.jsp");
			requestDispatcher.forward(req, resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}

}
