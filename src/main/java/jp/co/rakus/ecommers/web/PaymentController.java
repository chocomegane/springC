package jp.co.rakus.ecommers.web;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.rakus.ecommers.domain.LoginUser;
import jp.co.rakus.ecommers.domain.User;
import jp.co.rakus.ecommers.service.CartService;
import jp.co.rakus.ecommers.service.PaymentService;

@Controller
@Transactional
@RequestMapping(value="/cinemaShop")
public class PaymentController {
	/** Longに変換した時に桁あふれせずに変換できる16進数の最大桁数 */
	private static final int LONG_DIGIT = 15;
	@Autowired
	private HttpSession session;
	@Autowired
	private CartService cartService;
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
		User user = chkUser(principal, cookie);
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

	/**
	 * Cookieの情報とログインユーザーの情報を統合するメソッド.
	 * @param principal
	 * @param cookie
	 * @return
	 */
	private User chkUser(Principal principal, String cookie) {
		User user = null;
		try {
			if (principal == null) {
				user = new User();
				long guestid = makeUserId(cookie);
				user.setId(guestid);
				user.setName("ゲスト");
			} else {
				LoginUser loginUser = (LoginUser) ((Authentication) principal).getPrincipal();
				user = loginUser.getUser();
				if (loginUser.getGuestId() == null) {
					String jsessionId = (String) session.getAttribute("guestid");
					long guestId = makeUserId(jsessionId);
					loginUser.setGuestId(guestId);
					cartService.joinCart(user, guestId);
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw e;
		}
		return user;
	}

	/**
	 * JSESSIONIDからDBに登録可能なUserIDを生成するメソッド.
	 * @param jsessionId
	 * @return
	 */
	private long makeUserId(String jsessionId) {
		int digit = jsessionId.length() - LONG_DIGIT;
		digit = digit > 0 ? digit : 0; 
		long guestId = Long.parseLong(jsessionId.substring(digit), 16);
		return guestId;
	}

}
