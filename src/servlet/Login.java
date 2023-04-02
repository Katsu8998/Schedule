package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import model.CalendarBeans;
import model.CalendarLogic;
import model.User;
import model.UserLogic;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CalendarLogic beansLogic;
	private CalendarBeans beans;
	private CalendarBeans now;
	private UserDAO uDao;
	private User UN;
	private UserLogic userLogic;

	public Login() {
		super();
		this.beansLogic = new CalendarLogic();
		this.beans = new CalendarBeans();
		this.now = new CalendarBeans();
		this.uDao = new UserDAO();
		this.UN = new User();
		this.userLogic = new UserLogic();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String forwardPath = "";
		String action = request.getParameter("action");
		HttpSession session = request.getSession();


		/**
		 * actionに含まれている値により、遷移先を判断する
		 * nullとlogoutの場合、ログイン画面
		 * changeの場合、パスワード変更画面
		 * researchの場合、検索画面に遷移
		 */

			if (action== null) {
				forwardPath = "/WEB-INF/jsp/login.jsp";
			}else if(action.equals("logout")) {
				session.removeAttribute("user");
				forwardPath = "/WEB-INF/jsp/login.jsp";
			}else if(action.equals("change")) {
				forwardPath = "/WEB-INF/jsp/change.jsp";
			}else if(action.equals("register")) {
			forwardPath = "/WEB-INF/jsp/register.jsp";
			}else if (action.equals("research")) {
			forwardPath = "/WEB-INF/jsp/research.jsp";

			}

		RequestDispatcher d = request.getRequestDispatcher(forwardPath);
		d.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//sessionスコープインスタンスの取得
		HttpSession session = request.getSession();

		//遷移先のパスを入力するための変数
		String forwardPath = "";


		//ログイン時の日付けでカレンダーを取得
	//	CalendarLogic beansLogic = new CalendarLogic();
	//	CalendarBeans beans = new CalendarBeans();
		beans = beansLogic.execute2();

	//	CalendarBeans now = new CalendarBeans();
		now = beansLogic.execute(beans.getYear(), beans.getMonth());

		//入力されたIDとパスワードを取得
		int id = Integer.parseInt(request.getParameter("id"));
		String password = request.getParameter("password");

		//データベースからIDとパスワードを取得
//		UserDAO uDao = new UserDAO();
		List<User> user = uDao.findAll();

		/**
		 * DBから取得したデータと一致する場合、カレンダー画面に遷移
		 * 一致しない場合、ログイン失敗画面に遷移
		 */


	//	User UN = new User();
		boolean flg = false;
		flg = user.stream().anyMatch(u -> u.getId() == id && u.getPassword().equals(password));
//		UserLogic userLogic = new UserLogic();
		if( flg) {
			UN = userLogic.selectUser(user, id, password);
			session.setAttribute("user", UN);
			session.setAttribute("now", now);
			forwardPath = "/WEB-INF/jsp/calendar.jsp";
			}else {
				forwardPath = "/WEB-INF/jsp/loginFail.jsp";
			};


		RequestDispatcher d = request.getRequestDispatcher(forwardPath);
		d.forward(request, response);
	}

}
