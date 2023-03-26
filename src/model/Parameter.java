package model;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

public class Parameter {

	/**
	 * 新規登録用
	 * スケジュールインスタンスのフィールド設定
	 * @param request
	 * @param user
	 * @return
	 */
	public ScheduleBeans execute(HttpServletRequest request, User user) {
		int id = user.getId();

		//String からSQLのDate型に変換
		String day = request.getParameter("day");
		Date sqlDay= Date.valueOf(day);


		String start = request.getParameter("start").toString();

		String finish = request.getParameter("finish").toString();

		String title = request.getParameter("title");
		String memo = request.getParameter("detail");

		String u_id = String.valueOf(id);
		String s_id = u_id + start + finish;


		ScheduleBeans SB = new ScheduleBeans(id, sqlDay, start, finish, title, memo);
		return SB;

	}

	/**
	 *
	 * 編集用
	 * スケジュールインスタンスのフィールド設定
	 * @param request
	 * @param s
	 * @return
	 */

	public ScheduleBeans execute(HttpServletRequest request, ScheduleBeans s) {
		String day = request.getParameter("day");
		Date sqlDay= Date.valueOf(day);


		String start = request.getParameter("start").toString();

		String finish = request.getParameter("finish").toString();

		String title = request.getParameter("title");
		String memo = request.getParameter("detail");

		s.setDate(sqlDay);
		s.setStart(start);
		s.setEnd(finish);
		s.setTitle(title);
		s.setDetail(memo);

		return s;

	}

	/**
	 *ユーザーインスタンスのフィールド設定
	 * @param request
	 * @return
	 */

	public User register(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		User u = new User (password, name, id);
		return u;

	}

}
