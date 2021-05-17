package movie.controller;

import java.io.File;
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

import movie.model.vo.Genre;
import movie.model.vo.Movie;
import movie.model.vo.MovieData;
import movie.service.MovieListService;


/**
 * Servlet implementation class MovieInfoUpdateServlet
 */
@WebServlet(name = "MovieInfoUpdate", urlPatterns = { "/movieInfoUpdate" })
public class MovieInfoUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieInfoUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//2.값추출
		if(!ServletFileUpload.isMultipartContent(request)) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "수정 오류");
			request.setAttribute("loc", "/");
			rd.forward(request, response);
			return;
		}
		//1)업로드 경로 지정
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root+"poster/movieListPoster";
		//2)업로드 파일 최대크기 지정
		int maxSize = 1024*1024*10;
		//3)request -> MultipartRequest로 변환
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		int movieNo = Integer.parseInt(mRequest.getParameter("movieNo"));
		String movieTitle = mRequest.getParameter("movieTitle");
		String filename = mRequest.getOriginalFileName("movieImg");
		String filepath = mRequest.getFilesystemName("movieImg");
		String moviePlot = mRequest.getParameter("moviePlot");
		String movieDirector = mRequest.getParameter("movieDirector");
		String movieCast = mRequest.getParameter("movieCast");
		String movieDate = mRequest.getParameter("movieDate");
		String movieGrade = mRequest.getParameter("movieGrade");
		String movieRuntime = mRequest.getParameter("movieRuntime");
		//기존파일 이름 경로
		String oldFilename = mRequest.getParameter("oldFilename");
		String oldFilepath = mRequest.getParameter("oldFilename");
		//기존파일 삭제 확인용
		String status = mRequest.getParameter("status");
		if(status.equals("delete")) {//기존 첨부파일을 삭제했을 때
			File delFile = new File(saveDirectory+"/"+oldFilepath);
			delFile.delete();
		}else if(oldFilename != null) {
			filename = oldFilename;
			filepath = oldFilepath;
		}
		//Notice n = new Notice(0, noticeNo, noticeTitle, null, noticeContent, null, filename, filepath); 
		Movie mv = new Movie();
		mv.setMovieNo(movieNo);
		mv.setMovieTitle(movieTitle);
		mv.setMoviePlot(moviePlot);
		mv.setMovieDirector(movieDirector);
		mv.setMovieCast(movieCast);
		mv.setMovieDate(movieDate);
		mv.setMovieGrade(movieGrade);
		mv.setMovieRuntime(movieRuntime);
		mv.setMovieImg(filename);
		mv.setMovieImg(filepath);
		//3.비지니스로직
		int result = new MovieListService().updateMovieInfo(mv);
		//4.결과처리
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("msg", "수정 성공");
		}else {
			request.setAttribute("msg", "수정 실패");
		}
		request.setAttribute("loc", "/movieInfo?movieNo="+movieNo);
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
