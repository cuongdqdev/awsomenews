package com.example.controller.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.NewsDAO;
import com.example.model.NewsModel;

@WebServlet(urlPatterns = { "/detail-news" })
public class DetailController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher requestDispatcher;
		resp.setContentType("text/html;charset=UTF-8");
		resp.setCharacterEncoding("utf-8");

		String ID = req.getParameter("ID");
		NewsDAO newsDAO = new NewsDAO();
		NewsModel news = new NewsModel();
		
		boolean flag = false;

		try {
			ArrayList<NewsModel> listNews = newsDAO.getListNews();
			for (NewsModel newsModel : listNews) {
				if(Integer.parseInt(ID)==newsModel.getID()) {
					flag = true;
				}
			}
			if (!ID.equalsIgnoreCase("") && (Integer.parseInt(ID) > 0) && (flag==true)) {
				news = newsDAO.getDetailNews(Integer.parseInt(ID));
				req.setAttribute("news", news);
				requestDispatcher = req.getRequestDispatcher("/views/web/detail.jsp");
				requestDispatcher.forward(req, resp);
			} else {
				requestDispatcher = req.getRequestDispatcher("/views/web/error.jsp");
				requestDispatcher.forward(req, resp);
			}
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
