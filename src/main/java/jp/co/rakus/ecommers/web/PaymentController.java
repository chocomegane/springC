package jp.co.rakus.ecommers.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.rakus.ecommers.domain.User;
import jp.co.rakus.ecommers.service.PaymentService;
import jp.co.rakus.ecommers.service.UserService;

@Controller
@Transactional
@RequestMapping
public class PaymentController {
	@Autowired
	private UserService userService;
	@Autowired
	private PaymentService paymentService;
	
	/**
	 * 決済表示画面を表示する
	 * @param principal ログイン中のユーザー情報
	 * @param model モデルオブジェクト
	 * @return　決済表示画面のｊｓｐ
	 */
	@RequestMapping("/mekePayment")
	public String makePayment(Principal principal, @CookieValue("JSESSIONID") String cookie, Model model) {
		User user = userService.chkUser(principal, cookie);
		model.addAttribute("paymentPage", paymentService.createPaymentPage(user));
		return "makePayment";
	}
	
	@RequestMapping("/finishPayment")
	public String finishPayment(@RequestParam Long orderId, Model model){
		if(paymentService.updateOrder(orderId)){
			model.addAttribute("message", "決済が完了しました！\nこの度はご注文ありがとうございます。\n\nお支払い先は、お送りしたメールに記載してありますのでご確認ください。");
		}else{
			model.addAttribute("message", "注文情報がありません");
		}
		return "finishPayment";
	}
}
