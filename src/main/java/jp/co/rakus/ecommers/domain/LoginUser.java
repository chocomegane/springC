package jp.co.rakus.ecommers.domain;

import org.springframework.security.core.authority.AuthorityUtils;

import lombok.Data;

@Data
public class LoginUser extends org.springframework.security.core.userdetails.User {
	/** default serialVersionUID */
	private static final long serialVersionUID = 1L;
	/** 通常のユーザー情報 */
	private User user;
	/** ログイン前に持っていたCookieのJSESSIONIDから変換したゲスト用UserId */
	private Long guestId;

	/**
	 * 通常の管理者情報に加え、認可用ロールを設定する。
	 * 
	 * @param user
	 */
	public LoginUser(User user) {
		super(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
		this.user = user;
	}
}
