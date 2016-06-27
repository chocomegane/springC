package jp.co.rakus.ecommers.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * ユーザーのログイン処理を行うコントローラー.
 * @author kohei.sakata
 *
 */
@Controller
@RequestMapping("/cinemaShop")
@SessionAttributes("guestid")
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
	String loginForm(@CookieValue(value="JSESSIONID",required=false,defaultValue="0") String guestid, Model model) {
		model.addAttribute("guestid", guestid);
		System.out.println("guestid:" + guestid);
		return "userLogin";
	}
	
//    /**
//     * @param form
//     * @param result
//     * @param principal
//     * @return
//     */
//    @RequestMapping(value = "/login")
//    public String login(@CookieValue("JSESSIONID") String cookie, BindingResult result, Principal principal) {
//        if (result.hasErrors()) {
//            return loginForm();
//        }
//        LoginUser loginUser = (LoginUser) ((Authentication) principal).getPrincipal();
//		loginUser.setCookieValue(cookie);
//        return "redirect:/menu";
//    }
	
    /**
     * ログイン失敗時の処理を行うメソッド.
     * @param form ログインフォーム
     * @param result
     * @return
     */
    @RequestMapping(value = "/loginError")
    public String loginError(UserLoginForm form,BindingResult result,@CookieValue(value="JSESSIONID",required=false,defaultValue="0") String guestid,Model model) {
    	ObjectError error = new ObjectError("loginError", "メールアドレスまたはパスワードが不正です。");
        result.addError(error);
        return loginForm(guestid,model);
    }

}
