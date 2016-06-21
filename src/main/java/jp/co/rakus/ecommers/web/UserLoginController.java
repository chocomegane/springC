package jp.co.rakus.ecommers.web;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ユーザーのログイン処理を行うコントローラー.
 * @author kohei.sakata
 *
 */
@Controller
@RequestMapping("/user")
public class UserLoginController {

	/**
	 * フォームを初期化します.
	 * @return フォーム
	 */
	@ModelAttribute
	public UserLoginForm setUpForm() {
		return new UserLoginForm();
	}

	/**
	 * ログイン画面を表示する。
	 * @return
	 */
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
     * ログイン失敗時の処理を行うメソッド.
     * @param form ログインフォーム
     * @param result
     * @return
     */
    @RequestMapping(value = "/loginError")
    public String loginError(UserLoginForm form,BindingResult result) {
    	ObjectError error = new ObjectError("loginError", "メールアドレスまたはパスワードが不正です。");
        result.addError(error);
        return loginForm();
    }

}
