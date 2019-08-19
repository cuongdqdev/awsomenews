package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.example.model.NewsModel;

public class NewsDAO {
	public ArrayList<NewsModel> getListNews() {
		Connection connection = DBConfig.getConnection();
		ArrayList<NewsModel> results = new ArrayList<>();
		String sql = "SELECT * FROM tb_news";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		if (connection != null) {
			try {
				statement = connection.prepareStatement(sql);
				resultSet = statement.executeQuery();
				while (resultSet.next()) {
					NewsModel news = new NewsModel();
					news.setID(resultSet.getInt("id"));
					news.setTitle(resultSet.getString("title"));
					news.setImage(resultSet.getString("image"));
					news.setContent(resultSet.getString("content"));
					news.setAuthor(resultSet.getString("author"));
					results.add(news);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return results;
	}

	public NewsModel getDetailNews(int ID) {
		Connection connection = DBConfig.getConnection();
		NewsModel news = new NewsModel();
		String sql = "SELECT * FROM tb_news WHERE id = ?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		if (connection != null) {
			try {
				statement = connection.prepareStatement(sql);
				statement.setInt(1, ID);
				resultSet = statement.executeQuery();
				while (resultSet.next()) {
					news.setID(resultSet.getInt("id"));
					news.setTitle(resultSet.getString("title"));
					news.setImage(resultSet.getString("image"));
					news.setContent(resultSet.getString("content"));
					news.setAuthor(resultSet.getString("author"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return news;
	}

	public ArrayList<NewsModel> getNewsDetailByAuthor(String author) {
		Connection connection = DBConfig.getConnection();
		ArrayList<NewsModel> results = new ArrayList<>();
		String sql = "SELECT * FROM tb_news WHERE author = ?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		if (connection != null) {
			try {
				statement = connection.prepareStatement(sql);
				statement.setString(1, author);
				resultSet = statement.executeQuery();
				while (resultSet.next()) {
					NewsModel news = new NewsModel();
					news.setID(resultSet.getInt("id"));
					news.setTitle(resultSet.getString("title"));
					news.setImage(resultSet.getString("image"));
					news.setContent(resultSet.getString("content"));
					news.setAuthor(resultSet.getString("author"));
					results.add(news);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return results;
	}

	public ArrayList<NewsModel> searchNewsByKeyword(String keyword) {
		Connection connection = DBConfig.getConnection();
		ArrayList<NewsModel> results = new ArrayList<>();
		String sql = "SELECT * FROM tb_news WHERE title LIKE ?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		if (connection != null) {
			try {
				statement = connection.prepareStatement(sql);
				statement.setString(1, "%" + keyword + "%");
				resultSet = statement.executeQuery();
				while (resultSet.next()) {
					NewsModel news = new NewsModel();
					news.setID(resultSet.getInt("id"));
					news.setTitle(resultSet.getString("title"));
					news.setImage(resultSet.getString("image"));
					news.setContent(resultSet.getString("content"));
					news.setAuthor(resultSet.getString("author"));
					results.add(news);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return results;
	}

	public static boolean addNews(NewsModel news) {
		String sql = "INSERT INTO tb_news(title,image,description,content,author) VALUES(?,?,?,?,?)";
		Connection connection = DBConfig.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, news.getTitle());
			preparedStatement.setString(2, news.getImage());
			preparedStatement.setString(3, news.getDescription());
			preparedStatement.setString(4, news.getContent());
			preparedStatement.setString(5, news.getAuthor());

			return preparedStatement.executeUpdate() == 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) {
		// Check get news detail by author
		/*
		 * NewsDAO newsDAO = new NewsDAO(); ArrayList<NewsModel> resultLists =
		 * newsDAO.getNewsDetailByAuthor("btv1"); for (NewsModel newsModel :
		 * resultLists) { System.out.println(newsModel.getID() + " " +
		 * newsModel.getTitle()); }
		 */

		// Check add news
		/*
		 * NewsModel news = new
		 * NewsModel("title_test2","image_test2","description_test2","content_test2",
		 * "author_test2"); if(NewsDAO.addNews(news)) {
		 * System.out.println("Add success!"); } else { System.out.println("Add fail!");
		 * }
		 */

		NewsDAO newsDAO = new NewsDAO();
		// Test get list news

		ArrayList<NewsModel> newsList = newsDAO.getListNews();
		for (NewsModel newsModel : newsList) {
			System.out.println(newsModel.getID() + " " + newsModel.getTitle());
		}

		// Test get detail news
		/*
		 * NewsModel news = newsDAO.getDetailNews(2);
		 * System.out.println(news.getTitle());
		 */

		// Test get list news by searching keyword
		/*
		 * ArrayList<NewsModel> resultLists = newsDAO.searchNewsByKeyword("a");
		 * for(NewsModel newsModel : resultLists) { System.out.println(newsModel.getID()
		 * + " " + newsModel.getTitle()); } if(resultLists.size() == 0) {
		 * System.out.println("No result!"); }
		 */
	}
}
