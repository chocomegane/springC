package jp.co.rakus.ecommers.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommers.domain.Cinema;
import jp.co.rakus.ecommers.domain.Order;
import jp.co.rakus.ecommers.domain.OrderItem;
import jp.co.rakus.ecommers.domain.User;
import jp.co.rakus.ecommers.web.OrderListDetailChildPage;
import jp.co.rakus.ecommers.web.OrderListDetailPage;

/**
 * OrderListDetailPageオブジェクトを作成するためのService.
 * 注)私のServiceクラスの実装の仕方が悪かったので、このServiceクラスから他のServiceクラスを呼んでます
 * 
 * @author yusuke.nakano
 *
 */
@Service
public class CreatePageService {

	@Autowired
	private OrderListService service1;

	@Autowired
	private UserService service2;

	@Autowired
	private OrderItemService service3;

	@Autowired
	private CinemaListService service4;

	public OrderListDetailPage list(String orderNumber) {

		// (orderNumber=注文番号'00001') 購入者の住所
		Order order = service1.findByOrderNumber(orderNumber);
		User user = service2.findById(order.getUserId());

		System.out.println("1");
		System.out.println(order);
		System.out.println(user);

		// 購入商品詳細
		List<OrderItem> itemList = service3.findById(order.getId());
		List<Cinema> cinemaList = new LinkedList<>();
		for (OrderItem item : itemList) {
			Cinema cinema = service4.findById(item.getCinemaId());
			cinemaList.add(cinema);
		}

		System.out.println("2");
		System.out.println(itemList);
		System.out.println(cinemaList);

		OrderListDetailPage page = new OrderListDetailPage();
		List<OrderListDetailChildPage> init = new ArrayList<>();
		page.setChildPage(init);

		page.setOrderNumber(order.getOrderNumber());
		page.setUserName(user.getName());
		page.setEmail(user.getEmail());
		page.setAddress(user.getAddress());
		page.setTelephone(user.getTelephone());

		System.out.println("3");
		System.out.println(page);

		for (int i = 0; i < itemList.size(); i++) {
			OrderListDetailChildPage childPage = new OrderListDetailChildPage();
			childPage.setTitle(cinemaList.get(i).getTitle());
			childPage.setPrice(cinemaList.get(i).getPrice());
			childPage.setQuantity(itemList.get(i).getQuantity());
			childPage.setTotal(cinemaList.get(i).getPrice() * itemList.get(i).getQuantity());
			page.getChildPage().add(childPage);
		}

		System.out.println("4");

		Integer total = 0;
		Integer tax = 0;
		for (int j = 0; j < page.getChildPage().size(); j++) {
			total += page.getChildPage().get(j).getTotal();
		}
		page.setSubTotal(total);
		tax = (int) (total * 0.08);
		page.setTax(tax);
		page.setGrandTotal(total + tax + 500);

		Map<Integer, String> statusMap = new LinkedHashMap<>();
		statusMap.put(1, "未入金");
		statusMap.put(2, "入金済み");
		statusMap.put(3, "発送済み");
		statusMap.put(4, "キャンセル");

		page.setStatusMap(statusMap);

		switch (order.getStatus()) {
		case 1:
			page.setStatus("未入金");
			break;
		case 2:
			page.setStatus("入金済み");
			break;
		case 3:
			page.setStatus("発送済み");
			break;
		case 4:
			page.setStatus("キャンセル");
			break;
		}

		return page;
	}
}