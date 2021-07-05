package com.javaex.ex05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
	// Field
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "webdb";
	String pw = "webdb";

	// 간소화
	public void getConnection() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	public void close() {
		try {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}

	// Delete
	public int bookDelete(BookVo bookVo) {
		int count = -1;

		getConnection();

		try {
			String query = "";
			query += " delete from book ";
			query += " where book_id = ? ";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bookVo.getBookId());

			count = pstmt.executeUpdate();
			System.out.println(count + "건이 삭제되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return count;
	}

// Update
	public int bookUpdate(BookVo bookVo) {
		int count = -1;

		getConnection();

		try {
			String query = "";
			query += " update book ";
			query += " set title = ?, ";
			query += "     pubs = ?, ";
			query += "     pub_date = ?, ";
			query += "     author_id = ?, ";
			query += " where book_id = ? ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bookVo.getTitle());
			pstmt.setString(2, bookVo.getPubs());
			pstmt.setString(3, bookVo.getPubDate());
			pstmt.setInt(1, bookVo.getAuthorId());

			count = pstmt.executeUpdate();
			// 결과처리
			System.out.println(count + "건이 수정되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return count;
	}

// Insert
	public int bookInsert(BookVo bookVo) {
		int count = -1;

		getConnection();

		try {
			String query = "";
			query += " insert into book ";
			query += " values(seq_book_id.nextval, ?, ?, ?, ?) ";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bookVo.getTitle());
			pstmt.setString(2, bookVo.getPubs());
			pstmt.setString(3, bookVo.getPubDate());
			pstmt.setInt(4, bookVo.getAuthorId());

			count = pstmt.executeUpdate();

			// 결과처리
			System.out.println(count + "건이 등록되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return count;
	}
	
// SelectAll
	public List<BookVo> getBookList() {

		// DB에서 리스트 가져옴
		List<BookVo> arrayList = new ArrayList<BookVo>();

		getConnection();

		try {

			String query = "";
			query += " select book_id, ";
			query += "		 title, ";
			query += "       pubs, ";
			query += "       pub_date, ";
			query += "       author_name ";
			query += " from book bo, author au ";
			query += " where bo.author_id = au.author_id ";
			
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int bookId = rs.getInt("book_id");
				String Title = rs.getString("title");
				String Pubs = rs.getString("pubs");
				String pubDate = rs.getString("pub_date");
				String authorName = rs.getString("author_name");

				BookVo bookVo = new BookVo(bookId, Title, Pubs, pubDate, authorName);

				arrayList.add(bookVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return arrayList;
	}
	
	//Search
	public List<BookVo> bookSearch(String search) {

		// DB에서 리스트 가져옴
		List<BookVo> arrayList = new ArrayList<BookVo>();

		getConnection();

		try {

			String query = "";
			query += " select book_id, ";
			query += "		 title, ";
			query += "       pubs, ";
			query += "       pub_date, ";
			query += "       author_name ";
			query += " from (select book_id, "
					+ "				title, "
					+ "				pubs, "
					+ "				pub_date, "
					+ "				author_name "
					+ " from book bo, author au "
					+ " where bo.author_id = au.author_id) " ;
			query += " where book_id like '%" + search + "%'";
			query += " or title like '%" + search + "%'";
			query += " or pubs like '%" + search + "%'";
			query += " or pub_date like '%" + search + "%'";
			query += " or author_name like '%" + search + "%'";
			
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int bookId = rs.getInt("book_id");
				String Title = rs.getString("title");
				String Pubs = rs.getString("pubs");
				String pubDate = rs.getString("pub_date");
				String authorName = rs.getString("author_name");

				BookVo bookVo = new BookVo(bookId, Title, Pubs, pubDate, authorName);

				arrayList.add(bookVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return arrayList;
	}


}
// 총 5개, bookDelete, bookUpdate, bookInsert, bookSearch, bookSelectAll