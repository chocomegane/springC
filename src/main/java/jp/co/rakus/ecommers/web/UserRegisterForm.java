package jp.co.rakus.ecommers.web;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;



@Data
public class UserRegisterForm {
	/** 名前 */
	@NotBlank(message="お名前を入力して下さい" )
	@Size(min =0, max=120,message = "120文字までです")
	private String name;
	/** メールアドレス */
	@Email(message = "メールアドレスが不正です")
	@Size(min =1, max=127, message = "メールアドレスを入力して下さい")
	private String email;
	/** パスワード */
	@NotBlank(message = "パスワードを入力して下さい")
	@Size(min =8, max=16,message = "パスワードは8文字以上16文字以下で登録してください")
	private String password;
	/** 確認用パスワード */
	@NotBlank(message = "確認用パスワードを入力して下さい")
	@Size(min =8, max=16,message = "確認パスワードは8文字以上16文字以下で登録してください")
	private String confirmPassword;
	/** 電話番号 のトップ*/
	@Min(1)
	@Max(4)
	private String telephoneTop;
	
	/** 住所 */
	@NotBlank(message = "アドレスを入力してください")
	@Size(min =0, max=120,message = "120文字までです")
	private String address;
	/** 電話番号 のミドル*/
	@Min(1)
	@Max(4)
	private String telephoneMiddle;
	
	/** 電話番号 のラスト*/
	@Min(1)
	@Max(4)
	private String telephoneLast;

}
