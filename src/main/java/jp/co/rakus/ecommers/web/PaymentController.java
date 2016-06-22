package jp.co.rakus.ecommers.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ecommers.domain.LoginUser;
import jp.co.rakus.ecommers.domain.User;
import jp.co.rakus.ecommers.service.CartService;
import jp.co.rakus.ecommers.service.PaymentService;

@Controller
@Transactional
@RequestMapping(value="/cinemaShop")
public class PaymentController {
	@Autowired
	private CartService cartService;
	
	@RequestMapping("/mekePayment")
	public String makePayment(Principal principal, Model model) {
		LoginUser loginUser = (LoginUser)((Authentication) principal).getPrincipal();
		User user = loginUser.getUser();
		CartListPage cartPage = cartService.findAllCart(user.getId());
		model.addAttribute("cartPage", cartPage);
		return "makePayment";
	}
}
