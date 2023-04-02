package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import model.LoginCheck;
import model.User;

/**
 * Servlet implementation class ChangePassword
 */
@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private User user;
	private LoginCheck loginCheck;
	private UserDAO dao;

	public ChangePassword() {
		super();
		this.loginCheck = new LoginCheck();
		this.user = new User();

		this.dao = new UserDAO();
	}

//パスワード更新時に稼働
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String forwardPath = "/WEB-INF/jsp/changePass.jsp";
		String pass = request.getParameter("password");

		String new_pass = request.getParameter("new_pass");

		HttpSession session = request.getSession();
	//	User
		user = (User) session.getAttribute("user");
	//	LoginCheck loginCheck = new LoginCheck();
	//	UserDAO dao = new UserDAO();

		int result = loginCheck.execute(user, new_pass, pass);

		switch (result) {
		case 0:
			dao.changePass(user, new_pass);
			session.setAttribute("user", user);
			request.setAttribute("changePassMsg", "パスワード変更が完了しました");
			//フォワード先を成功画面に変更
			forwardPath = "/WEB-INF/jsp/changePassResult.jsp";
			break;

		case 1:
			//エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg1", "入力されたパスワードが照合できるデータではありません");
			break;

		case 2:
			//入力された現在のパスワードが不正
			//エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg1", "入力されたパスワードが正しくありません");
			break;

		case 3:
			//パスワード更新エラー

			//エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg2", "新しいパスワードは設定できません");
			break;

		default:
			//エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg1", "不明のエラーが発生しました");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);

	}
}
