package model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import commonPass.Pass;
import dao.UserDAO;
import passwordUtil.PasswordUtil;

public class LoginCheckTest {
	@InjectMocks
	private LoginCheck LC = new LoginCheck();

	@Mock
	private PasswordUtil PU = new PasswordUtil();

	@Mock
	private UserDAO dao = new UserDAO();

	@Mock
	private User user = new User();

	@Mock
	private Pass pass = new Pass();

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void loginCheckTest01() {
		List<User> u = new ArrayList();
		User user = new User("1234", "Hana", 8);
		u.add(user);
		when(dao.findAll()).thenReturn(u);
		assertIterableEquals(u, LC.execute());
	}

	/**
	 * PASSWORD_OK
	 */

		@Test
		public void loginCheckTest02() {
			List<User> users = new ArrayList();
			User user = new User("1234", "Hana", 1);
			String new_pass = "12345";
			String old_pass = user.getPassword();
			int expected = 0;
			users.add(user);
			when(dao.findAll()).thenReturn(users);
			when(dao.changePass(user, new_pass)).thenReturn(true);
			assertEquals(expected, LC.execute(user, new_pass, old_pass));
		}


	/**
	 * PASSWORD_NG_UNMACH;
	 */
	@Test
	public void loginCheckTest03() {
		user = new User("1234", "Alice", 1);
		List<User> users = new ArrayList();

		String new_pass = "123";
		String old_pass = "2";
		users.add(user);
		when(dao.findAll()).thenReturn(users);
		when(dao.changePass(user, new_pass)).thenReturn(true);
		int expected = 2;

		assertEquals(expected, LC.execute(user, new_pass, old_pass));

	}
/*
 * .PASSWORD_NG_UPDATE
 */


	@Test
	public void loginCheckTest04() {
		user = new User("1234", "Alice", 1);
		User user2 = new User("123", "Mike", 2);
		//dao  = new UserDAO();

		String new_pass = "123";
		String old_pass = "2";
		List<User> users = new ArrayList();
		users.add(user);
		users.add(user2);

		when(dao.findAll()).thenReturn(users);
		when(dao.changePass(user, new_pass)).thenReturn(false);
		int expected = 3;

		assertEquals(expected, LC.execute(user, new_pass, old_pass));
	}
}
