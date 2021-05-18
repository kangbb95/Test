package board.controller;

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

import board.model.service.BoardService;
import board.model.vo.Board;

/**
 * Servlet implementation class BoardWriteServlet
 */
@WebServlet(name = "BoardWrite", urlPatterns = { "/boardWrite" })
public class BoardWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		//2.값추출
		if(!ServletFileUpload.isMultipartContent(request)) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "게시글작성오류[enctype]");
			request.setAttribute("loc", "/");
			rd.forward(request, response);
			return;
		}
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root + "upload/board";
		System.out.println("파일저장경로 :" +saveDirectory);
		int maxSize=10*1024*1024;
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize,"UTF-8", new DefaultFileRenamePolicy());
		Board board = new Board();
		board.setBoardTitle(mRequest.getParameter("boardTitle"));
		board.setBoardWriter(mRequest.getParameter("boardWriter"));
		board.setBoardContent(mRequest.getParameter("boardContent"));
		board.setFileName(mRequest.getOriginalFileName("filename"));
		board.setFilePath(mRequest.getFilesystemName("filename"));
		//3.비지니스로직
		int result = new BoardService().insertBoard(board);
		//4.결과처리
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result > 0) {
			request.setAttribute("msg", "공지사항 등록 성공");
		}else {
			request.setAttribute("msg", "공지사항 등록 실패");
		}
		request.setAttribute("loc", "/boardList?reqPage=1");
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
