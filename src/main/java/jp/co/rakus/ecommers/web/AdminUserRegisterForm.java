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
	@NotEmpty(message = "名前は必須です")
	private String name;
	/** Eメールアドレス */
	@Size(min = 1, max = 127, message = "入力してください")
	@Email(message = "メールアドレスが不正です。")
	private String email;
	/** パスワード */
	@Size(min = 1,max = 127, message = "パスワードを入力してください")
	private String password;
	/** 確認パスワード */
	@Size(min = 1, max = 127 , message = "確認用パスワードを入力してください" )
	private String confirmationPassword;
	
}
