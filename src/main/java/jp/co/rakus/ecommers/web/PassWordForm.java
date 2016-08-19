package jp.co.rakus.ecommers.web;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
// @NoArgsConstructor
// @AllArgsConstructor
public class PassWordForm {

	/** パスワード */
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[\\[\\!\\#\\$\\%\\&\\(\\)\\?\\]\\\\\\@]).+", message = "記号、大文字、小文字、数字を含ませてください")
	@NotBlank(message = "パスワードを入力して下さい")
	@Size(min = 8, max = 16, message = "パスワードは8文字以上16文字以下で登録してください")
	private String password;

	/** 確認用パスワード */
	@NotBlank(message = "確認用パスワードを入力して下さい")
	@Size(min = 8, max = 16, message = "確認パスワードは8文字以上16文字以下で登録してください")
	private String confirmPassword;

	private String id;

	public long getLongId() {
		return Long.parseLong(id);
	}

}
