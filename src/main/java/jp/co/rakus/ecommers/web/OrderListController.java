package jp.co.rakus.ecommers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ecommers.service.OrderListService;

/**
 * 注文詳細一覧を表示するためのControllerクラス.
 * @author yusuke.nakano
 *
 */
@Controller
@Transactional
@RequestMapping(value = "/admin")
public class OrderListController {
	
	/** OrderListServiceを利用するためのDI */
	@Autowired
	private OrderListService service;
		
	/**
	 * 注文一覧ページを表示する
	 * @param model requestスコープを扱うための変数
	 * @return orderList.jspへフォワード
	 */
	@RequestMapping("/orderList")
	public String output(Model model) {
		
		OrderListPage page = service.findAllOfOrderList();
		System.out.println("err");
		if (!page.getCinemaList().isEmpty()) {
			model.addAttribute("page", page);
			model.addAttribute("flag", true);
			return "orderList";
		} else {
			System.out.println("eles");
			model.addAttribute("flag", false);
			return "orderList";
		}
		
	}
}