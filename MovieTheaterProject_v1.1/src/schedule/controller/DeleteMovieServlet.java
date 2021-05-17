package schedule.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.model.vo.Movie;
import movie.service.MovieService;
import schedule.model.vo.Schedule;
import schedule.service.ScheduleService;

/**
 * Servlet implementation class DeleteMovieServlet
 */
@WebServlet(name = "DeleteMovie", urlPatterns = { "/deleteMovie" })
public class DeleteMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMovieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		ArrayList<Schedule> sList = new ScheduleService().selectMovie();
		   for (int i = 0; i < sList.size(); i++) {
			    System.out.println(sList.get(i).getMovieNo());
			    System.out.println(sList.get(i).getScheduleNo());
			    System.out.println(sList.get(i).getTheaterNo());
			    System.out.println(sList.get(i).getEndDate());
			    System.out.println(sList.get(i).getStartDate());
			   }
		int result = new MovieService().deleteMovie(sList);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result > 0) {
			request.setAttribute("msg", ""+result+"개의 영화 삭제완료");
		}else {
			request.setAttribute("msg", "삭제실패");			
		}
		request.setAttribute("loc", "/deleteMovieFrm");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
