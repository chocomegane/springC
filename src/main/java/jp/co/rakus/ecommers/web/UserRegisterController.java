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
 * 
 * @author rakus
 *
 **/
@Controller
@RequestMapping(value = "/")
public class UserRegisterController {

	@Autowired
	private UserRegisterService service;

	/**
	 * form初期化
	 * 
	 * @return
	 */
	@ModelAttribute
	private UserRegisterForm setUp() {
		return new UserRegisterForm();
	}

	/**
	 * ユーザーの登録画面へフォワード
	 * 
	 * @return
	 */
	@RequestMapping(value = "/registerForm")
	public String index() {
		return "userRegister";
	}

	/**
	 * ユーザーの情報をINSERT
	 * 
	 * @param form
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/register")
	public String userInsert(@Validated UserRegisterForm form, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return index();
		}

		String email = form.getEmail();
		String confirmPassword = form.getConfirmPassword();
		String password = form.getPassword();
		String telephoneTop = form.getTelephoneTop();
		String telephoneMiddle = form.getTelephoneMiddle();
		String telephoneLast = form.getTelephoneLast();

		System.out.println("err1");

		Boolean telephoneCheck = false;
		// Telephoneの確認////////////////////////////////////////////////////////////////////////////////
		if (telephoneTop.equals("") || telephoneMiddle.equals("") || telephoneLast.equals("")) {
			System.out.println("err2");
			String telephoneErr1 = "電話番号を入力してください";
			model.addAttribute("telephoneErr1", telephoneErr1);
			model.addAttribute("flag1", true);
			telephoneCheck = true;
		}

		if (telephoneCheck(telephoneTop, telephoneMiddle, telephoneLast)) {
			System.out.println("222");
			String telephoneErr2 = "文字入力はできません";
			model.addAttribute("telephoneErr2", telephoneErr2);
			model.addAttribute("flag2", true);
			telephoneCheck = true;
		}
		/////////////////////////////////////////////////////////////////////////////////////////////////

		if (result.hasErrors() || telephoneCheck) {
			System.out.println(result);
			System.out.println("err3");

			return index();
		}

		// 確認用パスワードとパスワードの比較//////////////////////////////////////////////////////
		if (!password.equals(confirmPassword)) {
			System.out.println("err4");
			String err = "確認パスワードとパスワードが違います";
			model.addAttribute("err", err);
			return "userRegister";

		}
		////////////////////////////////////////////////////////////////////////////////////////////////////////

		User testUser = service.findByEmail(email);
		if (!(testUser == null)) {
			String err = "そのアドレスはすでに使われています";
			model.addAttribute("err", err);
			return "userRegister";
		}

		User user = new User();
		BeanUtils.copyProperties(form, user);

		String telephone = telephoneTop + "-" + telephoneMiddle + "-" + telephoneLast;
		user.setTelephone(telephone);
		String rawPssword = user.getPassword();

		register(user, rawPssword);

		service.userInsert(user);
		System.out.println("goal");
		return "redirect:/login";
	}

	/**
	 * パスワードの暗号化
	 * 
	 * @param user
	 * @param rawPssword
	 */
	public void register(User user, String rawPssword) {
		BCryptPasswordEncoder spe = new BCryptPasswordEncoder();
		String encryptPassword = spe.encode(rawPssword);
		user.setPassword(encryptPassword);
	}

	/**
	 * 電話番号の確認
	 * 
	 * @param telephoneTop
	 * @param telephoneMidle
	 * @param telephoneLast
	 * @return
	 */
	public boolean telephoneCheck(String telephoneTop, String telephoneMidle, String telephoneLast) {
		try {
			Integer.parseInt(telephoneLast);
			Integer.parseInt(telephoneMidle);
			Integer.parseInt(telephoneTop);
		} catch (NumberFormatException e) {
			return true;
		}
		return false;
	}

}
