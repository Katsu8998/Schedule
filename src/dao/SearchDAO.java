package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.SearchBeans;
import model.User;

public class SearchDAO {
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/Calendar";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";

	/**
	 * 該当Userのスケジュールと名前をスケジュールテーブル及びユーザーテーブルから取得
	 *
	 * @return
	 */
	public List<SearchBeans> find(int id) {
		List<SearchBeans> SrB = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT * FROM \"USER\" INNER JOIN SCHEDULE ON \"USER\".ID = SCHEDULE.ID WHERE \"USER\".ID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, id);

			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				int user_id = rs.getInt("ID");
				String name = rs.getString("NAME");
				String pass = rs.getString("PASSWORD");
				int su_id = rs.getInt("ID");
				Date day = rs.getDate("DAY");
				String start = rs.getString("START");
				String finish = rs.getString("FINISH");
				String title = rs.getString("TITLE");
				String detail = rs.getString("DETAIL");
				String schedule_id = rs.getString("SCHEDULE_ID");

				SearchBeans Sr = new SearchBeans(user_id, name, day, start, finish, title, detail);
				SrB.add(Sr);
			}
		} catch (SQLException e) {
			e.printStackTrace();

			return null;
		}
		return SrB;

	}

	/**
	 * 該当IDからユーザー名、ID、パスワード取得
	 * @param id
	 * @return
	 */

	public User finds(int id) {
		User user = new User();
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT * FROM \"USER\" WHERE \"USER\".ID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, id);

			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				int user_id = rs.getInt("ID");
				String name = rs.getString("NAME");
				String pass = rs.getString("PASSWORD");

				user = new User(pass, name, user_id);

			}
		} catch (SQLException e) {
			e.printStackTrace();

			return null;
		}
		return user;

	}
}
