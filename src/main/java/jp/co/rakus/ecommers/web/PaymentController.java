package jp.co.rakus.ecommers.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
public class PaymentController {
	@RequestMapping("/mekePayment")
	public String makePayment(Model model) {
		return null;
	}
}
