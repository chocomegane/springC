package jp.co.rakus.ecommers.web;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

/**
 * ユーザーログイン関連のリクエストパラメータが入るフォーム.
 * @author kohei.sakata
 *
 */
@Data
public class UserLoginForm {
	/** メールアドレス */
	@NotBlank(message = "値を入力してください")
	private String email;
	/** パスワード */
	@NotBlank(message = "値を入力してください")
	private String password;
	/** ログイン前に持っていたCookieのJSESSIONID */
	private String cookieValue;
}
