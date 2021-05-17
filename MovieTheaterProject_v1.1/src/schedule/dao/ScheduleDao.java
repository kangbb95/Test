package schedule.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import schedule.model.vo.Schedule;

public class ScheduleDao {

	public ArrayList<Schedule> selectAllSchedule(Connection conn) {
		PreparedStatement pstmt= null;
		ResultSet rset = null;
		String query = "select * from schedule_tbl";
		ArrayList<Schedule> list = new ArrayList<Schedule>();
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Schedule s = new Schedule();
				s.setScheduleNo(rset.getInt("schedule_no"));
				s.setTheaterNo(rset.getInt("theater_no"));
				s.setStartDate(rset.getDate("start_date"));
				s.setEndDate(rset.getDate("end_date"));				
				s.setMovieNo(rset.getInt("movie_no"));
				list.add(s);
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

	public ArrayList<Schedule> selectMovie(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM SCHEDULE_TBL WHERE END_DATE < TO_CHAR(SYSDATE,'YYYY-MM-DD')";
		ArrayList<Schedule> sList = new ArrayList<Schedule>();
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Schedule s = new Schedule();
				s.setScheduleNo(rset.getInt("schedule_no"));
				s.setTheaterNo(rset.getInt("theater_no"));
				s.setStartDate(rset.getDate("start_date"));
				s.setEndDate(rset.getDate("end_date"));				
				s.setMovieNo(rset.getInt("movie_no"));
				sList.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		
		return sList;
	}
	
}
