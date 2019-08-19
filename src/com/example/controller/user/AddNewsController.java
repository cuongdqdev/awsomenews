package com.example.controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.example.dao.NewsDAO;
import com.example.model.NewsModel;
import com.example.model.UserModel;
import com.example.ultil.CheckUploadFile;
import com.example.ultil.EscapeUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet("/add-news")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1024 * 1024 * 50, maxRequestSize = 1024 * 1024
		* 100)
public class AddNewsController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String UPLOAD_DIR = "template\\images";

	private static final String path = "D:/AwesomeWorkspaces/awesomenews/WebContent/template/images/";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		HttpSession httpSession = req.getSession();
		UserModel user = (UserModel) httpSession.getAttribute("user");
		try {
			if (user.getRole() == 0 && user.getRoleName().equals("user")) {
				dispatcher = req.getRequestDispatcher("/views/user/addnews.jsp");
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
		resp.setContentType("text/html;charset=UTF-8");
		resp.setCharacterEncoding("utf-8");
		RequestDispatcher dispatcher;
		
		HttpSession httpSession = req.getSession();
		UserModel user = (UserModel) httpSession.getAttribute("user");

		String fileName = uploadFile(req);
		
		NewsModel news = new NewsModel();

		news.setTitle(EscapeUtils.escapeHtml(req.getParameter("title")));
		news.setDescription(EscapeUtils.escapeHtml(req.getParameter("description")));
		news.setContent(EscapeUtils.escapeHtml(req.getParameter("content")));
		// EXPLOIT IDOR HORIZONTAL AND STORED XSS
		//news.setAuthor(EscapeUtils.escapeHtml(req.getParameter("author")));
		news.setAuthor(user.getUserName());
		news.setImage(fileName);

		
		String pathFile = "";
		pathFile = path + fileName;
		File output = new File(pathFile);
		System.out.println("Output: " + output);

		if (!CheckUploadFile.checkExtension(fileName)) {
			output.delete();
			dispatcher = req.getRequestDispatcher("/views/user/addnews.jsp?e=1");
			dispatcher.forward(req, resp);
		} else if (!CheckUploadFile.checkContent(output) && !CheckUploadFile.checkContent(output)) {
			output.delete();
			dispatcher = req.getRequestDispatcher("/views/user/addnews.jsp?e=1");
			dispatcher.forward(req, resp);
		} else if (NewsDAO.addNews(news)) {
			dispatcher = req.getRequestDispatcher("/views/user/addnews.jsp?e=0");
			dispatcher.forward(req, resp);
		} else {
			dispatcher = req.getRequestDispatcher("/views/user/addnews.jsp?e=1");
			dispatcher.forward(req, resp);
		}
	}

	private String uploadFile(HttpServletRequest req) throws IOException, ServletException {
		String fileName = "";
		String newFileName = "";

		try {
			Part filePart = req.getPart("image");

			System.out.println("filePart: " + filePart); // org.apache.catalina.core.ApplicationPart@77533686

			// fileName: picture-001.jpg
			fileName = (String) getFileName(filePart);
			newFileName = CheckUploadFile.generateNewFileName(fileName);

			System.out.println("fileName: " + fileName);
			System.out.println("newFileName: " + newFileName);

			// applicationPath:
			String applicationPath = req.getServletContext().getRealPath("");
			System.out.println(applicationPath);
			// File.separator: \
			String basePath = applicationPath + UPLOAD_DIR + File.separator;
			System.out.println(basePath);
			InputStream inputStream = null;
			OutputStream outputStream = null;
			OutputStream outputStream2 = null;
			// String path = "D:/AwesomeWorkspaces/awesomenews/WebContent/template/images/";
			try {
				File outputFilePath = new File(basePath + newFileName);
				File outputFilePath2 = new File(path + newFileName);
				inputStream = filePart.getInputStream();
				outputStream = new FileOutputStream(outputFilePath);
				outputStream2 = new FileOutputStream(outputFilePath2);

				int read = 0;
				final byte[] bytes = new byte[1024];
				while ((read = inputStream.read(bytes)) != -1) {
					outputStream.write(bytes, 0, read);
					outputStream2.write(bytes, 0, read);
				}

			} catch (Exception e) {
				e.printStackTrace();
				fileName = "";
			} finally {
				if (inputStream != null) {
					inputStream.close();
				}
				if (outputStream != null) {
					outputStream.close();
					outputStream2.close();
				}
			}
		} catch (Exception e) {
			fileName = "";
		}
		return newFileName;
	}

	private String getFileName(Part part) {
		final String partHeader = part.getHeader("content-disposition");
		System.out.println("partHeader :" + partHeader); // form-data; name="image"; filename="kali.png"
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}
}
