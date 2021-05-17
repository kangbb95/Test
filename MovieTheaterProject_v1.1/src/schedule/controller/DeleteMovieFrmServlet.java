package schedule.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import schedule.model.vo.Schedule;
import schedule.service.ScheduleService;

/**
 * Servlet implementation class DeleteMovieFrmServlet
 */
@WebServlet(name = "DeleteMovieFrm", urlPatterns = { "/deleteMovieFrm" })
public class DeleteMovieFrmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMovieFrmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		ArrayList<Schedule> list = new ScheduleService().selectAllSchedule();
		if(list.isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "삭제할 영화가 업스무니다");
			request.setAttribute("loc", "/");
			rd.forward(request, response);
		}else {	
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/admin/deleteMovieFrm.jsp");
			request.setAttribute("list", list);
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
