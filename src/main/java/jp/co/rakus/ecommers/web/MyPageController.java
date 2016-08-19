
package jp.co.rakus.ecommers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.rakus.ecommers.domain.AdminUser;
import jp.co.rakus.ecommers.domain.User;
import jp.co.rakus.ecommers.service.CreatePageService;
import jp.co.rakus.ecommers.service.MyPageService;
import jp.co.rakus.ecommers.service.OrderListService;

/**
 * ユーザーのコントローラ
 * 
 * @author rakus
 *
 */
@Controller
@RequestMapping(value = "/myPage")
public class MyPageController {

	@Autowired
	private MyPageService service;

	@Autowired
	private CreatePageService service2;

	@ModelAttribute
	public UserForm setUpUserForm() {
		return new UserForm();
	}

	@ModelAttribute
	public PassWordForm setUpPassWordForm() {
		return new PassWordForm();
	}

	@ModelAttribute
	public UserUpdateForm setUpUserUpdateForm() {
		return new UserUpdateForm();
	}

	/**
	 * ユーザーのみの注文情報を受け取り 注文詳細画面へ移動
	 * 
	 * @return
	 */
	@RequestMapping(value = "/")
	public String myPageTop(@RequestParam long id, Model model) {

		OrderListPage page = service.findByOne(id);
		System.out.println("err");
		if (!page.getCinemaList().isEmpty()) {
			model.addAttribute("page", page);
			model.addAttribute("flag", true);
			return "myPageTop";
		} else {
			System.out.println("eles");
			model.addAttribute("flag", false);
			return "myPageTop";
		}
	}

	@RequestMapping("/orderDetailed")
	public String orderDetailed(@RequestParam String orderNumber, Model model) {
		OrderListDetailPage page = service2.list(orderNumber);

		model.addAttribute("page", page);
		System.out.println("zyake");
		return "orderDetailed";
	}

	/**
	 * ユーザー情報をもらい 更新ページに移動
	 *
	 * @param model
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/userUpdate")
	public String findAll(@RequestParam long id, Model model) {

		User user = service.findById(id);
		System.out.println(user.getTelephone());
		String telephone[] = user.getTelephone().split("-", 0);
		String telephoneTop = telephone[0];
		String telephoneMidle = telephone[1];
		String telephoneLast = telephone[2];
		System.out.println(telephoneTop + telephoneMidle + telephoneLast);
		user.setTelephoneTop(telephoneTop);
		user.setTelephoneMiddle(telephoneMidle);
		user.setTelephoneLast(telephoneLast);
		model.addAttribute("user", user);
		return "userUpdate";
	}

	/**
	 * ユーザー情報更新メソッド
	 * 
	 * @param model
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/userUpdate/execuse")
	public String userUpdate(@Validated UserUpdateForm form, BindingResult result, Model model, User user) {
		String name = form.getName();
		String email = form.getEmail();
		String address = form.getAddress();
		long id = form.getLongId();
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

			return "userUpdate";
		}
		String telephone = telephoneTop + "-" + telephoneMiddle + "-" + telephoneLast;

		System.out.println(form);
		System.out.println(user);
		service.userUpdate(name, email, telephone, address, id);
		String message = "更新が完了しました";
		model.addAttribute("message", message);
		return "userUpdate";
	}

	/**
	 * パスワード変更ページへ移動します
	 * 
	 * @return
	 */
	@RequestMapping("/passWordUpdate")
	public String passWordUpdateView() {
		return "passWordUpdate";
	}

	@RequestMapping("/passWordUpdate/execuse")
	public String passWordUpdate(@Validated PassWordForm form, BindingResult results, Model model, User user) {
		if (results.hasErrors()) {
			System.out.println(results);
			System.out.println("err3");

			return "passWordUpdate";
		}
		// 宣言
		String rawPassword = form.getPassword();
		System.out.println("form=" + form);
		long id = form.getLongId();
		String confirmPassword = form.getConfirmPassword();
		if (!rawPassword.equals(confirmPassword)) {
			String err = "パスワードと確認用パスワードが一致していません";
			System.out.println(err);
			model.addAttribute("newPassWordErr", err);
			return "passWordUpdate";
		}
		String password = newPassWordRegister(rawPassword);
		// 確認用パスワードとパスワードの一致確認

		service.passWordUpdate(password, id);
		String result = "更新が完了しました";
		System.out.println("dddd");
		model.addAttribute("result", result);

		return "passWordUpdate";
	}

	/**
	 * 文字列を暗号化
	 * 
	 * @param adminUser
	 * @param rawPssword
	 */
	public void register(User user, String rawPssword) {
		BCryptPasswordEncoder spe = new BCryptPasswordEncoder();
		String encryptPassword = spe.encode(rawPssword);
		user.setPassword(encryptPassword);
	}

	/**
	 * 新規パスワードの暗号化
	 * 
	 * @param rawPssword
	 * @return
	 */
	public String newPassWordRegister(String rawPssword) {
		BCryptPasswordEncoder spe = new BCryptPasswordEncoder();
		return spe.encode(rawPssword);
	}

	/**
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
