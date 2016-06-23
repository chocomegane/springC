package jp.co.rakus.ecommers.web;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
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
		// (orderNumber=注文番号'00001') 購入者の住所
		Order order = service.findByOrderNumber(orderNumber);
		User user = service2.findById(order.getUserId());
		
		// 購入商品詳細
		List<OrderItem> itemList = service3.findById(order.getId());
		List<Cinema> cinemaList = new LinkedList<>();
		for(OrderItem item : itemList) {
			 Cinema cinema = service4.findById(item.getCinemaId());
			 cinemaList.add(cinema);
		}
		
		OrderListDetailPage page = new OrderListDetailPage();
		List<OrderListDetailChildPage> init = new ArrayList<>();
		page.setChildPage(init);
		
		// メソッド化
			page.setOrderNumber(order.getOrderNumber());
			page.setUserName(user.getName());
			page.setEmail(user.getEmail());
			page.setAddress(user.getAddress());
			page.setTelephone(user.getTelephone());
			for(int i = 0; i < itemList.size(); i++) {
				OrderListDetailChildPage childPage = new OrderListDetailChildPage();
				childPage.setTitle(cinemaList.get(i).getTitle());
				childPage.setPrice(cinemaList.get(i).getPrice());
				childPage.setQuantity(itemList.get(i).getQuantity());
				childPage.setTotal(cinemaList.get(i).getPrice() * itemList.get(i).getQuantity());
				page.getChildPage().add(childPage);
			}
			Integer total = 0;
			Integer tax = 0;
			for(int j = 0; j < page.getChildPage().size(); j++) {
				total += page.getChildPage().get(j).getTotal();
			}
			page.setSubTotal(total);
			tax = (int)(total * 0.08);
			page.setTax(tax);
			page.setGrandTotal(total+tax+500);
			Map<Integer, String> statusMap = new LinkedHashMap<>();
			statusMap.put(1, "入金済み");
			statusMap.put(2, "未入金");
			statusMap.put(3, "発送済み");
			statusMap.put(4, "キャンセル");
			page.setStatusMap(statusMap);
			switch (order.getStatus()) {
			case 1:
				page.setStatus("入金済み");
				break;
			case 2:
				page.setStatus("未入金");
				break;
			case 3:
				page.setStatus("発送済み");
				break;
			case 4:
				page.setStatus("キャンセル");
				break;
			}
			model.addAttribute("page", page);
		
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
