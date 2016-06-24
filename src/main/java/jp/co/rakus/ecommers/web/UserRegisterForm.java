package jp.co.rakus.ecommers.web;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;



@Data
public class UserRegisterForm {
	/** 名前 */
	@NotBlank(message="お名前を入力して下さい" )
	private String name;
	/** メールアドレス */
	@Email(message = "アドレスを入力して下さい")
	@Size(min =1, max=127, message = "アドレスが不正です。")
	private String email;
	/** パスワード */
	@NotBlank(message = "パスワードを入力して下さい")
	@Size(min =8, max=16,message = "パスワードは8文字以上16文字以下で登録してください")
	private String password;
	/** 確認用パスワード */
	@NotBlank(message = "確認用パスワードを入力して下さい")
	@Size(min =8, max=16,message = "確認パスワードは8文字以上16文字以下で登録してください")
	private String confirmPassword;
	/** 電話番号 */
	@NotBlank(message = "電話番号を入力して下さい")
	@Size(min =1, max=127,message = "電話番号を入力して下さい")
	private String telephone;
	/** 住所 */
	@NotBlank(message = "アドレスを入力してください")
	@Size(min =1, max=127,message = "アドレスを入力してください")
	private String address;

}
