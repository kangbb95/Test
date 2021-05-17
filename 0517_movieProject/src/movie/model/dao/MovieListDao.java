package movie.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import movie.model.vo.Genre;
import movie.model.vo.Movie;
import movie.model.vo.MovieData;


public class MovieListDao {

	public int totalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalCount = 0;
		String query = "select count(*) cnt from movie_tbl";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalCount = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return totalCount;
	}

	public ArrayList<Movie> selectAllMovie(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Movie> list = new ArrayList<Movie>();
		String query = "select * from movie_tbl";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Movie mv = new Movie();
				mv.setMovieNo(rset.getInt("movie_no"));
				mv.setMovieTitle(rset.getString("movie_title"));
				mv.setMovieGrade(rset.getString("movie_grade"));
				mv.setMovieRuntime(rset.getString("movie_runtime"));
				mv.setMovieDate(rset.getString("movie_date"));
				mv.setMovieGenNo(rset.getInt("movie_gen_no"));
				mv.setMoviePlot(rset.getString("movie_plot"));
				mv.setMovieDirector(rset.getString("movie_director"));
				mv.setMovieCast(rset.getString("movie_cast"));
				mv.setMovieImg(rset.getString("movie_img"));
				list.add(mv);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public MovieData selectMovieInfo(Connection conn, int movieNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select movie_cast,movie_date,movie_director,genre_cate,movie_grade,movie_img,movie_no,movie_plot,movie_runtime,movie_title from movie_tbl join genre ON (movie_gen_no = genre_no) where movie_no=?";
		Movie mv = null;
		Genre g = null;
		MovieData md = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, movieNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				mv = new Movie();
				g = new Genre();
				mv.setMovieCast(rset.getString("movie_cast"));
				mv.setMovieDate(rset.getString("movie_date"));
				mv.setMovieDirector(rset.getString("movie_director"));
				g.setGenreCate(rset.getString("genre_cate"));
				mv.setMovieGrade(rset.getString("movie_grade"));
				mv.setMovieImg(rset.getString("movie_img"));
				mv.setMovieNo(rset.getInt("movie_no"));
				mv.setMoviePlot(rset.getString("movie_plot"));
				mv.setMovieRuntime(rset.getString("movie_runtime"));
				mv.setMovieTitle(rset.getString("movie_title"));
				md = new MovieData(mv,g);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return md;
	}

	public int updateMovieInfo(Connection conn, Movie mv) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update movie_tbl set movie_title=?,movie_grade=?,movie_runtime=?,movie_date=?,movie_plot=?,movie_director=?,movie_cast=?,movie_img=? where movie_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, mv.getMovieTitle());
			pstmt.setString(2, mv.getMovieGrade());
			pstmt.setString(3, mv.getMovieRuntime());
			pstmt.setString(4, mv.getMovieDate());
			pstmt.setString(5, mv.getMoviePlot());
			pstmt.setString(6, mv.getMovieDirector());
			pstmt.setString(7, mv.getMovieCast());
			pstmt.setString(8, mv.getMovieImg());
			pstmt.setInt(9, mv.getMovieNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

}
