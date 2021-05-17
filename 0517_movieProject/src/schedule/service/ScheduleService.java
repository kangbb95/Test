package schedule.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import schedule.dao.ScheduleDao;
import schedule.model.vo.Schedule;

public class ScheduleService {

	public ArrayList<Schedule> selectAllSchedule() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Schedule> list = new ScheduleDao().selectAllSchedule(conn);
		JDBCTemplate.close(conn);
		return list;
	}

	public ArrayList<Schedule> selectMovie() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Schedule> sList = new ScheduleDao().selectMovie(conn);
		JDBCTemplate.close(conn);
		return sList;
	}

}
