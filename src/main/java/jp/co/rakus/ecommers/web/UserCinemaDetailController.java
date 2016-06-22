package jp.co.rakus.ecommers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ecommers.service.OrderListService;

/**
 * 商品の詳細情報を表示するクラス.
 * 
 * @author takeshi.fujimoto
 *
 */

@Controller
@Transactional
@RequestMapping(value = "/user/cinemaDetail")
public class UserCinemaDetailController {
	
	@Autowired
	private OrderListService service;
	
	@ModelAttribute
	public CartForm setUpForm() {
		return new CartForm();
	}
	
//	@RequestMapping(value = "/")
//	public String index(Model model){
//		return "userCinemaList";
//	}
	
	/**
	 * 商品の詳細表示を行う.
	 * 商品一覧からリンクのIDを受け取り、それをもとに商品の検索を行う
	 * 受け取った結果をCinemaDetailPageに格納し、それをリクエストスコープに入れる
	 * 
	 * @param model
	 * @return　フォワード
	 */

	@RequestMapping(value = "detail/{id}")
	public String detail(@PathVariable("id") long id,Model model){
		CinemaDetailPage page = service.copyCinemaToPage(service.findOne(id));
		model.addAttribute("cinemaDetail", page);
		return "userCinemaDetail";
	}

}
