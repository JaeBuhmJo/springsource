package com.spring.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.domain.BoardDTO;

@Repository
public class BoardDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;

	@Autowired
	private DataSource ds;

	public void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close(Connection con, PreparedStatement pstmt) {
		try {
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void commit(Connection con) {
		try {
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void rollback(Connection con) {
		try {
			con.rollback();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean insert(BoardDTO dto) {
		boolean flag = false;
		try {
			con = ds.getConnection();
			String sql = "insert into spring_board(bno,title,content,writer) values (seq_board.nextval,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getWriter());
			if (pstmt.executeUpdate() > 0) {
				commit(con);
				flag = true;
			}
		} catch (Exception e) {
			rollback(con);
			e.printStackTrace();
		} finally {
			close(con, pstmt);
		}
		return flag;
	}

	public List<BoardDTO> getList() {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			con = ds.getConnection();
			String sql = "select * from spring_board order by bno";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setBno(rs.getInt("bno"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setWriter(rs.getString("WRITER"));
				dto.setRegdate(rs.getDate("regdate"));
				dto.setUpdatedate(rs.getDate("updatedate"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		return list;
	}

	public boolean delete(int bno) {
		boolean flag = false;
		try {
			con = ds.getConnection();
			String sql = "delete spring_board where bno = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			int result = pstmt.executeUpdate();
			if (result > 0) {
				commit(con);
				flag = true;
			}
		} catch (Exception e) {
			rollback(con);
			e.printStackTrace();
		} finally {
			close(con, pstmt);
		}
		return flag;
	}

	public boolean update(BoardDTO dto) {
		boolean flag = false;
		try {
			con = ds.getConnection();
			String sql = "update spring_board set title = ? , content = ?, updatedate = sysdate where bno =? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getBno());
			int result = pstmt.executeUpdate();
			if (result > 0) {
				commit(con);
				flag = true;
			}
		} catch (Exception e) {
			rollback(con);
			e.printStackTrace();
		} finally {
			close(con, pstmt);
		}
		return flag;
	}

	public BoardDTO getRow(int bno) {
		BoardDTO dto = null;
		try {
			con = ds.getConnection();
			String sql = "select * from spring_board where bno = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto = new BoardDTO();
				dto.setBno(rs.getInt("bno"));
				dto.setTitle(rs.getString("title"));
				dto.setWriter(rs.getString("writer"));
				dto.setContent(rs.getString("content"));
				dto.setRegdate(rs.getDate("regdate"));
				dto.setUpdatedate(rs.getDate("updatedate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		return dto;
	}
}
