package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Parameter;
import model.User;
import model.UserLogic;

/**
 * Servlet implementation class User
 */
@WebServlet("/User_R")
public class User_R extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Parameter parameter;
	private User user;
	private UserLogic logic;

	public User_R() {
		super();
		this.parameter = new Parameter();
		this.user = new User();
		this.logic = new UserLogic();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//sessionスコープインスタンスの取得
		HttpSession session = request.getSession();
	//	Parameter parameter = new Parameter();
	//	User user = parameter.register(request);
		user = parameter.register(request);
	//	UserLogic logic = new UserLogic();
		int result = logic.register(user);

		String forwardPath = "/WEB-INF/jsp/register_fail.jsp";

		switch (result) {
		case 0:
			session.setAttribute("user", user);
			request.setAttribute("RegisterOK", "登録完了しました");
			forwardPath = "/WEB-INF/jsp/register_success.jsp";
			break;

		case 1:
			//エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg1", "その他のIDを入力してください");
			break;

		case 2:
			//エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg1", "不明のエラーが発生しました");
			break;

		}

		RequestDispatcher d = request.getRequestDispatcher(forwardPath);
		d.forward(request, response);



	}

}
