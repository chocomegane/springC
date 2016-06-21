package jp.co.rakus.ecommers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 利用者登録関係コントローラ
 * @author rakus
 *
 */
@Controller
@RequestMapping(value = "/userRegister")
public class UserRegisterController {
	
	/**
	 * form初期化
	 * @return
	 */
	@ModelAttribute
	private UserRegisterForm setUp()
	{
		return new UserRegisterForm();
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/")
	public String index()
	{
		return "/userRegister";
	}
	
	public
	
}
