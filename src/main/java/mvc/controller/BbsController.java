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

import mvc.domain.Bbs;
import mvc.service.BbsService;

/**
 * Servlet implementation class BbsController
 */
@WebServlet("/bbs")
public class BbsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BbsService service  = new BbsService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BbsController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int pageNumber = 1;
		if (request.getParameter("pageNumber") != null) {
			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		}

		ArrayList<Bbs> list = service.getList(pageNumber);
		boolean nextPage = service.nextPage(pageNumber + 1);

		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("list", list);
		request.setAttribute("nextPage", nextPage);

		RequestDispatcher dispatcher = request.getRequestDispatcher("bbs.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
