package jp.co.rakus.ecommers.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.rakus.ecommers.domain.Cinema;
import jp.co.rakus.ecommers.domain.Order;
import jp.co.rakus.ecommers.domain.OrderItem;
import jp.co.rakus.ecommers.domain.User;
import jp.co.rakus.ecommers.domain.Cart;
import jp.co.rakus.ecommers.web.InsertForm;

/**
 * カートについての処理を行うRepositoryクラス
 * 
 * @author takeshi.fujimoto
 *
 */
@Repository
public class OrderCinemaRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<Order> orderRowMapper = (rs, i) -> {
		Long id = rs.getLong("id");
		String orderNumber = rs.getString("order_number");
		Long userId = rs.getLong("user_id");
		Integer status = rs.getInt("status");
		Integer totalPrice = rs.getInt("total_price");
		Date date = rs.getDate("date");
		return new Order(id, orderNumber, userId, status, null,  totalPrice, date);
	}; 
	
	private static final RowMapper<OrderItem> orderItemListRowMapper = (rs, i) -> {
		Long id = rs.getLong("id");
		long cinemaId = rs.getLong("cinema_id");
		long orderId = rs.getLong("order_id");
		Integer quantity = rs.getInt("quantity");
		return new OrderItem(id, cinemaId, orderId, quantity);
	}; 
	
	private static final RowMapper<Cart> orderListRowMapper = (rs, i) -> {
		Long id = rs.getLong("id");
		String orderNumber = rs.getString("order_number");
		Long userId = rs.getLong("user_id");
		Integer status = rs.getInt("status");
		Integer totalPrice = rs.getInt("total_price");
		Date date = rs.getDate("date");
		Long orderCinemaId = rs.getLong("id");
		long cinemaId = rs.getLong("cinema_id");
		Integer quantity = rs.getInt("quantity");
		return new Cart(id, orderNumber, userId, status, totalPrice, date, orderCinemaId, cinemaId, quantity);
	}; 
	
	
	
	/**
	 * ユーザーのオーダー情報が存在するか全件検索.
	 *
	 * @param form
	 * @return
	 */
	public Order searchOrder(User user) {
		Order order = new Order();
		
		try{
		String sql = "SELECT id, order_number, user_id, status, total_price, date From orders WHERE user_id=:user_id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("user_id", user.getId());
		order = template.queryForObject(sql, param, orderRowMapper);
		}catch(EmptyResultDataAccessException e){
			order = null;
			return order;
		}
		return order;
	}
	
	/**
	 * ユーザーのオーダー情報を新たに追加.
	 * statusには未購入(0),totalPriceには0円,dateにはカートに映画を入れた時の日時を追加.
	 * 
	 * @param order
	 */
	public void insertOrder(User user, Date date) {
		String sql ="INSERT INTO orders(order_number, user_id, status, total_price, date)" + " VALUES(:order_number, :user_id, :status, :total_price, :date)";
		SqlParameterSource param = new MapSqlParameterSource().addValue("order_number", "20160623123456").addValue("user_id", user.getId()).addValue("status", 1).addValue("total_price", 0).addValue("date", date);
		template.update(sql, param);
	}
	
	/**
	 * カートに入れる映画のcinemaId,quantity,userIdをINSERT.
	 * 
	 * 
	 * @param form
	 */
	public void insertOrderItem(InsertForm form, Order order){
		String sql = "INSERT INTO order_items (cinema_id, order_id, quantity)" + " VALUES(:cinema_id, :order_id, :quantity)";
		SqlParameterSource param = new MapSqlParameterSource().addValue("cinema_id", form.getCinemaId()).addValue("order_id", order.getId()).addValue("quantity", form.getQuantity());
		template.update(sql, param);
	}
	
	/**
	 * カートにある映画を全件検索.
	 * 
	 * @param cinemaId
	 * @return
	 */
	public List<OrderItem> findAllOrderItem(Order order) {
		
		String sql = "SELECT id, cinema_id, order_id, quantity";
		SqlParameterSource param = new MapSqlParameterSource().addValue("user_id", order.getId());
		List<OrderItem> orderItemList = template.query(sql, param, orderItemListRowMapper);
		return orderItemList;
	}
	
	/**
	 * 	カート内の商品を検索.
	 * 
	 * @param order
	 * @param cinemaId
	 * @return
	 */
	public List<Cart> findAllOrder(Order order, long cinemaId) {
		List<Cart> orderList = new ArrayList<>();
		try{
		String sql = "SELECT o.id, o.order_number, o.user_id, o.status, o.total_price, o.date, i.id, i.cinema_id, i.quantity FROM orders AS o INNER JOIN order_items AS i ON o.id = i.order_id WHERE o.status = 1 AND o.user_id = :user_id AND i.cinema_id=cinema_id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("user_id", order.getUserId()).addValue("cinema_id", cinemaId);
		orderList = template.query(sql, param, orderListRowMapper);
		}catch(EmptyResultDataAccessException e){
			return null;
		}
		return orderList;
	}
	
	public void updateOrderItem(Cart cart) {
		String sql = "UPDATE order_items SET quantity=:quantity";
		SqlParameterSource param = new MapSqlParameterSource().addValue("quantity", cart.getQuantity());
		template.update(sql, param);
	}
	
	/**
	 * ユーザーのオーダー情報を更新.
	 * 
	 * @param order
	 */
	public void updateOrder(Order order) {
		String sql = "UPDATE orders SET total_price=:total_price, date=:date";
		SqlParameterSource param = new MapSqlParameterSource().addValue("total_price", order.getTotalPrice()).addValue("date", order.getDate());
		template.update(sql, param);
	}
	
	/**
	 * 商品の削除.
	 * 
	 * @param orderCinemaId
	 */
	public void deleteByCinemaId(long orderCinemaId) {
		String sql = "DELETE FROM order_items WHERE cinema_id=:cinema_id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("cinema_id", orderCinemaId);
		template.update(sql, param);
	}
	
}
