package model;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

public class UserTest {

	/**
	 * コンストラクタなし
	 */
	@Test
	public void Usertest01() {
		User user = new User();
		user.setId(1);
		user.setName("Alice");
		user.setPassword("1234ABC");

		assertEquals(1, user.getId());
		assertEquals("Alice", user.getName());
		assertEquals("1234ABC", user.getPassword());

	}

	/**
	 * コンストラクタあり
	 */

	@Test
	public void Usertest02() {
		User user = new User("123ABC", "Mike", 2);
		assertNotEquals("1234ABC", user.getPassword());
		assertNotEquals("Alice", user.getName());
		assertNotEquals(1, user.getId());

	}
}
