		package jp.co.rakus.ecommers.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ユーザー情報を格納するクラス.
 * 
 * @author tsubasa.kaneko
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	/** 主キー */
	private Long id;
	/** 名前 */
	private String name;
	/** メールアドレス */
	private String email;
	/** パスワード */
	private String password;
	/** 住所 */
	private String address;
	/** 電話番号 */
	private String telephone;
	/**電話番号の上の桁*/
	private String telephoneTop; 
	/**電話番号の中の桁*/
	private String telephoneMiddle;
	/**電話番号の下の桁*/
	private String telephoneLast;
}
