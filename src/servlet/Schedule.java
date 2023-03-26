package servlet;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ScheduleDAO;
import model.Parameter;
import model.ScheduleBeans;
import model.ScheduleLogic;
import model.User;

/**
 * Servlet implementation class Schedule
 */
@WebServlet("/Schedule")
public class Schedule extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String forwardPath = "";
		String schedule = request.getParameter("schedule");
		String day = request.getParameter("day");
		String month = request.getParameter("month");
		String year = request.getParameter("year");
		String s_id = request.getParameter("s_id");

		ServletContext application = this.getServletContext();
		ScheduleDAO dao = new ScheduleDAO();
		ScheduleLogic SL = new ScheduleLogic();
		HttpSession session = request.getSession();

		/**
		 * scheduleに格納されている値により、遷移先が変更される
		 * viewの場合、スケジュール閲覧画面
		 * editの場合、編集画面
		 * deleteの場合、削除画面
		 * inputの場合、登録画面
		 *
		 */

		switch (schedule) {
		case "view":
			List<ScheduleBeans> SB = dao.findAll();
			List<ScheduleBeans> SB_T = SL.dayCheck(SB, day, month, year);
			Collections.sort(SB_T, new MySBComparator());
			application.setAttribute("SB", SB_T);

			forwardPath = "/WEB-INF/jsp/view.jsp";
			break;
		case "edit":
			//編集対象のインスタンスを取得し、セッションスコープに保存
			ScheduleBeans s = SL.selectS(s_id);

			session.setAttribute("SB_e", s);
			forwardPath = "/WEB-INF/jsp/edit.jsp";
			break;

		case "delete":
			//削除対象のインスタンスを取得し、セッションスコープに保存

			ScheduleBeans SD = SL.selectS(s_id);

			session.setAttribute("SB_d", SD);
			forwardPath = "/WEB-INF/jsp/deleteCheck.jsp";
			break;

		case "input":
			forwardPath = "/WEB-INF/jsp/input.jsp";
		}

		RequestDispatcher d = request.getRequestDispatcher(forwardPath);
		d.forward(request, response);

	}

	//ScheduleBeansリストを昇順
	public class MySBComparator implements Comparator<ScheduleBeans> {
		@Override
		public int compare(ScheduleBeans s1, ScheduleBeans s2) {
			return s1.getId() < s2.getId() ? -1 : 1;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String input = request.getParameter("action");
		HttpSession session = request.getSession();
		ServletContext application = this.getServletContext();

		User user = (User) session.getAttribute("user");
		Parameter parameter = new Parameter();

		ScheduleDAO dao = new ScheduleDAO();
		ScheduleLogic logic = new ScheduleLogic();

		String forwardPath = "/WEB-INF/jsp/in_fail.jsp";
		boolean result;

		/**
		 * inputに格納されている値により、遷移先を変更
		 * s_inputの場合、スケジュールの登録完了画面
		 * s_editの場合、スケジュール編集完了画面
		 * s_deleteの場合、スケジュール削除完了画面
		 */

		switch (input) {
		case "s_input":
			//スケジュールインスタンスの生成
			ScheduleBeans SB = parameter.execute(request, user);
			result = logic.execute01(SB);

			if (result) {
				//パスワードの暗号化
				SB = logic.setS_Id(SB);
				application.setAttribute("SB", SB);
				forwardPath = "/WEB-INF/jsp/input_success.jsp";
			} else {
			}
			break;

		case "s_edit":
			//該当するスケジュールインスタンスを取得し、変更を実行
			ScheduleBeans s = (ScheduleBeans) session.getAttribute("SB_e");
			s = parameter.execute(request, s);
			result = logic.execute03(s);
			if (result) {

				forwardPath = "/WEB-INF/jsp/edit_success.jsp";
			} else {
			}
			break;

		case "s_delete":
			//該当するスケジュールインスタンスを取得し、削除を実行
			ScheduleBeans Sd = (ScheduleBeans) session.getAttribute("SB_d");

			result = logic.delete(Sd);
			if (result) {
				forwardPath = "/WEB-INF/jsp/deleteSucess.jsp";
			} else {

			}
			break;
		}

		RequestDispatcher d = request.getRequestDispatcher(forwardPath);
		d.forward(request, response);

	}

}
