package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CalendarBeans;
import model.CalendarLogic;

/**
 * Servlet implementation class Calendar
 */


@WebServlet("/Calendar")
public class Calendar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	//カレンダー画面の前月、翌月リンクをクリックに機能
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int year = Integer.parseInt(request.getParameter("year"));
		int month = Integer.parseInt(request.getParameter("month"));
		CalendarLogic calendarLogic = new CalendarLogic();
		CalendarBeans beans = null;



		if(month==0) {
			month=12;
			year-=1;
		}
		if(month==13) {
			month=1;
			year+=1;
		}
		beans = calendarLogic.execute(year,month);

		HttpSession session = request.getSession();
		session.setAttribute("now", beans);

		String forwardPath = "/WEB-INF/jsp/calendar.jsp";

		RequestDispatcher d = request.getRequestDispatcher(forwardPath);
		d.forward(request, response);


	}

}
