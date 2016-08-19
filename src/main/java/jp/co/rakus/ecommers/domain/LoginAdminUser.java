package jp.co.rakus.ecommers.domain;

import org.springframework.security.core.authority.AuthorityUtils;

import lombok.Data;

/**
 * 管理者のログイン情報を格納するDTOクラス.
 * 
 * @author kohei.sakata
 *
 */
@Data
public class LoginAdminUser extends org.springframework.security.core.userdetails.User {
	/** default serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 通常の管理者情報 */
	private AdminUser adminUser;

	/**
	 * 通常の管理者情報に加え、認可用ロールを設定する。
	 * 
	 * @param adminUser
	 */
	public LoginAdminUser(AdminUser adminUser) {
		super(adminUser.getEmail(), adminUser.getPassword(), AuthorityUtils.createAuthorityList("ROLE_ADMIN"));
		this.adminUser = adminUser;
	}
}
