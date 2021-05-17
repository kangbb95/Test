package movie.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import movie.model.vo.Movie;
import schedule.model.vo.Schedule;

public class MovieDao {

	public int insertMovie(Connection conn, Movie m) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into movie_tbl values(movie_seq.nextval,?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMovieTitle());
			pstmt.setString(2, m.getMovieGrade());
			pstmt.setString(3, m.getMovieRuntime());
			pstmt.setString(4, m.getMovieDate());
			pstmt.setInt(5, m.getMovieGenNo());
			pstmt.setString(6, m.getMoviePlot());
			pstmt.setString(7, m.getMovieDirector());
			pstmt.setString(8, m.getMovieCast());
			pstmt.setString(9, m.getMovieImg());
		    result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Movie> selectAllMovieList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from movie_tbl";
		ArrayList<Movie> list = new ArrayList<Movie>();
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Movie m = new Movie();
				m.setMovieCast(rset.getString("movie_cast"));
				m.setMovieDate(rset.getString("movie_date"));
				m.setMovieDirector(rset.getString("movie_director"));
				m.setMovieGenNo(rset.getInt("movie_gen_no"));
				m.setMovieGrade(rset.getString("movie_grade"));
				m.setMovieImg(rset.getString("movie_img"));
				m.setMovieNo(rset.getInt("movie_no"));
				m.setMoviePlot(rset.getString("movie_plot"));
				m.setMovieRuntime(rset.getString("movie_runtime"));
				m.setMovieTitle(rset.getString("movie_title"));
				list.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);;
		}
		return list;
	}

	public int selectOneMember(Connection conn, int movieNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from movie_tbl where movie_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, movieNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int deleteMovie(Connection conn, ArrayList<Schedule> sList) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM movie_tbl m\r\n" + 
				"WHERE EXISTS ( \r\n" + 
				"SELECT * FROM schedule_tbl s WHERE s.END_DATE < TO_CHAR(SYSDATE,'YYYY-MM-DD')\r\n" + 
				"and s.movie_no = m.movie_no\r\n" + 
				")";
		try {
			pstmt = conn.prepareStatement(query);
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
