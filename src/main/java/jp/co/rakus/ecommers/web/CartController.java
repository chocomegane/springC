package jp.co.rakus.ecommers.web;

import java.security.Principal;

import javax.servlet.http.HttpSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.rakus.ecommers.domain.LoginUser;
import jp.co.rakus.ecommers.domain.OrderItem;
import jp.co.rakus.ecommers.domain.User;
import jp.co.rakus.ecommers.service.CartService;

/**
 * カートを操作するコントローラ.
 * 
 * @author takeshi.fujimoto
 *
 */
@Controller
@Transactional
@RequestMapping(value = "/cinemaShop")
public class CartController {
	/** Longに変換した時に桁あふれせずに変換できる16進数の最大桁数 */
	private static final int LONG_DIGIT = 15;
	@Autowired
	private CartService service;
	@Autowired
	private HttpSession session;

	/**
	 * カートに商品を追加
	 * 
	 * @param principal
	 * @param form
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/insert")
	public String insertCart(Principal principal, @CookieValue("JSESSIONID") String cookie, InsertForm form, Model model) {
		// principalからユーザーの情報を受け取るための操作
		OrderItem orderItem = new OrderItem();
		BeanUtils.copyProperties(form, orderItem);
		User user = chkUser(principal, cookie);
		service.insertCart(user, orderItem);
		return "redirect:/cinemaShop/view";
	}

	/**
	 * カート内の商品一覧を表示
	 * 
	 * @param principal
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/view")
	public String viewCart(Principal principal, @CookieValue("JSESSIONID") String cookie, Model model) {
		// principalからユーザーの情報を受け取るための操作
		User user = chkUser(principal, cookie);
		CartListPage cartPage = service.findAllCart(user);
		
		if (cartPage.getCartListChildPage() == null) {
			model.addAttribute("flag", false);
			return "viewShoppingCart";
		} else {
			model.addAttribute("flag", true);
			model.addAttribute("cartPage", cartPage);
			return "viewShoppingCart";
		}
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
					service.joinCart(user, guestId);
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
		long guestId = -Long.parseLong(jsessionId.substring(digit), 16);
		return guestId;
	}

	/**
	 * カート内の商品を削除
	 * 
	 * @param orderCinemaId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public String deleteCart(@RequestParam long orderCinemaId, Model model) {
		service.deleteCart(orderCinemaId);
		return "redirect:/cinemaShop/view";
	}

}
