package jp.co.rakus.ecommers.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ecommers.service.CartService;

@Controller
@Transactional
@RequestMapping(value = "/cart/")
public class CartController {

	@Autowired
	private CartService service;
	
	@RequestMapping(value = "/")
	public String insertCart(Principal principal,InsertForm form, Model model){
		service.insertCart(principal, form);
		return "redirect:/view";
	}
	
	@RequestMapping(value = "/view")
	public String viewCart(Model model){
		
		return "viewShoppingCart";
	}
	
}
