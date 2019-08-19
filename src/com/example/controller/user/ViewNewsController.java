package com.example.controller.user;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.dao.NewsDAO;
import com.example.model.NewsModel;
import com.example.model.UserModel;

@WebServlet(urlPatterns = { "/view-news" })
public class ViewNewsController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher requestDispatcher;
		resp.setContentType("text/html;charset=UTF-8");
		resp.setCharacterEncoding("utf-8");

		HttpSession httpSession = req.getSession();
		
		try {
			UserModel user = (UserModel) httpSession.getAttribute("user");
			String author = user.getUserName();
			if (user.getRole() == 0 && user.getRoleName().equals("user")) {
				NewsDAO newsDAO = new NewsDAO();
				try {
					ArrayList<NewsModel> listNews = newsDAO.getNewsDetailByAuthor(author);
					req.setAttribute("listNews", listNews);
					requestDispatcher = req.getRequestDispatcher("/views/user/viewnews.jsp");
					requestDispatcher.forward(req, resp);
				} catch (Exception e) {
					requestDispatcher = req.getRequestDispatcher("/views/user/error.jsp");
					requestDispatcher.forward(req, resp);
				}
			}
			else {
				requestDispatcher = req.getRequestDispatcher("/views/user/error.jsp");
				requestDispatcher.forward(req, resp);
			}
		} catch (Exception e) {
			requestDispatcher = req.getRequestDispatcher("/views/user/error.jsp");
			requestDispatcher.forward(req, resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}

}
