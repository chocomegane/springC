package jp.co.rakus.ecommers.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 管理者のログイン処理を行うコントローラー.
 * @author kohei.sakata
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminLoginController {

	@RequestMapping("/loginForm")
	String loginForm() {
		return "administerLogin";
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
}
