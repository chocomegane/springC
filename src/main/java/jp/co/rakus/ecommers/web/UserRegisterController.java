package jp.co.rakus.ecommers.web;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ecommers.domain.User;
import jp.co.rakus.ecommers.service.UserRegisterService;

/**
 * 利用者登録関係コントローラ
 * @author rakus
 *
 */
@Controller
@RequestMapping(value = "/cinemaShop")
public class UserRegisterController {
	
	@Autowired
 	 private UserRegisterService service;
	
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
	@RequestMapping(value = "/registerForm")
	public String index()
	{
		return "userRegister";
	}
	@RequestMapping(value = "/register")
	public String userInsert(@Validated UserRegisterForm form, BindingResult result, Model model)
	{
		String email = form.getEmail(); 
		String confirmPassword = form.getConfirmPassword();
		String password = form.getPassword(); 
	 System.out.println(password+":"+confirmPassword);
		if(result.hasErrors())
		{
			return index();
		}
		
		if(!password.equals(confirmPassword))
		{
			String err = "確認パスワードとパスワードが違います";
			model.addAttribute("err", err);
			return "userRegister";
			
		}
		
		User user = new User();
		BeanUtils.copyProperties(form, user);
		String rawPssword = user.getPassword();
	    register(user, rawPssword);
	    User testUser = service.findByEmail(email);
	 
	    if(!(testUser == null)) {
	    	String err = "そのアドレスはすでに使われています" ;
	    	model.addAttribute("err",err);
	    	return "userRegister";
	    }

		service.userInsert(user);
		return "redirect:/cinemaShop/loginForm";
	}
	
	public void register(User user, String rawPssword) {
		BCryptPasswordEncoder spe = new BCryptPasswordEncoder();
		String encryptPassword = spe.encode(rawPssword);
		user.setPassword(encryptPassword);
	}
	
}
