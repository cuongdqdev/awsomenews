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


@WebServlet(urlPatterns = {"/home-page"})
public class HomeController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("utf-8");
        
        NewsDAO newsDAO = new NewsDAO();
        try {
        	ArrayList<NewsModel> listNews = newsDAO.getListNews();
    		req.setAttribute("listNews", listNews);
    		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/web/home.jsp");
    		requestDispatcher.forward(req, resp);
		} catch (Exception e) {
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/web/error.jsp");
			requestDispatcher.forward(req, resp);
		}
		//super.doGet(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
	

}
