package jp.co.rakus.ecommers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommers.domain.User;
import jp.co.rakus.ecommers.repository.UserRepository;

/**
 * userページ関連のserviceです
 * @author rakus
 *
 */
@Service
public class MyPageService {
	//依存性の注入
	@Autowired
	private UserRepository repository;
	
	/**
	 * レポジトリのメソッドを呼び出します
	 * @param id
	 * @return
	 */
	public User findById(Long id)
	{
		return repository.findById(id);
		
	}
	
	
	/**ユーザー情報を更新します*/
	public void userUpdate(String name,String email,String telephone, String address, long id)
	{
		repository.updetaUser(name, email,telephone,address, id);
	}
	
	/**
	 * パスワードを更新するためのレポジトリを呼び出します。
	 * @param password　新規パスワード
	 * @param id　Id
	 */
	public void passWordUpdate(String password , long id){
		repository.passWordUpdate(password, id);
	}
	

}
