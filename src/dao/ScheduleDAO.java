package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ScheduleBeans;
import passwordUtil.PasswordUtil;

public class ScheduleDAO {
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/Calendar";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";

	private String sql_insert = "INSERT INTO SCHEDULE (ID, \"DAY\", START, FINISH, TITLE, DETAIL,SCHEDULE_ID) VALUES (?,?,?,?,?,?,?)";
	private String sql_select = "SELECT * FROM SCHEDULE ORDER BY id DESC";



	private ScheduleBeans sb;
	private PasswordUtil pu;

	public ScheduleDAO() {
		super();
		this.sb = new ScheduleBeans();
		this.pu = new PasswordUtil();
	}

	/**
	 * スケジュールを生成
	 * @param SB
	 * @return
	 */

	public boolean create(ScheduleBeans SB) {
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
		//	String sql = "INSERT INTO SCHEDULE (ID, \"DAY\", START, FINISH, TITLE, DETAIL,SCHEDULE_ID) VALUES (?,?,?,?,?,?,?)";

			PreparedStatement pStmt = conn.prepareStatement(sql_insert);

			pStmt.setInt(1, SB.getId());
			pStmt.setDate(2, SB.getDate());
			pStmt.setString(3, SB.getStart());
			pStmt.setString(4, SB.getEnd());
			pStmt.setString(5, SB.getTitle());
			pStmt.setString(6, SB.getDetail());

			String u_id = String.valueOf(SB.getId());
			String s_id = u_id + SB.getStart() + SB.getEnd();

		//	PasswordUtil pu = new PasswordUtil();
			s_id = pu.execute02(s_id);

			pStmt.setString(7, s_id);

			int result = pStmt.executeUpdate();
			if (result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * スケジュールテーブルの全データを取得
	 * @return
	 */
	public List<ScheduleBeans> findAll() {
		List<ScheduleBeans> SB = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
		//	String sql = "SELECT * FROM SCHEDULE ORDER BY id DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql_select);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("ID");
				Date day = rs.getDate("DAY");
				String start = rs.getString("START");
				String finish = rs.getString("FINISH");
				String title = rs.getString("TITLE");
				String detail = rs.getString("DETAIL");
				String schedule_id = rs.getString("SCHEDULE_ID");

			//	ScheduleBeans sb = new ScheduleBeans(id, day, start, finish, title, detail, schedule_id);
				 sb = new ScheduleBeans(id, day, start, finish, title, detail, schedule_id);
				SB.add(sb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return SB;
	}

	/**
	 * 編集機能用
	 * 編集に失敗した場合、編集前のデータになるようにトランザクション制御機能を実装
	 * @param SB
	 * @return
	 */

	public boolean edit(ScheduleBeans SB) {

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			conn.setAutoCommit(false);

			String sql_edit = "UPDATE SCHEDULE SET \"DAY\" = ?, START = ?, FINISH = ?, TITLE = ?, DETAIL = ? WHERE SCHEDULE_ID ="
					+ "\'" + SB.getSchedule_id() + "\'";


		//	String sql_edit =
			int result = 0;
			PreparedStatement pStmt = conn.prepareStatement(sql_edit);

			pStmt.setDate(1, SB.getDate());
			pStmt.setString(2, SB.getStart());
			pStmt.setString(3, SB.getEnd());
			pStmt.setString(4, SB.getTitle());
			pStmt.setString(5, SB.getDetail());
		//	pStmt.setString(6, "'" + SB.getSchedule_id() + "'");

		//	pStmt.setString(6,SB.getSchedule_id());

			result = pStmt.executeUpdate();
			if (result != 1) {
				conn.rollback();
				return false;
			}

			conn.commit();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * 該当するスケジュールのみ取得
	 * @param s_id
	 * @return
	 */

	public ScheduleBeans finds(String s_id) {
	//	ScheduleBeans sb = new ScheduleBeans();
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT * FROM SCHEDULE where SCHEDULE_ID =" + "\'" + s_id + "\'";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("ID");
				Date day = rs.getDate("DAY");
				String start = rs.getString("START");
				String finish = rs.getString("FINISH");
				String title = rs.getString("TITLE");
				String detail = rs.getString("DETAIL");
				String schedule_id = rs.getString("SCHEDULE_ID");

				sb = new ScheduleBeans(id, day, start, finish, title, detail, schedule_id);

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return sb;

	}

	/**
	 * 該当スケジュールの削除
	 * @param Sd
	 * @return
	 */

	public boolean deleteFunc(ScheduleBeans Sd) {
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "DELETE FROM SCHEDULE where SCHEDULE_ID =" + "\'" + Sd.getSchedule_id() + "\'";
			int result = 0;
			PreparedStatement pStmt = conn.prepareStatement(sql);
			result = pStmt.executeUpdate();
			if (result != 1) {
				return false;
			}
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

}
