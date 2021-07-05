package com.javaex.ex05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableSequenceDao {
	
	//Field
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String driver = "oracle.jdbc.driver.OracleDriver";
	String id = "webdb";
	String pw = "webdb";
	
	public void getConnection(){
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		}
	}
	
	public void close() {
		try {
	        if (rs != null) {
	            rs.close();
	        }                
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
	   
	//Create Table
	public void createBook() {
		
		try {
			String query = "";
			query += "create table book( ";
			query += "	book_id number(5), ";
			query += "	title varchar2(50) not null, ";
			query += "	pubs varchar2(20), ";
			query += "	pub_date date, ";
			query += "	author_id number(10) ";
			query += "	primary key(book_id), ";
			query += "	constraint book_fk foreign key (author_id) ";
			query += "	references author(author_id) ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//Create Author
	public void createAuthor() {
		
		try {
			String query = "";
			query += "create table author( ";
			query += "	author_id number(5), ";
			query += "	author_name varchar2(30) not null, ";
			query += "	author_desc varchar2(50), ";
			query += "	primary key (author_id)";
			
			pstmt = conn.prepareStatement(query);
			pstmt.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//Delete Table
	public void dropBook() {
		
		try {
			String query ="";
			query += " drop table book; ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Delete Author
	public void dropAuthor() {
		try {
			String query ="";
			query += " drop table author; ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	//Create Sequence Book
	public void createSequence() {
		
	}
	
	//Create Sequence Author
	
	//Drop Sequence Book
	
	//Drop Sequence Author
	
	
	
	
	
	
	
	
	
	
	
	

}

//만들어야하는거 Create Table(author, book), Drop Table(author,book),
//Create Sequence(author,book), Drop Sequence (author, book).
//총 8개