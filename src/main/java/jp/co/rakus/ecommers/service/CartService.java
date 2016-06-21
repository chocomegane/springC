package jp.co.rakus.ecommers.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommers.domain.Cinema;
import jp.co.rakus.ecommers.domain.Order;
import jp.co.rakus.ecommers.domain.OrderItem;
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
	
	public Order searchOrder(InsertForm form) {
		return repository.searchOrder(form);
	}
	
	public void insertCart(InsertForm form) {
		if(repository.searchOrder(form) == null){
		Calendar cal = Calendar.getInstance();
		
//		order.setOrderNumber(orderNumber);
		order.setStatus(0);
		order.setTotalPrice(0);
		order.setDate((Timestamp)cal.getTime());
		}
		
		repository.insertOrderItem(form);
		
		List<Cart> orderList = new ArrayList<>();
		orderList= repository.findAllOrder(order);
		
		int sum = 0;
		for(Cart cart : orderList){
			Cinema cinema = repository.findOne(cart.getCinemaId());
			sum = sum + cinema.getPrice() * cart.getQuantity();
		}
		
		order.setTotalPrice(sum);
		
		repository.updateOrder(order);
		
	}
	
}
