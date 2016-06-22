package jp.co.rakus.ecommers.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ecommers.service.CartService;
import jp.co.rakus.ecommers.service.PaymentService;

@Controller
@Transactional
@RequestMapping(value="/cinemaShop")
public class PaymentController {
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private CartService service;
	
	@RequestMapping("/mekePayment")
	public String makePayment(Principal principal, Model model) {
//		CinemaListPage listPage = paymentService.findAll();
		CartListPage cartPage = service.findAllCart(principal);
		model.addAttribute("cartPage", cartPage);
		return "makePayment";
	}
}
