package commonPass;

public class Pass {

	/** パスワード変更：OK */
	public static final int PASSWORD_OK			= 0;
	/** パスワード変更：NG 暗号化時の例外発生 */
	public static final int PASSWORD_NG_FACTORY	= 1;
	/** パスワード変更：NG 変更前のパスワードが不一致 */
	public static final int PASSWORD_NG_UNMACH	= 2;
	/** パスワード変更：NG データベースへの更新で例外発生 */
	public static final int PASSWORD_NG_UPDATE	= 3;

}
