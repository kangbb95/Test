package board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import board.model.vo.Board;
import common.JDBCTemplate;


public class BoardDao {

	public ArrayList<Board> selectBoardList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset= null;
		ArrayList<Board> list = new ArrayList<Board>();
		String query = "select * from(select rownum as rnum, n.* from(select * from board order by board_no desc)n) where rnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Board board = new Board();
				board.setBoardContent(rset.getString("board_content"));
				board.setBoardDate(rset.getString("board_date"));
				board.setBoardNo(rset.getInt("board_no"));
				board.setBoardTitle(rset.getString("board_title"));
				board.setBoardWriter(rset.getString("board_writer"));
				board.setFileName(rset.getString("filename"));
				board.setFilePath(rset.getString("filepath"));
				board.setRnum(rset.getInt("rnum"));
				list.add(board);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return list;
	}

	public int totalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset= null;
		String query= "select count(*) as cnt from board";
		int result = 0; 
		try {
			pstmt= conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int insertBoard(Connection conn, Board board) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into board values(board_seq.nextval,?,?,?,to_char(sysdate,'yyyy-mm-dd'),?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, board.getBoardTitle());
			pstmt.setString(2, board.getBoardWriter());
			pstmt.setString(3, board.getBoardContent());
			pstmt.setString(4, board.getFileName());
			pstmt.setString(5, board.getFilePath());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public Board selectOneNotice(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from board where board_no=?";
		Board board = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				board = new Board();
				board.setFileName(rset.getString("filename"));
				board.setFilePath(rset.getString("filepath"));
				board.setBoardContent(rset.getString("board_content"));
				board.setBoardDate(rset.getString("board_date"));
				board.setBoardNo(rset.getInt("board_no"));
				board.setBoardTitle(rset.getString("board_title"));
				board.setBoardWriter(rset.getString("board_writer"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return board;
	}

	public int updateBoard(Connection conn, Board board) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update board set board_title = ?, board_content=?, filename=?, filepath=? where board_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, board.getBoardTitle());
			pstmt.setString(2, board.getBoardContent());
			pstmt.setString(3, board.getFileName());
			pstmt.setString(4, board.getFilePath());
			pstmt.setInt(5, board.getBoardNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteBoard(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from board where board_no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

}
