package com.example.news;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class NewsDAO {
	final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	final String JDBC_URL = "jdbc:mysql://localhost:3306/jwbookdb?useUnicode=true&characterEncoding=utf-8";
	
	//DB연결을 가져온 메서드
	public Connection open() {
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, "root", "1234");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public List<News>getAll() throws Exception{
		Connection conn = open();
		List<News> newsList = new ArrayList<>();
		
		String sql = "select aid, title, date as cdate from news";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		try(conn; pstmt; rs) {
			while(rs.next()) {
				News n = new News();
				n.setAid(rs.getInt("aid"));
				n.setTitle(rs.getString("title"));
				n.setDate(rs.getString("cdate"));
				
				newsList.add(n);
			}
			return newsList;
		}
	}
	
	public News getNews(int aid) throws SQLException{
		Connection conn = open();
		
		News n = new News();
		String sql = "select aid, title, img, date as cdate, content from news where aid=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, aid);
		ResultSet rs = pstmt.executeQuery();
		
		rs.next();
		
		try(conn; pstmt; rs){
			n.setAid(rs.getInt("aid"));
			n.setTitle(rs.getString("title"));
			n.setImg(rs.getString("img"));
			n.setDate(rs.getString("cdate"));
			n.setContent(rs.getString("content"));
			pstmt.executeQuery();
			return n;
		}
	}
	
	public void addNews(News n) throws Exception{
		Connection conn = open();
		
		String sql = "insert into news(title, img, date, content) values(?,?,CURRENT_TIMESTAMP(), ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		try(conn; pstmt){
			pstmt.setString(1, n.getTitle());
			pstmt.setString(2, n.getImg());
			pstmt.setString(3, n.getContent());
			pstmt.executeUpdate();
		}
	}
	
	public void delNews(int aid) throws SQLException{
		Connection conn = open();
		String sql = "delete from news where aid=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		try(conn; pstmt){
			pstmt.setInt(1, aid);
			
			if(pstmt.executeUpdate() == 0) {
				throw new SQLException("DB에러");
			}
		}
	}
}