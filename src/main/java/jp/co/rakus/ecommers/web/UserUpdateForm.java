package jp.co.rakus.ecommers.web;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class UserUpdateForm {
	/** 名前 */
	@NotBlank(message="お名前を入力して下さい" )
	@Size(min =0, max=120,message = "120文字までです")
	private String name;
	/** メールアドレス */
	@Email(message = "メールアドレスが不正です")
	@Size(min =1, max=127, message = "メールアドレスを入力して下さい")
	private String email;

	/** 住所 */
	@NotBlank(message = "アドレスを入力してください")
	@Size(min =0, max=120,message = "120文字までです")
	private String address;
	
	/** 電話番号 のトップ*/
	@Size(max=5)
	@Pattern(regexp = "[0-9]+", message = "1)数字を入力してください")
	@NotBlank(message = "先頭項目を入力してください")
	private String telephoneTop;
	/** 電話番号 のミドル*/
	@Size(max=4)
	@Pattern(regexp = "[0-9]+", message = "2)数字を入力してください")
	@NotBlank(message = "中央項目を入力してください")
	private String telephoneMiddle;
	
	/** 電話番号 のラスト*/
	@Size(max=4)
	@Pattern(regexp = "[0-9]+", message = "3)数字を入力してください")
	@NotBlank(message = "最終項目を入力してください")
	private String telephoneLast;
	
	private String id;
	
	public long getLongId() {
		return Long.parseLong(id);
	}
	

}
