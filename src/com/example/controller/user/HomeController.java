package com.example.controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.model.UserModel;

@WebServlet(urlPatterns = { "/user-home" })
public class HomeController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		HttpSession httpSession = req.getSession();
		UserModel user = (UserModel) httpSession.getAttribute("user");
		try {
			if (user.getRole() == 0 && user.getRoleName().equals("user")) {
				dispatcher = req.getRequestDispatcher("/views/user/home.jsp");
				dispatcher.forward(req, resp);
			}
			else {
				dispatcher = req.getRequestDispatcher("/views/user/error.jsp");
				dispatcher.forward(req, resp);
			}
		} catch (Exception e) {
			dispatcher = req.getRequestDispatcher("/views/user/error.jsp");
			dispatcher.forward(req, resp);
		}	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
