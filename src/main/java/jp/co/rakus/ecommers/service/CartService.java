package jp.co.rakus.ecommers.service;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommers.domain.Cinema;
import jp.co.rakus.ecommers.domain.LoginUser;
import jp.co.rakus.ecommers.domain.Order;
import jp.co.rakus.ecommers.domain.Cart;
import jp.co.rakus.ecommers.repository.OrderCinemaRepository;
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
	
	@Autowired
	private Order order;
	
	public void insertCart(Principal principal, InsertForm form) {
		LoginUser loginUser = (LoginUser)principal;
		if(repository.searchOrder(loginUser.getUser().getId()) == null){
		Calendar cal = Calendar.getInstance();
		
//		order.setOrderNumber(orderNumber);
		order.setStatus(0);
		order.setTotalPrice(0);
		order.setDate((Timestamp)cal.getTime());
		}
		
		repository.insertOrderItem(form);
		
		List<Cart> orderList= repository.findAllOrder(order);
		
		int sum = 0;
		for(Cart cart : orderList){
			Cinema cinema = repository.findOne(cart.getCinemaId());
			sum = sum + cinema.getPrice() * cart.getQuantity();
		}
		
		order.setTotalPrice(sum);
		
		repository.updateOrder(order);
		
	}
	
	public List<Cart> findAllCart(Principal principal) {
		LoginUser loginUser = (LoginUser)principal;
		Order order = repository.searchOrder(loginUser.getUser().getId());
		List<Cart> cartList = repository.findAllOrder(order);
		return cartList;
	}
	
}
