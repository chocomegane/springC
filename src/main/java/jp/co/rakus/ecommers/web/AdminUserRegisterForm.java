package jp.co.rakus.ecommers.web;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

/**
 * 管理人登録form
 * 
 * @author rakus
 */
@Data
public class AdminUserRegisterForm {
	/** 名前 */
	@NotBlank(message = "お名前を入力して下さい")
	private String name;
	/** Eメールアドレス */
	@Size(min = 1, max = 127, message = "アドレスを入力して下さい")
	@Email(message = "アドレスが不正です。")
	private String email;
	/** パスワード */
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[\\[\\!\\#\\$\\%\\&\\(\\)\\?\\]\\\\]).+", message = "記号、大文字、小文字、数字を含ませてください")
	@NotBlank(message = "パスワードを入力して下さい")
	@Size(min = 8, max = 16, message = "パスワードは8文字以上16文字以下で登録してください")
	private String password;
	/** 確認パスワード */
	@NotBlank(message = "確認パスワードを入力して下さい")
	@Size(min = 8, max = 16, message = "確認パスワードは8文字以上16文字以下で登録してください")
	private String confirmationPassword;

}
