package jp.co.rakus.ecommers.web;

import lombok.Data;

/**
 * ユーザー登録の際のリクエストパラメータを受け取るクラス.
 * 
 * @author tsubasa.kaneko
 *
 */
@Data
public class UserForm {

	/** 名前 */
	private String name;
	/** メールアドレス */
	private String email;
	/** パスワード */
	private String password;
	/** 電話番号 */
	private String telephone;

}
