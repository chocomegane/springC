package jp.co.rakus.ecommers.web;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;



@Data
public class UserRegisterForm {
	/** 名前 */
	@NotEmpty(message="お名前を入力して下さい" )
	private String name;
	/** メールアドレス */
	@Email(message = "アドレスを入力して下さい")
	@Size(min =1, max=127, message = "アドレスが不正です。")
	private String email;
	/** パスワード */
	@Size(min =1, max=127,message = "パスワードを入力して下さい")
	private String password;
	/** 確認用パスワード */
	@Size(min =1, max=127,message = "確認用パスワードを入力して下さい")
	private String confirmPassword;
	/** 電話番号 */
	@Size(min =1, max=127,message = "電話番号を入力して下さい")
	private String telephone;
	/** 住所 */
	@Size(min =1, max=127,message = "そのアドレスはすでに使われています")
	private String address;

}
