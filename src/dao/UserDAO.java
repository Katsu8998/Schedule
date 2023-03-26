package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import commonPass.Register;
import model.User;

public class UserDAO {
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/Calendar";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";

	/**
	 * 	USERテーブルから全データを取得
	 * @return
	 */
	public List<User>findAll(){
		List<User>user = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT * FROM \"USER\" ORDER BY id DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("ID");
				String name = rs.getString("NAME");
				String password = rs.getString("PASSWORD");

				User u = new User (password, name,id);
				user.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return user;
	}

	/**
	 * パスワードを更新
	 * @param user
	 * @param new_pass
	 * @return
	 */

	public boolean changePass(User user, String new_pass) {
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "UPDATE \"USER\" set PASSWORD= ? WHERE ID=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1,user.getPassword());
			pStmt.setInt(2, user.getId());
			int result = pStmt.executeUpdate();
			if(result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 *新規ユーザーを登録
	 * @param user
	 * @return
	 */

	public int register(User user) {
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "INSERT INTO \"USER\" (ID, NAME, PASSWORD) VALUES(?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, user.getId());
			pStmt.setString(2, user.getName());
			pStmt.setString(3, user.getPassword());

			int result = pStmt.executeUpdate();
			if(result != 1) {
				return Register.Register_NG_Factory;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return Register.Register_NG_UPDATE;
		}
		return Register.Register_OK;

	}

}
