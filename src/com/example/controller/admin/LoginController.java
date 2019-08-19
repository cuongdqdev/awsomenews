package com.example.controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.dao.UserDAO;
import com.example.model.UserModel;

@WebServlet(urlPatterns = { "/login" })
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher requestDispatcher;
		HttpSession httpSession = req.getSession();
		UserModel user = (UserModel) httpSession.getAttribute("user");
		if (user != null) {
			if (user.getRole() == 0 && user.getRoleName().equals("user")) {
				resp.sendRedirect("/AwesomeNews/user-home");
			} else if (user.getRole() == 1 && user.getRoleName().equals("admin")) {
				resp.sendRedirect("/AwesomeNews/admin-home");
			}

		} else {

			requestDispatcher = req.getRequestDispatcher("/views/admin/login.jsp");
			requestDispatcher.forward(req, resp);	
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getParameter("username") != null && req.getParameter("password") != null) {
			String username = req.getParameter("username");
			String password = req.getParameter("password");

			if (UserDAO.checkUserLogin(username, password)) {
				UserModel user = UserDAO.getUser(username, password);
				HttpSession httpSession = req.getSession();
				httpSession.setAttribute("user", user);

				String roleName = user.getRoleName();
				int role = user.getRole();

				if (role == 0 && roleName.equals("user")) {
					resp.sendRedirect("/AwesomeNews/user-home");
				} else if (role == 1 && roleName.equals("admin")) {
					resp.sendRedirect("/AwesomeNews/admin-home");
				}
			} else {
				resp.sendRedirect("/AwesomeNews/login?e=1");
			}
		} else {
			resp.sendRedirect("/AwesomeNews/login?e=1");
		}
	}

}
