package jp.co.rakus.ecommers.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ecommers.service.CartService;

/**
 * カートを操作するコントローラ.
 * 
 * @author takeshi.fujimoto
 *
 */
@Controller
@Transactional
@RequestMapping(value = "/shop")
public class CartController {

	@Autowired
	private CartService service;
	
	/**
	 * カートに商品を追加し、ページに現在カートに入っている商品一覧を格納し、フォワード
	 * 
	 * @param principal
	 * @param form
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/insert")
	public String insertCart(Principal principal,InsertForm form, Model model){
		service.insertCart(principal, form);
		return "redirect:/shop/view";
	}
	
	@RequestMapping(value = "/view")
	public String viewCart(Principal principal, Model model){
		CartListPage cartPage = service.findAllCart(principal);
		model.addAttribute("cartPage", cartPage);
		return "viewShoppingCart";
	}
	
	@RequestMapping(value = "/delete")
	public String deleteCart(CinemaForm form, Model model){
		service.deleteCart(form);
		return "redirect:/shop/cart";
	}
	
}
