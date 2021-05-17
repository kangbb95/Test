package movie.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import movie.model.vo.Movie;
import movie.service.MovieService;


/**
 * Servlet implementation class InsertMovieServlet
 */
@WebServlet(name = "InsertMovie", urlPatterns = { "/insertMovie" })
public class InsertMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertMovieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		if(!ServletFileUpload.isMultipartContent(request)) {		//폼태그의 enctype이 맞냐 아니냐	정상이 아니라면
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");	//메시지 띄워주는 결과창 지정
			request.setAttribute("msg", "영화 작성오류[enctype]");	//에러메시지 띄우고
			request.setAttribute("loc", "/");	//페이지이동
			rd.forward(request, response);
			return;		//false면 리턴!
		}
		String root = getServletContext().getRealPath("/");		//WebContent폴더 경로 가져오는 코드
		String saveDirectory = root + "poster/movieListPoster";			//파일 저장 경로 지정
		//2. 업로드 파일의 최대크기 지정(10mb 이상은 잘 안함 웹은) 대ㅑ용량은 드라이브로 전환한다. --> 자동전환 
		int maxSize = 10 * 1024 * 1024;	//10mb 이다 이게
		//3. request -> MultipartRequest객체로 바꿔서 변환	파일이 업로드 되는 코드 이 multipart객체가 생성되는 순간이 파일이 업로드 되는 순간
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize, "utf-8", new DefaultFileRenamePolicy());
															//request객체 , 파일 저장경로, 최대크기 ,인코딩타입, 파일명 중복 처리 객체  이렇게 매개변수 5개
		Movie m = new Movie();
		m.setMovieTitle(mRequest.getParameter("movieTitle"));
		m.setMovieGrade(mRequest.getParameter("movieGrade"));
		m.setMovieRuntime(mRequest.getParameter("movieRuntime"));
		m.setMovieDate(mRequest.getParameter("movieDate"));
		m.setMovieGenNo(Integer.parseInt(mRequest.getParameter("movieGenNo")));
		m.setMoviePlot(mRequest.getParameter("moviePlot"));
		m.setMovieDirector(mRequest.getParameter("movieDirector"));
		m.setMovieCast(mRequest.getParameter("movieCast"));
		m.setMovieImg(mRequest.getFilesystemName("movieImg"));	//path로 저장해야하기 때문에 db컬럼명이 movie_img여도 path로 저장하는느낌
		
		//getOriginalFileName,getFilesystemName	매개변수는 input type에서의 name값이 들어가는 것

		//ㅂㅣ지느스로직
		int result = new MovieService().insertMovie(m);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result > 0) {
			request.setAttribute("msg", "영화등록성공");
		}else {
			request.setAttribute("msg", "영화등록실패");			
		}
		request.setAttribute("loc", "/");
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
