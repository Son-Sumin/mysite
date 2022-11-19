package com.bitacademy.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitacademy.mysite.vo.BoardVo;

@Repository
public class BoardRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public Boolean insert(BoardVo vo) {
		int count = sqlSession.insert("board.insert", vo);
		return count == 1;
	}
	
	public List<BoardVo> findAll() {
		return sqlSession.selectList("board.findAll");
	}
	
	public boolean update(BoardVo vo) {
		int count = sqlSession.insert("board.update", vo);
		return count == 1;
	}
	
	public BoardVo findByNo(Long no) {
		BoardVo result = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			String sql = " select title, contents,hit, user_no from board where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String title = rs.getString(1);
				String contents = rs.getString(2);
				Long hit = rs.getLong(3);
				Long userNo = rs.getLong(4);
				
				result = new BoardVo();
				result.setTitle(title);
				result.setContents(contents);
				result.setHit(hit);
				result.setUserNo(userNo);
			}
			
		} catch (SQLException e) {
			System.out.println("Error:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public Boolean deleteByNo(Long no) {
		boolean result = false;
		
		Connection conn = null;  
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = " delete from board where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			
			int count = pstmt.executeUpdate();

			result = count == 1;
			
		} catch (SQLException e) {
			System.out.println("Error: " + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
		
	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");

			String url = "jdbc:mysql://127.0.0.1:3306/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패: " + e);
		}
		return conn;
	}
}
