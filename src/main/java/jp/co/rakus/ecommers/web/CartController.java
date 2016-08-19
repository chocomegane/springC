package jp.co.rakus.ecommers.web;

import java.security.Principal;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.rakus.ecommers.domain.Cinema;
import jp.co.rakus.ecommers.domain.OrderItem;
import jp.co.rakus.ecommers.domain.User;
import jp.co.rakus.ecommers.service.CartService;
import jp.co.rakus.ecommers.service.OrderListService;
import jp.co.rakus.ecommers.service.UserService;

/**
 * カートを操作するコントローラ.
 * 
 * @author takeshi.fujimoto
 *
 */
@Controller
@Transactional
@RequestMapping(value = "/cart")
public class CartController {
	@Autowired
	private UserService userService;
	@Autowired
	private CartService service;
	@Autowired
	private OrderListService orderListService;

	/**
	 * カートに商品を追加
	 * 
	 * @param principal
	 * @param form
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/insert")
	public String insertCart(Principal principal, @CookieValue("JSESSIONID") String cookie, @Validated CartForm form,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			Cinema cinema = orderListService.findOne(form.getCinemaId());
			model.addAttribute("cinemaDetail", cinema);
			return "userCinemaDetail";
		}
		// principalからユーザーの情報を受け取るための操作
		OrderItem orderItem = new OrderItem();
		BeanUtils.copyProperties(form, orderItem);
		if (orderItem.getQuantity() == null) {
			orderItem.setQuantity(Integer.parseInt(form.getQuantity()));
		}
		User user = userService.chkUser(principal, cookie);
		service.insertCart(user, orderItem);
		return "redirect:/cart/view";
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
		User user = userService.chkUser(principal, cookie);
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
	 * カート内の商品を削除
	 * 
	 * @param orderCinemaId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public String deleteCart(@RequestParam long orderCinemaId, Model model) {
		service.deleteCart(orderCinemaId);
		return "redirect:/cart/view";
	}

}
