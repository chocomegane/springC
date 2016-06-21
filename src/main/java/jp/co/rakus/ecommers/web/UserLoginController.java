package jp.co.rakus.ecommers.web;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 管理者のログイン処理を行うコントローラー.
 * @author kohei.sakata
 *
 */
@Controller
@Order(1)
@RequestMapping("/")
public class UserLoginController {

	/**
	 * フォームを初期化します.
	 * @return フォーム
	 */
	@ModelAttribute
	public UserLoginForm setUpForm() {
		return new UserLoginForm();
	}

	@RequestMapping("/loginForm")
	String loginForm() {
		return "userLogin";
	}
	
	/* ログイン処理は実装しない。SpringSecurityの処理で行われる。
    @RequestMapping(value = "/login")
    public String login(@Valid LoginForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "login/login";
        }
        return "redirect:/menu";
    }
	*/
	
    /**
     * @param form
     * @param result
     * @param model
     * @return
     */
    @RequestMapping(value = "/loginError")
    public String loginError(UserLoginForm form,BindingResult result, Model model) {
    	ObjectError error = new ObjectError("loginError", "メールアドレスまたはパスワードが不正です。");
        result.addError(error);
        return loginForm();
    }

}
