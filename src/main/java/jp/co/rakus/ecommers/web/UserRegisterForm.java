package jp.co.rakus.ecommers.web;

import lombok.Data;

@Data
public class UserRegisterForm {
	/** 名前 */
	private String name;
	/** メールアドレス */
	private String email;
	/** パスワード */
	private String password;
	/** 確認用パスワード */
	private String confirmPassword;
	/** 電話番号 */
	private String telephone;
	/** 住所 */
	private String address;

}
