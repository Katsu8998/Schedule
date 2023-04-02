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

import model.SearchBeans;
import model.User;
import testUtil.TestUtil;

public class SearchDAOTest {
	private final static String JDBC_URL = "jdbc:h2:tcp://localhost/~/Calendar";
	private final static String DB_USER = "sa";
	private final static String DB_PASS = "";

	@InjectMocks
	private SearchDAO dao = new SearchDAO();

	@Mock
	Connection conn;

	@Mock
	PreparedStatement pStmt;

	@Mock
	ResultSet rs;

	@Mock
	SearchBeans Sr = new SearchBeans();

	@Mock
	User user = new User();

	@Mock
	TestUtil TU = new TestUtil();

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@BeforeAll
	static void resetDB() throws SQLException {
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "DROP TABLE IF EXISTS Schedule ;\n" +

					"create table SCHEDULE (ID INT, \"DAY\" VARCHAR (100), START VARCHAR(255), FINISH VARCHAR(255), TITLE VARCHAR(255), DETAIL VARCHAR(255), SCHEDULE_ID VARCHAR(255) PRIMARY KEY );\n"
					+
					"insert into SCHEDULE values(1, '2023-04-01', '8:00', '17:00', 'Work', 'JAVA', 'c9aa4a7018f56298ceb2a797d8758cd4ad03fdae');";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.execute();

			String sql2 = "DROP TABLE IF EXISTS \"USER\";\n" +
					"create table \"USER\" (ID INT PRIMARY KEY, NAME VARCHAR (100), PASSWORD VARCHAR(255));\n" +
					"insert into \"USER\" values(1, 'Alice', '1234');";

			PreparedStatement pStmt2 = conn.prepareStatement(sql);
			pStmt2.execute();
		}
	}

	@Test
	public void find01_SearchDAO() {
		List<SearchBeans> SrBs = dao.find(1);
		assertNotNull(SrBs);

	}


	@Test
	public void find02_SearchDAO() throws Exception {
		TU.setPrivateField(dao, "sql_select",
				"SELECT Location FROM \"USER\" INNER JOIN SCHEDULE ON \"USER\".ID = SCHEDULE.ID WHERE \"USER\".ID = ?");
		assertEquals(null, dao.find(1));

	}

	@Test
	public void finds01_test() {
		user = dao.finds(1);
		assertNotNull(user);

	}

	@Test
	public void finds02_test() throws Exception {


		TU.setPrivateField(dao, "sql_select2",
				"SELECT Location FROM \"USER\" WHERE ID = ?");
		assertNull(null, dao.finds(1));

	}


}
