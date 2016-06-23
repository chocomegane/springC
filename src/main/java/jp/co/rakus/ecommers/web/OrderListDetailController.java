package jp.co.rakus.ecommers.web;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.rakus.ecommers.domain.Cinema;
import jp.co.rakus.ecommers.domain.Order;
import jp.co.rakus.ecommers.domain.OrderItem;
import jp.co.rakus.ecommers.domain.User;
import jp.co.rakus.ecommers.service.CinemaListService;
import jp.co.rakus.ecommers.service.OrderItemService;
import jp.co.rakus.ecommers.service.OrderListService2nd;
import jp.co.rakus.ecommers.service.UserService;

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
	private OrderListService2nd service;
	
	@Autowired
	private UserService service2;
	
	@Autowired
	private OrderItemService service3;
	
	@Autowired
	private CinemaListService service4;
	
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
		/*************************************************************/
		// 購入者の住所 (id=注文番号'00001')
		Order order = service.findByOrderNumber(orderNumber);
		User user = service2.findById(order.getUserId());
		model.addAttribute("orderNumber", orderNumber);
		model.addAttribute("user", user);
		/*************************************************************/
		// 購入商品詳細(ホントはChildPageに入れないといけない)
		OrderItem item = service3.findById(order.getId());
		Cinema cinema = service4.findById(item.getCinemaId());
		model.addAttribute("cinema", cinema);	// ホンマはリスト
		model.addAttribute("item", item);		// ホンマはリスト
		model.addAttribute("totalPrice", cinema.getPrice()*item.getQuantity()); // この処理はホントはServiceでやらんといけん
		/*************************************************************/
		// 振込情報,　合計金額とか！
		model.addAttribute("tax", cinema.getPrice()*0.08);
		model.addAttribute("sum", cinema.getPrice()*1.08+500); // マジックナンバー！（送料一律＝500円）
		/*************************************************************/
		// selectボタンのステータスを格納する
		model.addAttribute("order", order);
		Map<Integer, String> statusMap = new LinkedHashMap<>();
		statusMap.put(1, "入金済み");
		statusMap.put(2, "未入金");
		statusMap.put(3, "発送済み");
		statusMap.put(4, "キャンセル");
		model.addAttribute("statusMap", statusMap);
		/*************************************************************/
		return "orderListDetail";
	}
	
	@RequestMapping(value="/statusUpdate", method=RequestMethod.POST)
	public String update(OrderForm form, Model model) {
		Order order = service.findByOrderNumber(form.getOrderNumber());
		service.statusUpdate(form.getStatus(), order.getOrderNumber());
		model.addAttribute("message","更新が完了いたしました");
		return list(order.getOrderNumber(),model);
	}
}
