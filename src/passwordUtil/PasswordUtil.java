package passwordUtil;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * 新規パスワードの暗号化
 * @author katsu
 *
 */
public class PasswordUtil {
	public  String execute(String new_pass) {
		String sha = "";
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			byte[] result = digest.digest(new_pass.getBytes());
			sha = String.format("%040x", new BigInteger(1, result));
		} catch (Exception e) {
			e.printStackTrace();

		}
		return sha;
	}

	/**
	 * スケジュールテーブルのプライマリー・キーとして機能する値を生成
	 * @param s_id
	 * @return
	 */
	public String execute02(String s_id) {
		String sha = "";
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			byte[] result = digest.digest(s_id.getBytes());
			sha = String.format("%040x", new BigInteger(1, result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sha;
	}


}
