
package jp.co.rakus.ecommers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.rakus.ecommers.domain.AdminUser;
import jp.co.rakus.ecommers.domain.User;
import jp.co.rakus.ecommers.service.MyPageService;
import jp.co.rakus.ecommers.service.OrderListService;




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
    
	@Autowired
	
	@ModelAttribute
	public UserForm setUpForm() {
		return new UserForm();
	}
	
	
	
	/**
	 * 注文詳細画面へ移動
	 * @return
	 */
	@RequestMapping(value = "/")
	public String myPageTop(@RequestParam long id, Model model)
	{
		
		OrderListPage page = service.findByOne(id);
		System.out.println("err");
		if (!page.getCinemaList().isEmpty()) {
			model.addAttribute("page", page);
			model.addAttribute("flag", true);
			return "myPageTop";
		} else {
			System.out.println("eles");
			model.addAttribute("flag", false);
			return "myPageTop";
		}
	}
	
//	@RequestMapping("/myPage/output")
//	public String output(@RequestParam long id, Model model)
//	{
//		OrderListPage page = service.findByOne(id);
//		System.out.println("err");
//		if (!page.getCinemaList().isEmpty()) {
//			model.addAttribute("page", page);
//			model.addAttribute("flag", true);
//			return "myPageTop";
//		} else {
//			System.out.println("eles");
//			model.addAttribute("flag", false);
//			return "myPageTop";
//		}
//		
//	}
	
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
	@RequestMapping("/passWordUpdate/execuse")
	public String passWordUpdate(UserForm form, Model model)
	{
		//宣言
		String rawPassword = form.getPassword();
		long id = form.getLongId();
		String confirmPassword = form.getConfirmPassword();
		String password = newPassWordRegister(rawPassword);
		//確認用パスワードとパスワードの一致確認
		if (!rawPassword.equals(confirmPassword))
		{
			String err = "パスワードと確認用パスワードが一致していません";
			model.addAttribute("newPassWordErr",err);
			return "passWordUpdate";
		}
		
		service.passWordUpdate(password, id);
		String result = "更新が完了しました"; 
		model.addAttribute("result" ,result);
		
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
	
	/**
	 * 新規パスワードの暗号化
	 * @param rawPssword
	 * @return
	 */
	public String newPassWordRegister( String rawPssword) {
		BCryptPasswordEncoder spe = new BCryptPasswordEncoder();
		return spe.encode(rawPssword);
	}


}
