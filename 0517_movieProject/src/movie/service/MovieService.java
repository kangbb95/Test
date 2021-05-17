package movie.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import movie.model.dao.MovieDao;
import movie.model.vo.Movie;
import schedule.model.vo.Schedule;

public class MovieService {

	public int insertMovie(Movie m) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new MovieDao().insertMovie(conn,m);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public ArrayList<Movie> selectAllMovieList() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Movie> list = new MovieDao().selectAllMovieList(conn);
		JDBCTemplate.close(conn);
		return list;
	}

	public int selectOneMovie(int movieNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new MovieDao().selectOneMember(conn,movieNo);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int deleteMovie(ArrayList<Schedule> sList) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new MovieDao().deleteMovie(conn,sList);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}



}
