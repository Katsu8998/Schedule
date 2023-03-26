package commonPass;

public class Register {
	/** 登録完了：OK */
	public static final int Register_OK			= 0;

	/** 登録失敗：NG 既存のIDがある */
	public static final int Register_NG_UPDATE	= 1;

	/** 登録失敗：例外発生 */
	public static final int Register_NG_Factory	= 2;


}
