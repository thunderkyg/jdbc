package com.javaex.ex02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {

	//Method - ordinary
	//AuthorSelectApp
	public List<AuthorVo> getAuthorList()	{
		//DB값을 가져와서 ArrayList로 전달
		
		//리시트 생성
		List<AuthorVo> authorList = new ArrayList<AuthorVo>();
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

		    // 3. SQL문 준비 / 바인딩 / 실행
			String query = "";						//쿼리문 문자열로 만들기 --> ? 주의
			query += " select  author_id, ";
			query += " 		   author_name, ";
			query += " 		   author_desc ";
			query += " from author ";
			query += " order by author_id asc ";
			
		    pstmt = conn.prepareStatement(query);
		    rs = pstmt.executeQuery();
		    
		    
		    // 4.결과처리
		    while(rs.next()) {
		    	int authorId = rs.getInt("author_id");
		    	String authorName = rs.getString("author_name");
		    	String authorDesc = rs.getString("author_desc");
		    	
		    	AuthorVo authorVo = new AuthorVo(authorId, authorName, authorDesc);
		    	authorList.add(authorVo);
		    	
		    }

		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
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
		return authorList;
	}
	
	//AuthorInsertApp
	public int authorInsert(String authorName, String authorDesc) {
		
		int iCount = 0;
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

		    // 3. SQL문 준비 / 바인딩 / 실행
			String query = "";						//쿼리문 문자열로 만들기 --> ? 주의
			query += " insert into author ";
			query += " values (seq_author_id.nextval, ?, ?)";
			
			pstmt = conn.prepareStatement(query);   //쿼리문으로 만들기
			pstmt.setString(1, authorName);			//?(물음표)중 1번째 --> 순서중
		    pstmt.setString(2, authorDesc);			//?(물음표)중 2번째 --> 순서중
		    
		    iCount = pstmt.executeUpdate(); 		//쿼리문 실행 --> 리텀값으로 성공여부 판단
		    
		    //insert into author values (seq_author_id.nextval, '김영하', '알쓸신잡');
		    
		    // 4.결과처리
		    System.out.println(iCount + "건 등록");
		    return iCount; //성공갯수 리턴;
		    
		    

		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
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
		return iCount;
		
	}
	
	public int authorUpdate(int authorId, String authorDesc, String authorName) {
		int uCount = 0;
		// 0. import java.sql.*;
				Connection conn = null;
				PreparedStatement pstmt = null;

				try {
				    // 1. JDBC 드라이버 (Oracle) 로딩
					Class.forName("oracle.jdbc.driver.OracleDriver");
					
				    // 2. Connection 얻어오기
					String url = "jdbc:oracle:thin:@localhost:1521:xe";
					conn = DriverManager.getConnection(url, "webdb", "webdb");

				    // 3. SQL문 준비 / 바인딩 / 실행
					String query = "";						//쿼리문 문자열로 만들기 --> ? 주의
					query += " 	update author ";
					query += " 	set author_name = ?, ";
					query += " 	    author_desc = ? ";
					query += " 	where author_id = ?";
					
					
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, authorName);
					pstmt.setString(2, authorDesc);
					pstmt.setInt(3, authorId);
				    
					uCount = pstmt.executeUpdate();
				    // 4.결과처리
				    System.out.println(uCount + "건이 수정되었습니다.");

				} catch (ClassNotFoundException e) {
				    System.out.println("error: 드라이버 로딩 실패 - " + e);
				} catch (SQLException e) {
				    System.out.println("error:" + e);
				} finally {
				   
				    // 5. 자원정리
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
		return uCount;
		
	}
	
	public int authorDelete(int authorId) {
		int dCount = 0;
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

		    // 3. SQL문 준비 / 바인딩 / 실행
			String query = "";						//쿼리문 문자열로 만들기 --> ? 주의
			query += " 	delete from author ";
			query += " 	where author_id = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, authorId);
		    
			dCount = pstmt.executeUpdate();
		    // 4.결과처리
		    System.out.println(dCount + "건이 삭제되었습니다.");

		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
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
		return dCount;
	}
	
	public void getAuthorOne(int num) {
		
	}
	
}
