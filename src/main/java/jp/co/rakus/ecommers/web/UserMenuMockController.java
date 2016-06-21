package jp.co.rakus.ecommers.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserMenuMockController {
	@RequestMapping("/")
	public String index(Model model){
		return "redirect:/userCinemaList";
	}
	@RequestMapping("/detail")
	public String detail(Model model){
		return "redirect:/CinemaShop/detail/";
	}
}
