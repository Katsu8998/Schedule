package model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import commonPass.Pass;
import dao.UserDAO;
import passwordUtil.PasswordUtil;

public class LoginCheck {

	/**
	 *ユーザーテーブルから全データを取得
	 * @return
	 */
	public List<User> execute() {
		UserDAO dao = new UserDAO();
		List<User> user = dao.findAll();
		return user;
	}

	/**
	 *パスワードの暗号化
	 * @param user
	 * @param new_pass
	 * @param old_pass
	 * @return
	 */

	public int execute(User user, String new_pass, String old_pass) {
		String password;
		PasswordUtil passwordUtil = new PasswordUtil();
		UserDAO userdao = new UserDAO();

		//パスワードの暗号化
		try {
			password = passwordUtil.execute(new_pass);
		} catch (Exception e) {
			//暗号化パスワード生成例外発生
			return Pass.PASSWORD_NG_FACTORY;
		}

		//変更前のパスワードと一致するか確認
		List<User> people = userdao.findAll();


		//listの要素を昇順に変更
		Collections.sort(people, new MyUserComparator());



		if (old_pass.equals(people.get(user.getId()-1).getPassword())) {
			user.setPassword(new_pass);

			System.out.println(old_pass);
			System.out.println(people.get(user.getId()).getPassword());
			return Pass.PASSWORD_OK;


		} else 	if (!userdao.changePass(user, new_pass)) {
			return Pass.PASSWORD_NG_UPDATE;
		} else {


			return Pass.PASSWORD_NG_UNMACH;
		}


	}

/**
 * ArrayListでまとめたインスタンスの中身を昇順にソート
 * @author katsu
 *
 */
	public class MyUserComparator implements Comparator<User> {
		@Override
		public int compare(User u1, User u2) {
			return u1.getId() < u2.getId() ? -1 : 1;
		}

	}

}
