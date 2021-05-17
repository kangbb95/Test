package movie.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.model.vo.Movie;
import movie.model.vo.MovieData;
import movie.service.MovieListService;


/**
 * Servlet implementation class MovieInfoUpdateFrmServlet
 */
@WebServlet(name = "MovieInfoUpdateFrm", urlPatterns = { "/movieInfoUpdateFrm" })
public class MovieInfoUpdateFrmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieInfoUpdateFrmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int movieNo = Integer.parseInt(request.getParameter("movieNo"));
		MovieData md = new MovieListService().selectMovieInfo(movieNo);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/movieList/movieInfoUpdateFrm.jsp");
		request.setAttribute("md", md);
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
