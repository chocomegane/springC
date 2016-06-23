package jp.co.rakus.ecommers.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommers.domain.Cinema;
import jp.co.rakus.ecommers.domain.Order;
import jp.co.rakus.ecommers.domain.User;
import jp.co.rakus.ecommers.domain.Cart;
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
	private OrderCinemaRepository repository;
	/**
	 * カートに商品を追加するメソッド.
	 * 
	 * @param id userID
	 * @param form
	 */
	public void insertCart(User user, InsertForm form) {
		
		java.util.Date utilDate = new java.util.Date();
		
		
		Order order = repository.searchOrder(user);

		if(order == null){
		order = new Order();

		long utilMillisecond = utilDate.getTime();
		java.sql.Date sqlDate = new java.sql.Date(utilMillisecond);
		
		order.setDate(sqlDate);

		repository.insertOrder(user, sqlDate);
		}
		
		List<Cart> orderList= repository.findAllOrder(order);
		
//		if(repository.findAllOrder(order, form.getCinemaId()) == null){
			repository.insertOrderItem(form, order);
//		}
		
//		for(Cart cart : orderList){
//			if(cart.getCinemaId() == form.getCinemaId()){
//				cart.setQuantity(cart.getQuantity() + form.getQuantity());
//				break;
//			}
//		}
		
		int sum = 0;
		for(Cart cart : orderList){
			Cinema cinema = repository.findOne(cart.getCinemaId());
			sum = sum + cinema.getPrice() * cart.getQuantity();
		}
		
		order.setTotalPrice(sum);
		
		long utilMillisecond = utilDate.getTime();
		java.sql.Date sqlDate = new java.sql.Date(utilMillisecond);
		order.setDate(sqlDate);
		
		repository.updateOrder(order);
		
	}
	
	/**
	 * カート内の商品一覧表示
	 * 
	 * @param id userId
	 * @return　page情報
	 */
	public CartListPage findAllCart(User user) {
		CartListPage page = new CartListPage();
		List<CartListChildPage> init = new ArrayList<>();
		page.setCartListChildPage(init);
		Order order = repository.searchOrder(user);
		
		List<Cart> cartList = repository.findAllOrder(order);
		if(cartList == null){
			return null;
		}
		for(Cart cart : cartList){
			CartListChildPage childPage = new CartListChildPage();
			Cinema cinema = repository.findOne(cart.getCinemaId());
			childPage.setOrderCinemaId(cart.getCinemaId());
			childPage.setTitle(cinema.getTitle());
			childPage.setPrice(cinema.getPrice());
			childPage.setQuantity(cart.getQuantity());
			
			page.getCartListChildPage().add(childPage);
		}
		return page;
	}
	
	public void deleteCart(long orderCinemaId) {
		repository.deleteByCinemaId(orderCinemaId);
	}
	
}
