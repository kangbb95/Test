package movie.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import movie.model.vo.Movie;
import movie.model.vo.MovieData;
import movie.model.dao.MovieListDao;

public class MovieListService {

	public int totalCount() {
		Connection conn = JDBCTemplate.getConnection();
		int totalCount = new MovieListDao().totalCount(conn);
		JDBCTemplate.close(conn);
		return totalCount;
	}

	public ArrayList<Movie> selectAllMovie() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Movie> list = new MovieListDao().selectAllMovie(conn);
		JDBCTemplate.close(conn);
		return list;
	}

	public MovieData selectMovieInfo(int movieNo) {
		Connection conn = JDBCTemplate.getConnection();
		MovieListDao dao = new MovieListDao();
		MovieData md = dao.selectMovieInfo(conn, movieNo);
		JDBCTemplate.close(conn);
		return md;
	}

	public int updateMovieInfo(Movie mv) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new MovieListDao().updateMovieInfo(conn,mv);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

}
