package jp.co.rakus.ecommers.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.rakus.ecommers.domain.LoginUser;
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
		//principalからユーザーの情報を受け取るための操作
		LoginUser loginUser = (LoginUser)((Authentication) principal).getPrincipal();
		User user = loginUser.getUser();
		service.insertCart(user.getId(), form);
		return "redirect:/shop/view";
	}
	
	@RequestMapping(value = "/view")
	public String viewCart(Principal principal, Model model){
		//principalからユーザーの情報を受け取るための操作
		LoginUser loginUser = (LoginUser)((Authentication) principal).getPrincipal();
		User user = loginUser.getUser();
		
		CartListPage cartPage = service.findAllCart(user.getId());
		model.addAttribute("cartPage", cartPage);
		return "viewShoppingCart";
	}
	
	@RequestMapping(value = "/delete")
	public String deleteCart(@RequestParam long orderCinemaId,Model model){
		System.out.println("==============================================");
		System.out.println(orderCinemaId);
		System.out.println("==============================================");
		service.deleteCart(orderCinemaId);
		return "redirect:/shop/view";
	}
	
}
