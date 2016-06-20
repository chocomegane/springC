package jp.co.rakus.ecommers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommers.repository.AdministerRegisterRepository;
import jp.co.rakus.ecommers.web.AdminUserRegisterForm;

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
	 * @param form　管理者登録画面のプログラム
	 */
	public void adminInsert(AdminUserRegisterForm form)
	{
		repository.adminInsert(form);
	}

}