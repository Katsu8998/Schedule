package dao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.Date;
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

import model.ScheduleBeans;
import model.User;
import passwordUtil.PasswordUtil;
import testUtil.TestUtil;

public class ScheduleDAO_Test {

	private final static String JDBC_URL = "jdbc:h2:tcp://localhost/~/Calendar";
	private final static String DB_USER = "sa";
	private final static String DB_PASS = "";

	@InjectMocks
	private ScheduleDAO dao = new ScheduleDAO();

	@Mock
	private ScheduleBeans SB = new ScheduleBeans();

	@Mock
	private PasswordUtil p = new PasswordUtil();

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
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "DROP TABLE IF EXISTS Schedule ;\n" +
			//	String sql = "INSERT INTO SCHEDULE (ID, \"DAY\", START, FINISH, TITLE, DETAIL,SCHEDULE_ID) VALUES (?,?,?,?,?,?,?)";

					"create table SCHEDULE (ID INT, \"DAY\" VARCHAR (100), START VARCHAR(255), FINISH VARCHAR(255), TITLE VARCHAR(255), DETAIL VARCHAR(255), SCHEDULE_ID VARCHAR(255) PRIMARY KEY );\n"
					+
					"insert into SCHEDULE values(1, '2023-04-01', '8:00', '17:00', 'Work', 'JAVA', 'c9aa4a7018f56298ceb2a797d8758cd4ad03fdae');";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.execute();
		}
	}

	@Test
	public void create01_test() throws Exception {
		Date date = Date.valueOf("2023-04-01");
		SB = new ScheduleBeans(1, date, "18:00", "21:00", "School", "Java");

		TU.setPrivateField(dao, "sql_insert",
				"INSERT INTO SCHEDULE (ID, \"DAY\", START, FINISH, TITLE, DETAIL,SCHEDULE_ID) VALUES (?,?,?,?,?,?,?)");

		String u_id = String.valueOf(SB.getId());
		String s_id = u_id + SB.getStart() + SB.getEnd();
		when(p.execute02(s_id)).thenReturn("0111");
		boolean result = dao.create(SB);
		assertTrue(result);
	}

	@Test
	public void create02_test() throws Exception {
		Date date = Date.valueOf("2023-04-01");
		SB = new ScheduleBeans(10, date, "18:00", "21:00", "School", "Java");

		TU.setPrivateField(dao, "sql_insert",
				"delete from SCHEDULE where ID = ?");

		String u_id = String.valueOf(SB.getId());
		String s_id = u_id + SB.getStart() + SB.getEnd();
		when(p.execute02(s_id)).thenReturn("0111");
		boolean result = dao.create(SB);
		assertEquals(false, result);
	}

	@Test
	public void findAll01_test() throws Exception {
		List<ScheduleBeans>SBs = dao.findAll();
		assertNotNull(SBs);

	}

	@Test
	public void findAll02_test() throws Exception {
		TU.setPrivateField(dao, "sql_select", "SELECT ID, NAME FROM  SHCEDULE");
		assertEquals(null, dao.findAll());

	}

}
