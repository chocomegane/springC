package jp.co.rakus.ecommers.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 管理者を表すDTOクラス.
 * @author kohei.sakata
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminUser {
	/** DB上のID */
	private Long id;
	/** 名前 */
	private String name;
	/** Eメールアドレス */
	private String email;
	/** パスワード */
	private String password;
}
