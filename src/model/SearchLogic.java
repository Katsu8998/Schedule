package model;

import java.util.List;

import dao.SearchDAO;

public class SearchLogic {
	private SearchLogic SrL;
	private SearchDAO dao;
	private User user;

	public SearchLogic() {
		super();
		this.dao = new SearchDAO();
		this.user = new User();

	}

	/**
	 * 取得IDから該当ユーザーのスケジュールとユーザーの名前やID、パスワードをリストに格納
	 * @param id
	 * @return
	 */
	public List<SearchBeans> execute(int id) {
	//	SearchDAO dao = new SearchDAO();

		List<SearchBeans>SrB = dao.find(id);
		return SrB;
	}

	/**
	 *ユーザーテーブルから全データを取得
	 *上記のpublic List<SearchBeans> execute(int id)を実行し、テーブルにスケジュールがある場合、
	 *画面にユーザー名やIDを出力できるが、存在しない場合、画面にユーザ名等の出力不可。
	 *そのため、スケジュールが存在しない場合でも、ユーザー名とIDを出力を可能にする、
	 * @param id
	 * @return
	 */

	public User execute02(int id) {
	//	SearchDAO dao = new SearchDAO();
	//	User user = dao.finds(id);
		user = dao.finds(id);
		return user;
	}


}
