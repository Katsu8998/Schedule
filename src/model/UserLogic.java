package model;

import java.util.List;

import commonPass.Register;
import dao.UserDAO;

public class UserLogic {
	/**
	 * Login画面にて、入力されたIDとパスワードがユーザーテーブルにあるデータを一致するか確認
	 * @param user
	 * @param id
	 * @param password
	 * @return
	 */
	public User selectUser(List<User> user, int id, String password) {

		 User UN = new User();

		 for(User u : user) {
			 if(u.getId()==id) {
				 UN.setName(u.getName());
				 UN.setPassword(password);
				 UN.setId(id);
				 break;
			 }
		 }
		 return UN;

	}
	/**
	 * 登録完了の場合、更新完了メッセージ、
	 * 失敗の場合、登録失敗メッセージ。もしくは例外発生メッセージを伝える
	 * @param user
	 * @return
	 */

	public int register(User user) {
		UserDAO dao = new UserDAO();
		int result = dao.register(user);

		if(result == 0) {
			return Register.Register_OK;

		}else if(result == 1) {
			return Register.Register_NG_UPDATE;

		}else {
			return Register.Register_NG_Factory;
		}
	}

}
