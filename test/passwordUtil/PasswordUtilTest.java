package passwordUtil;

import static org.junit.Assert.*;

import org.junit.Test;

public class PasswordUtilTest {

	/*
	 * 暗号化成功した場合のテスト
	 */

	@Test
	public void PasswordUtilTest01() {
		PasswordUtil PU = new PasswordUtil();
		String result = "";
		String pass = "1234";

		result = PU.execute(pass);
		assertNotEquals(pass,result);
	}

	/*
	 *例外処理テスト
	 *例外処理の実行の場合、空白文字列を返す。
	 */

	@Test
	public void PasswordUtilTest02() {
		PasswordUtil PU = new PasswordUtil();
		String result = "";
		String pass = null;

		assertEquals(result, PU.execute(pass));

	}
	/*
	 * 暗号化成功した場合のテスト
	 */

	@Test
	public void PasswordUtilTest03() {
		PasswordUtil PU = new PasswordUtil();
		String result = "";
		String pass = "1";

		result = PU.execute02(pass);;
		assertNotEquals(pass,result);
	}
	/*
	 *例外処理テスト
	 *例外処理の実行の場合、空白文字列を返す。
	 */

	@Test
	public void PasswordUtilTest04() {
		PasswordUtil PU = new PasswordUtil();
		String result = "";
		String expected = "";

		result = PU.execute02(null);
		assertEquals(expected, result);
	}
}


