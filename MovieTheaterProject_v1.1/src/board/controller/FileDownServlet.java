package board.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Board;

/**
 * Servlet implementation class FileDownServlet
 */
@WebServlet(name = "FileDown", urlPatterns = { "/fileDown" })
public class FileDownServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileDownServlet() {
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
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		//3.비지니스로직
		Board board = new BoardService().selectOneBoard(boardNo);
		//4.결과처리
		String root = getServletContext().getRealPath("/");
		String file = root + "upload/board/"+board.getFilePath();
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(fis);
		ServletOutputStream sos = response.getOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(sos);
		String resFilename = "";
		boolean bool = request.getHeader("user-agent").indexOf("MSIE") != -1 || request.getHeader("user-agent").indexOf("Trident") != -1;
		if(bool) {
			resFilename = URLEncoder.encode(board.getFileName(),"UTF-8");
			resFilename = resFilename.replace("\\\\", "%20");
		}else {
			resFilename = new String(board.getFileName().getBytes("UTF-8"),"ISO-8859-1");
		}
		response.setContentType("application/octet-strea,");
		response.setHeader("Content-Disposition", "attachment;filename="+resFilename);
		while(true) {
			int read = bis.read();
			if(read != -1) {
				bos.write(read);
			}else {
				break;
			}
		}
		bos.close();
		bis.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
