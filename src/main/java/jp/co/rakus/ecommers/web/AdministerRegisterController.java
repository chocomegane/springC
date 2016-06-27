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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.rakus.ecommers.domain.AdminUser;
import jp.co.rakus.ecommers.service.AdministerRegisterService;

/**
 * ユーザー登録関連のコントローラー
 * 
 * @author rakus
 */

@Controller
@RequestMapping(value = "/admin/register")
public class AdministerRegisterController {

	@Autowired
	private AdministerRegisterService service;

	/**
	 * @return
	 */
	@ModelAttribute
	public AdminUserRegisterForm setupForm() {
		return new AdminUserRegisterForm();
	}

	@RequestMapping(value = "/")
	public String index() {
		return "administerRegister";
	}

	/**
	 * 管理者の追加します
	 * 
	 * @param form
	 *            リクエストパラメータ
	 * @return 管理者メニューにフォワード
	 */
	@RequestMapping(value = "/adminRegister")
	public String adminInsert(@Validated AdminUserRegisterForm form, BindingResult result, RedirectAttributes redirect, Model model)
	{
		String email = form.getEmail(); //
		String confirmationPassword = form.getConfirmationPassword();
		String password = form.getPassword(); 
	 System.out.println(password+":"+confirmationPassword);
		if(result.hasErrors())
		{
			return index();
		}
		
		boolean error = false; 
		
		if(!password.equals(confirmationPassword))
		{
			String err = "確認パスワードとパスワードが違います";
			redirect.addFlashAttribute("err1", err);
			error = true;
			// return "redirect:/admin/register/";
		}
		AdminUser adminUser = new AdminUser();
		BeanUtils.copyProperties(form, adminUser);
		String rawPssword = adminUser.getPassword();
	    register(adminUser, rawPssword);
//	    System.out.println(adminUser);
	    AdminUser testUser = service.findByEmail(email);
	 
	    if(!(testUser == null)) {
	    	String err = "そのアドレスはすでに使われています" ;
	    	redirect.addFlashAttribute("err2",err);
	    	error = true;
	    	// return "redirect:/admin/register/";
	    }
//	    System.out.println(adminUser);

	    if(error == true) return "redirect:/admin/register/";
	    
		service.adminInsert(adminUser);
		return "redirect:/admin/menu";
		
	}

	/**
	 * パスワードの暗号化します。
	 * 
	 * @param adminUser
	 *            DTO
	 * @param rawPssword
	 *            暗号化前のパスワード
	 */
	public void register(AdminUser adminUser, String rawPssword) {
		BCryptPasswordEncoder spe = new BCryptPasswordEncoder();
		String encryptPassword = spe.encode(rawPssword);
		adminUser.setPassword(encryptPassword);
	}

}