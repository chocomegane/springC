package jp.co.rakus.ecommers.web;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 管理者のログイン処理を行うコントローラー.
 * @author kohei.sakata
 *
 */
@Controller
@RequestMapping("/admin")
@Order(1)
public class AdminLoginController {

	/**
	 * フォームを初期化します.
	 * @return フォーム
	 */
	@ModelAttribute
	public AdminLoginForm setUpForm() {
		return new AdminLoginForm();
	}

	/**
	 * 管理者のログイン画面を表示する。<br>
	 * ログイン失敗の際はエラーメッセージを追加表示。
	 * @param form
	 * @param result
	 * @param guestid
	 * @param model
	 * @param error ログイン失敗時に渡される。
	 */
	@RequestMapping("/login")
	String loginForm(AdminLoginForm form,BindingResult result,
			Model model,@RequestParam(required=false) String error) {
		if (error != null) {
	        result.addError(new ObjectError("loginError", "メールアドレスまたはパスワードが不正です。"));
		}
		return "administerLogin";
	}
}
