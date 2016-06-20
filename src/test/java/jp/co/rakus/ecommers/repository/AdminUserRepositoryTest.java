package jp.co.rakus.ecommers.repository;

import jp.co.rakus.ecommers.domain.AdminUser;

public class AdminUserRepositoryTest {

	public static void main(String[] args) {
		AdminUserRepository adminUserRepository = new AdminUserRepository();
		AdminUser adminUser = adminUserRepository.findByEmail("admin@rakus.co,jp");
		System.out.println(adminUser);
	}

}
