
package jp.co.rakus.ecommers.web;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jp.co.rakus.ecommers.domain.User;
import jp.co.rakus.ecommers.service.MyPageService;


/**
 * ユーザーのコントローラ
 * @author rakus
 *
 */
@Controller
@RequestMapping(value = "/myPage")
public class MyPageController {
	
	@Autowired
	private MyPageService service;
	
	@ModelAttribute
	public UserForm setUpForm() {
		return new UserForm();
	}
	
	
	
	@RequestMapping(value = "/")
	public String myPageTop()
	{
		return "myPageTop";
	}
	
	/**
	 * ユーザー情報をもらい
	 * 更新ページに移動
	 *
	 * @param model
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/userUpdate")
	public String findAll(@RequestParam long id, Model model )
	{
		User user =service.findById(id);
		model.addAttribute("user",user);
		return "userUpdate";
	}
	/**
	 * ユーザー情報更新メソッド
	 * @param model
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/userUpdate/execuse")
	public String userUpdate( Model model,User user,UserForm form)//更新ボタンのとき
	{
		String name = form.getName();
		String email =form.getEmail();
		String telephone =form.getTelephone();
		String address = form.getAddress();
		long id = form.getLongId();
		System.out.println(form);
		System.out.println(user);
		service.userUpdate(name,email, telephone,address, id);
		String message = "更新が完了しました";
		model.addAttribute("message", message);
		return "userUpdate";
	}
	
	
	/**
	 * パスワード変更ページへ移動します
	 * @return
	 */
	@RequestMapping("/passWordUpdate")
	public String passWordUpdateView()
	{
		return "passWordUpdate";
	}
	
	
	
	
	/**
	 * 文字列を暗号化
	 * @param adminUser
	 * @param rawPssword
	 */
	public void register(AdminUser adminUser, String rawPssword) {
		BCryptPasswordEncoder spe = new BCryptPasswordEncoder();
		String encryptPassword = spe.encode(rawPssword);
		adminUser.setPassword(encryptPassword);
	}

}
