package jp.co.rakus.ecommers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommers.domain.AdminUserRegister;
import jp.co.rakus.ecommers.repository.AdministerRegisterRepository;

/**
 * 管理者追加　レポジトリ
 * @author rakus
 */
@Service
public class AdministerRegisterService {
	@Autowired
	private AdministerRegisterRepository repository;
	
	/**
	 * 管理者を追加
	 * 
	 */
//	public void adminInsert(String name , String password , String email)
	public void adminInsert(AdminUserRegister adminUserRegister)
	{
		
//		repository.adminInsert(name,password, email);
//		repository.adminInsert(adminUserRegister);
		
	}

}