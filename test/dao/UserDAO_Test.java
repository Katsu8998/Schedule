package dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import commonPass.Register;
import model.User;
import testUtil.TestUtil;

public class UserDAO_Test {
	private final static String JDBC_URL = "jdbc:h2:tcp://localhost/~/Calendar";
	private final static String DB_USER = "sa";
	private final static String DB_PASS = "";


	@InjectMocks
	private UserDAO dao = new UserDAO();

	@Mock
	Connection conn;

	@Mock
	PreparedStatement pStmt;

	@Mock
	ResultSet rs;

	@Mock
	private User user = new User();

	@Mock
	private TestUtil TU = new TestUtil();

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@BeforeAll
	static void resetDB() throws SQLException {
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			String sql =
						"DROP TABLE IF EXISTS \"USER\";\n" +
						"create table \"USER\" (ID INT PRIMARY KEY, NAME VARCHAR (100), PASSWORD VARCHAR(255));\n" +
						"insert into \"USER\" values(1, 'Alice', '1234');";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.execute();
		}
	}

	@Test
	public void findAll01_UserDAO() {
		List<User>users = dao.findAll();
		assertNotNull(users);
	}

	@Test
	public void findAll02_UserDAO() {
		List<User>users = dao.findAll();
		boolean flg = false;
		flg = users.stream().anyMatch(u -> u.getName().equals("Alice"));
		assertEquals(true, flg);
	}

	@Test
	public void findAll_test03() throws Exception {
		TU.setPrivateField(dao, "sql_select",
				"SELECT id, name, password, age FROM \"USER\" ORDER BY id DESC");
		assertEquals(null, dao.findAll());
	}

	@Test
	public void create_test01() throws Exception {
		User user = new User("1234", "Alice", 1);
		String newPass = "12";
		String pass = "1234";

		TU.setPrivateField(dao, "sql_change",
				"UPDATE \"USER\" set \"PASSWORD\"= ? WHERE ID=?");
		boolean result = dao.changePass(user, newPass);
		assertTrue(result);
	}

	@Test
	public void create_test02() throws Exception {
		User user = new User("1234", "Alice", 1);
		String newPass = "12";
		String pass = "1234";

		TU.setPrivateField(dao, "sql_change",
				"UPDATE \"USER\" set AGE = ? WHERE ID=?");
		boolean result = dao.changePass(user, newPass);
		assertEquals(false, result);
	}

	@Test
	public void create_test03() throws Exception {
		User user = new User("1234", "Mike", 10);
		String newPass = "12";

		TU.setPrivateField(dao, "sql_change",
				"UPDATE \"USER\" set PASSWORD = ? WHERE ID=?");

		boolean result = dao.changePass(user, newPass);
		assertEquals(false, result);
	}

	@Test
	public void register_test01() throws Exception {
		User user = new User("1234", "Mike", 2);
		TU.setPrivateField(dao, "sql_register",
				"INSERT INTO \"USER\" (ID, NAME, PASSWORD) VALUES(?,?,?)");

		int result = dao.register(user);
		assertEquals(Register.Register_OK, result);
	}

	@Test
	public void register_test02() throws Exception {
		User user = new User("1234", "Mike", 1);
		TU.setPrivateField(dao, "sql_register",
				"INSERT INTO \"USER\" (ID, NAME, PASSWORD) VALUES(?,?,?)");

		int result = dao.register(user);
		assertEquals(Register.Register_NG_UPDATE, result);
	}

	@Test
	public void register_test03() throws Exception {
		User user = new User("1234", "Mike",3 );
		TU.setPrivateField(dao, "sql_register",
				"delete from \"USER\" where ID = ? AND NAME = ? AND PASSWORD = ?");

		int result = dao.register(user);
		assertEquals(Register.Register_NG_Factory, result);
	}






}
