package jp.co.rakus.ecommers.web;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

/**
 * 管理者ログイン関連のリクエストパラメータが入るフォーム.
 * @author kohei.sakata
 *
 */
@Data
public class AdminLoginForm {
	/** メールアドレス */
	@NotBlank(message = "値を入力してください")
	private String mailAddress;
	/** パスワード */
	@NotBlank(message = "値を入力してください")
	private String password;
}
