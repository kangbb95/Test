package board.controller;

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

import board.model.service.BoardService;
import board.model.vo.Board;


/**
 * Servlet implementation class BoardUpdateServlet
 */
@WebServlet(name = "BoardUpdate", urlPatterns = { "/boardUpdate" })
public class BoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateServlet() {
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
			request.setAttribute("msg", "게시글 수정오류[enctype]");
			request.setAttribute("loc", "/");
			rd.forward(request, response);
			return;
		}
		//1)업로드 경로지정
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root + "upload/board";
		//2)업로드 파일 최대크기 지정
		int maxSize = 1024*1024*10;
		//3)request -> MultipartRequest로 변환
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize, "UTF-8", new DefaultFileRenamePolicy() );
		int boardNo = Integer.parseInt(mRequest.getParameter("boardNo"));
		String boardTitle = mRequest.getParameter("boardTitle");
		String filename = mRequest.getOriginalFileName("filename");
		String filepath = mRequest.getFilesystemName("filename");
		String boardContent = mRequest.getParameter("boardContent");
		//기존파일 이름 및 경로
		String oldFilename = mRequest.getParameter("oldfilename");
		String oldFilepath = mRequest.getParameter("oldFilepath");
		//기존 파일 삭제 확인용
		String status = mRequest.getParameter("status");
		if(status.equals("delte")) {	//기존 첨부파일을 삭제했을 경우
			File delFile = new File(saveDirectory+"/"+oldFilepath);
			delFile.delete();
		}else if(oldFilename != null){
			filename = oldFilename;
			filepath = oldFilepath;
		}
		//Notice n
		//= new Notice
		//(0,noticeNo,noticeTitle,null,noticeContent,null,filename,filepath)
		Board board = new Board();
		board.setBoardNo(boardNo);
		board.setBoardTitle(boardTitle);
		board.setBoardContent(boardContent);
		board.setFileName(filename);
		board.setFilePath(filepath);
		
		//3.비지니스로직
		int result = new BoardService().updateBoard(board);
		//4.결과처리
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result > 0) {
			request.setAttribute("msg", "변경 성공");
		}else {
			request.setAttribute("msg", "변경 실패");
		}
		request.setAttribute("loc", "/boardView?boardNo="+boardNo);
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
