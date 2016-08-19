package jp.co.rakus.ecommers.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * ユーザーのログイン処理を行うコントローラー.
 * 
 * @author kohei.sakata
 *
 */
@Controller
@RequestMapping
@SessionAttributes("guestid")
public class UserLoginController {

	/**
	 * フォームを初期化します.
	 * 
	 * @return フォーム
	 */
	@ModelAttribute
	public UserLoginForm setUpForm() {
		return new UserLoginForm();
	}

	/**
	 * ユーザーのログイン画面を表示する。<br>
	 * ログイン失敗の際はエラーメッセージを追加表示。
	 * 
	 * @param form
	 * @param result
	 * @param guestid
	 * @param model
	 * @param error
	 *            ログイン失敗時に渡される。
	 * @return
	 */
	@RequestMapping("/login")
	String loginForm(UserLoginForm form, BindingResult result,
			@CookieValue(value = "JSESSIONID", required = false, defaultValue = "0") String guestid, Model model,
			@RequestParam(required = false) String error) {
		// System.err.println(error);
		if (error != null) {
			result.addError(new ObjectError("loginError", "メールアドレスまたはパスワードが不正です。"));
		} else {
			model.addAttribute("guestid", guestid);
		}
		return "userLogin";
	}

	/**
	 * @param ex
	 * @param model
	 * @return
	 */
	@RequestMapping("/403")
	String handleAccessDenied() {
		return "adminAccessError";
	}

}
