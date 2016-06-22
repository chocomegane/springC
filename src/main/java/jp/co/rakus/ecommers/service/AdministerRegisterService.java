package jp.co.rakus.ecommers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommers.domain.AdminUser;
import jp.co.rakus.ecommers.repository.AdminUserRepository;

/**
 * 管理者追加　レポジトリ
 * @author rakus
 */
@Service
public class AdministerRegisterService {
	@Autowired
	private AdminUserRepository repository;
	
	/**
	 * 管理者を追加
	 * 
	 */
//	public void adminInsert(String name , String password , String email)
	public void adminInsert(AdminUser adminUser)
	{
		
//		repository.adminInsert(name,password, email);
//		repository.adminInsert(adminUserRegister);
		repository.adminInsert(adminUser);
		
	}
	
	/**
	 * emailを使って検索をかけます。
	 * @param email　リクエストパラメータ
	 * @return　検索結果が返ります
	 */
	public AdminUser findByEmail(String email)
	{
		return repository.findByEmail(email);
	}

}