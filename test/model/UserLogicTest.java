package model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import commonPass.Register;
import dao.UserDAO;

public class UserLogicTest {
	@InjectMocks
	private UserLogic UL = new UserLogic();

	@Mock
	private User UN = new User();

	@Mock
	private UserDAO dao = new UserDAO();

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void selectUser_UserLogicTest() {
		User user = new User("12345", "Alice", 1);
		List<User> users = new ArrayList<>();
		users.add(user);
		int id = 1;
		String pass = "12345";

		UN = UL.selectUser(users, id, pass);
		assertEquals(1, UN.getId());
		assertEquals("Alice", UN.getName());
		assertEquals("12345", UN.getPassword());

	}

	@Test
	public void selectUser02_UserLogicTest() {
		User user = new User("12345", "Alice", 1);
		List<User> users = new ArrayList<>();
		users.add(user);
		int id = 2;
		String pass = "12345";

		UN = UL.selectUser(users, id, pass);
		assertNotEquals(1, UN.getId());
	}

	@Test
	public void register_UserLogicTest() {
		User user = new User("12345", "Alice", 1);
		when(dao.register(user)).thenReturn(Register.Register_OK);

		assertEquals(Register.Register_OK, UL.register(user));

	}

	@Test
	public void register02_UserLogicTest() {
		User user = new User("12345", "Alice", 1);
		when(dao.register(user)).thenReturn(Register.Register_NG_UPDATE);

		assertEquals(Register.Register_NG_UPDATE, UL.register(user));

	}

	@Test
	public void register03_UserLogicTest() {
		User user = new User("12345", "Alice", 1);
		when(dao.register(user)).thenReturn(Register.Register_NG_Factory);

		assertEquals(Register.Register_NG_Factory, UL.register(user));

	}
}
