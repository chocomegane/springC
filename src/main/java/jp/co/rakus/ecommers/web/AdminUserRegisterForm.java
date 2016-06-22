package jp.co.rakus.ecommers.web;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

/**
 * 管理人登録form
 * 
 * @author rakus
 */
@Data
public class AdminUserRegisterForm {
	/** 名前 */
	@NotEmpty(message = "お名前を入力して下さい")
	private String name;
	/** Eメールアドレス */
	@Size(min = 1, max = 127, message = "アドレスを入力して下さい")
	@Email(message = "アドレスが不正です。")
	private String email;
	/** パスワード */
	@NotEmpty(message = "パスワードを入力して下さい")
	@Size(min =8, max=16,message = "パスワードは8文字以上16文字以下で登録してください")
	private String password;
	/** 確認パスワード */
	@NotEmpty(message = "確認パスワードを入力して下さい")
	@Size(min =8, max=16,message = "確認パスワードは8文字以上16文字以下で登録してください")
	private String confirmationPassword;
	
}
