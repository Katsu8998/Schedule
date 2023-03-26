package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import dao.ScheduleDAO;
import passwordUtil.PasswordUtil;

/**
 * DAOを使用し、処理するクラス
 * @author katsu
 *
 */
public class ScheduleLogic {
	/**
	 * 新規登録
	 * @param SB
	 * @return
	 */
	public boolean execute01(ScheduleBeans SB) {
		ScheduleDAO dao = new ScheduleDAO();
		return dao.create(SB);
	}

	/**
	 * スケジュール情報を格納
	 * @return
	 */

	public List<ScheduleBeans> execute02() {
		ScheduleDAO dao = new ScheduleDAO();
		List<ScheduleBeans> SB = dao.findAll();



		return SB;
	}



	/**
	 * 編集用
	 * @param SB
	 * @return
	 */
	public boolean  execute03(ScheduleBeans SB) {
		ScheduleDAO dao = new ScheduleDAO();
		return dao.edit(SB);

	}

	/**
	 * パスワードを暗号化し、スケジュールインスタンスのフィールドに設定
	 * @param SB
	 * @return
	 */

	public ScheduleBeans setS_Id(ScheduleBeans SB) {
		ScheduleBeans SB2 = new ScheduleBeans();
		String u_id = String.valueOf(SB.getId());
		String s_id = u_id + SB.getStart() + SB.getEnd();
		PasswordUtil pu = new PasswordUtil();
		s_id = pu.execute02(s_id);
		SB2.setSchedule_id(s_id);

		return SB2;
	}


	/**
	 * IDから該当するスケジュールを取得
	 * @param s_id
	 * @return
	 */
	public ScheduleBeans selectS(String s_id) {
		ScheduleDAO dao = new ScheduleDAO();
		ScheduleBeans s2 = new ScheduleBeans();
		s2 = dao.finds(s_id);
		return s2;

		 }

	/**
	 * 削除用
	 * @param Sd
	 * @return
	 */

	public boolean delete(ScheduleBeans Sd) {
		ScheduleDAO dao = new ScheduleDAO();
		return dao.deleteFunc(Sd);

		 }

	/**
	 * DB上に挿入したユーザーのスケジュール入力とカレンダー画面にてクリックした日付けが一致した場合のみ、予定が出力する
	 */
	public List<ScheduleBeans> dayCheck(List<ScheduleBeans> SB, String day, String month, String year) {
		Date date = null;
		List<ScheduleBeans>SB_True = new ArrayList<>();
		for (ScheduleBeans s : SB) {
			date = s.getDate();

			Calendar c = Calendar.getInstance();
			c.setTime(date);
			int yearDate = c.get(Calendar.YEAR);
			int monthDate = c.get(Calendar.MONTH) + 1;
			int dayDate = c.get(Calendar.DATE);

			int y = Integer.parseInt(year);
			int m = Integer.parseInt(month);
			int d = Integer.parseInt(day);



			boolean result = (yearDate == y && monthDate == m && dayDate == d);
			if (result) {
				SB_True.add(s);
			}
		}
			return SB_True;

	}

}