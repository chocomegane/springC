package jp.co.rakus.ecommers.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommers.domain.Cinema;
import jp.co.rakus.ecommers.domain.Order;
import jp.co.rakus.ecommers.domain.OrderItem;
import jp.co.rakus.ecommers.domain.User;
import jp.co.rakus.ecommers.domain.Cart;
import jp.co.rakus.ecommers.repository.CinemaRepository;
import jp.co.rakus.ecommers.repository.OrderCinemaRepository;
import jp.co.rakus.ecommers.web.CartListChildPage;
import jp.co.rakus.ecommers.web.CartListPage;
import jp.co.rakus.ecommers.web.InsertForm;

/**
 * カートの操作をするServiceクラス.
 * 
 * @author takeshi.fujimoto
 *
 */

@Service
public class CartService {

	@Autowired
	private OrderCinemaRepository orderCinemaRepository;

	@Autowired
	private CinemaRepository cinemaRepository;

	/**
	 * カートに商品を追加するメソッド.
	 * 
	 * @param id
	 *            userID
	 * @param form
	 */
	public void insertCart(User user, InsertForm form) {

		java.util.Date utilDate = new java.util.Date();

		Order order = orderCinemaRepository.searchOrder(user);

		if (order == null) {
			order = new Order();

			long utilMillisecond = utilDate.getTime();
			java.sql.Date sqlDate = new java.sql.Date(utilMillisecond);

			order.setDate(sqlDate);

			orderCinemaRepository.insertOrder(user, sqlDate);
		}

			orderCinemaRepository.saveOrderItem(form, order);

		List<Cart> orderItemList = orderCinemaRepository.findAllOrder(order, form.getCinemaId());

		order.setTotalPrice(sumPrice(orderItemList));

		long utilMillisecond = utilDate.getTime();
		java.sql.Date sqlDate = new java.sql.Date(utilMillisecond);
		order.setDate(sqlDate);

		orderCinemaRepository.updateOrder(order);

	}

	/**
	 * カート内の商品一覧表示.
	 * 
	 * @param id
	 *            userId
	 * @return page情報
	 */
	public CartListPage findAllCart(User user) {
		CartListPage page = new CartListPage();
		List<CartListChildPage> init = new ArrayList<>();
		page.setCartListChildPage(init);
		Order order = orderCinemaRepository.searchOrder(user);

		order.setOrderCinemaList(orderCinemaRepository.findAllOrderItem(order));

		int totalPrice = 0;
		for (OrderItem orderItem : order.getOrderCinemaList()) {
			Cinema cinema = cinemaRepository.findOne(orderItem.getCinemaId());
			totalPrice = totalPrice + cinema.getPrice() * orderItem.getQuantity();
		}

		order.setTotalPrice(totalPrice);
		
		if (order.getOrderCinemaList() == null) {
			page.getCartListChildPage().add(null);
			return page;
		}
		for (OrderItem orderItems : order.getOrderCinemaList()) {
			CartListChildPage childPage = new CartListChildPage();
			Cinema cinema = cinemaRepository.findOne(orderItems.getCinemaId());
			childPage.setOrderCinemaId(orderItems.getCinemaId());
			childPage.setTitle(cinema.getTitle());
			childPage.setPrice(cinema.getPrice());
			childPage.setQuantity(orderItems.getQuantity());

			page.getCartListChildPage().add(childPage);
		}
		return page;
	}

	/**
	 * 削除を行うRepogitoryを呼び出す.
	 * 
	 * @param orderCinemaId
	 */
	public void deleteCart(long orderCinemaId) {
		orderCinemaRepository.deleteByCinemaId(orderCinemaId);
	}
	
	public int sumPrice(List<Cart> orderItemList) {
		int sum = 0;
		for (Cart cart : orderItemList) {
			Cinema cinema = cinemaRepository.findOne(cart.getCinemaId());
			sum = sum + cinema.getPrice() * cart.getQuantity();
		}
		return sum;
	}

}
