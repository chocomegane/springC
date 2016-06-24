package jp.co.rakus.ecommers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.rakus.ecommers.domain.Order;
import jp.co.rakus.ecommers.service.CreatePageService;
import jp.co.rakus.ecommers.service.OrderListService;

/**
 * 管理者の画面遷移を行うクラス.
 * 
 * @author tsubasa.kaneko
 *
 */
@Controller
@Transactional
@RequestMapping(value="/admin")
public class OrderListDetailController {

	@Autowired
	private CreatePageService service1;

	@Autowired
	private OrderListService service2;

	@ModelAttribute
	public OrderForm setUpForm() {
		return new OrderForm();
	}
	
	/**
	 * 管理者の商品一覧表示を表示するメソッド.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/orderListDetail")
	public String list(@RequestParam String orderNumber, Model model){
	
		OrderListDetailPage page = service1.list(orderNumber);
			
		model.addAttribute("page", page);
		return "orderListDetail";
	}
	
	@RequestMapping(value="/statusUpdate", method=RequestMethod.POST)
	public String update(OrderForm form, Model model) {
		
		Order order = service2.findByOrderNumber(form.getOrderNumber());
		service2.statusUpdate(form.getStatus(), order.getOrderNumber());
		
		model.addAttribute("message","更新が完了いたしました");
		return list(order.getOrderNumber(), model);
	}
}
