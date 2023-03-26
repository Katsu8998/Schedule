package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.SearchBeans;
import model.SearchLogic;
import model.User;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		SearchLogic logic = new SearchLogic();
		int id = Integer.parseInt(request.getParameter("id"));

		//ユーザーテーブルとスケジュールテーブルの内部結合により得たデータをリストに格納
		List<SearchBeans >SrB = logic.execute(id);
		request.setAttribute("research", SrB);

		//リストのサイズが0もしくはnullの場合、ユーザーテーブーブルから該当ユーザーの全情報を取得

		if(SrB.size()==0 || SrB==null) {
			User user = logic.execute02(id);
			request.setAttribute("user", user);
		}

		//検索結果画面に遷移

		String forwardPath = "/WEB-INF/jsp/searchResult.jsp";

		RequestDispatcher d = request.getRequestDispatcher(forwardPath);
		d.forward(request, response);


	}

}
