package mvc.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.domain.Bbs;
import mvc.service.BbsService;

/**
 * Servlet implementation class BbsController
 */
@WebServlet("/write")
public class WriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BbsService service = new BbsService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WriteController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("write.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String bbsTitle = request.getParameter("bbsTitle");
		String bbsContent = request.getParameter("bbsContent");
		
		//세션값 가져옴
		HttpSession session = request.getSession();
		//문자라 String으로 받고 캐스팅함.
		String userID = (String) session.getAttribute("userID");
		
		Bbs bbs = new Bbs();
		bbs.setBbsTitle(bbsTitle);
		bbs.setBbsContent(bbsContent);
		bbs.setUserID(userID);

		/* <> 를 문자로 바꿔 이미지 태그를 못하게 막는다. */
		String content = bbs.getBbsContent().replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		bbs.setBbsContent(content);

		int res = service.write(bbs);
		

		switch (res) {
		case 1: response.sendRedirect("bbs");break;
		default:response.sendRedirect("write"); break;
		}
	}

}
