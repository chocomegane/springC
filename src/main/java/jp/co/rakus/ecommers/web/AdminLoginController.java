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
		System.out.println("loginPage");
		return "administerLogin";
	}
}
