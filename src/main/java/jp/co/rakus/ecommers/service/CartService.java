package jp.co.rakus.ecommers.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommers.domain.Cinema;
import jp.co.rakus.ecommers.domain.Order;
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
	 * @param principal
	 * @param form
	 */
//	@SuppressWarnings("null")
	public void insertCart(long id, InsertForm form) {
		
		java.util.Date utilDate = new java.util.Date();
		
		
		Order order = repository.searchOrder(id);
		
		if(order == null){
		
		order.setOrderNumber("00000000000000");
		order.setStatus(0);
		order.setTotalPrice(0);
		
		long utilMillisecond = utilDate.getTime();
		java.sql.Date sqlDate = new java.sql.Date(utilMillisecond);
		
		order.setDate(sqlDate);
		}
		
		
		
		List<Cart> orderList= repository.findAllOrder(order);
		
		for(Cart cart : orderList){
			if(cart.getCinemaId() == form.getCinemaId()){
				cart.setQuantity(cart.getQuantity() + form.getQuantity());
				break;
			}
		}
		
		if(repository.findAllOrder(order, form.getCinemaId()) == null){
			repository.insertOrderItem(form, order.getId());
		}
		
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
	 * @param principal
	 * @return　page情報
	 */
	public CartListPage findAllCart(long id) {
		CartListPage page = new CartListPage();
		List<CartListChildPage> init = new ArrayList<>();
		page.setCartListChildPage(init);
		Order order = repository.searchOrder(id);
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
