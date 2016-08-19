package jp.co.rakus.ecommers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommers.domain.AdminUser;
import jp.co.rakus.ecommers.domain.LoginAdminUser;
import jp.co.rakus.ecommers.repository.AdminUserRepository;

/**
 * 管理者の情報を仲介するサービスクラス.
 * 
 * @author kohei.sakata
 *
 */
@Service
public class LoginAdminUserService implements UserDetailsService {
	/** DBから情報を得るためのリポジトリを呼び出すDI */
	@Autowired
	private AdminUserRepository adminUserRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String) DBから検索をし、ログイン情報を構成して返す。
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		AdminUser adminUser = adminUserRepository.findByEmail(email);
		if (adminUser == null) {
			throw new UsernameNotFoundException("そのEmailは登録されていません。");
		}
		return new LoginAdminUser(adminUser);
	}
}
