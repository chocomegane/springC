package jp.co.rakus.ecommers.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommers.domain.Cinema;
import jp.co.rakus.ecommers.domain.LoginUser;
import jp.co.rakus.ecommers.domain.Order;
import jp.co.rakus.ecommers.domain.Cart;
import jp.co.rakus.ecommers.repository.OrderCinemaRepository;
import jp.co.rakus.ecommers.web.CartListChildPage;
import jp.co.rakus.ecommers.web.CartListPage;
import jp.co.rakus.ecommers.web.DeleteForm;
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
	public void insertCart(Principal principal, InsertForm form) {
		
		java.util.Date utilDate = new java.util.Date();
		
		
//		LoginUser loginUser = (LoginUser)principal;
		Order order = repository.searchOrder(/*loginUser.getUser().getId()*/1);
		
		if(order == null){
		
		order.setOrderNumber("00000000000000");
		order.setStatus(0);
		order.setTotalPrice(0);
		
		long utilMillisecond = utilDate.getTime();
		java.sql.Date sqlDate = new java.sql.Date(utilMillisecond);
		
		order.setDate(sqlDate);
		}
		
		repository.insertOrderItem(form, order.getId());
		
		List<Cart> orderList= repository.findAllOrder(order);
		
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
	public CartListPage findAllCart(Principal principal) {
		CartListPage page = new CartListPage();
		List<CartListChildPage> init = new ArrayList<>();
		page.setCartListChildPage(init);
//		LoginUser loginUser = (LoginUser)principal;
		Order order = repository.searchOrder(/*loginUser.getUser().getId()*/1);
		List<Cart> cartList = repository.findAllOrder(order);
		if(cartList == null){
			return null;
		}
		for(Cart cart : cartList){
			CartListChildPage childPage = new CartListChildPage();
			Cinema cinema = repository.findOne(cart.getCinemaId());
			childPage.setItemId(cinema.getId());
			childPage.setTitle(cinema.getTitle());
			childPage.setPrice(cinema.getPrice());
			childPage.setQuantity(cart.getQuantity());
			
			page.getCartListChildPage().add(childPage);
		}
		return page;
	}
	
	public void deleteCart(DeleteForm form) {
		repository.deleteByCinemaId(form);
	}
	
}
