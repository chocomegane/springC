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
		return new Order(id, orderNumber, userId, status, null, totalPrice, date);
	};
	
	private static final RowMapper<Cinema> cinemaRowMapper = (rs, i) -> {
		Long id = rs.getLong("id");
		String title = rs.getString("title");
		Integer price = rs.getInt("price");
		String genre = rs.getString("genre");
		Integer time = rs.getInt("time");
		Date releaseDate = rs.getTimestamp("release_date");
		String mediaType = rs.getString("media_type");
		String company = rs.getString("company");
		String directedBy = rs.getString("directed_by");
		String rating = rs.getString("rating");
		String description = rs.getString("description");
		String imagePath = rs.getString("image_path");
		boolean deleted = rs.getBoolean("deleted"); 
		return new Cinema(id, title, price, genre, time, releaseDate, mediaType, company, directedBy, rating, description, imagePath, deleted);
	};
	
	private static final RowMapper<Cart> orderListRowMapper = (rs, i) -> {
		Long id = rs.getLong("id");
		String orderNumber = rs.getString("order_number");
		Long userId = rs.getLong("user_id");
		Integer status = rs.getInt("status");
		Integer totalPrice = rs.getInt("total_price");
		Date date = rs.getDate("date");
		long orderCinemaId = rs.getLong("id");
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
		
		System.out.println("================================================");
		System.out.println("search");
		System.out.println(user);
		System.out.println("================================================");
		
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
		
		System.out.println("================================================");
		System.out.println("insert");
		System.out.println(order);
		System.out.println("================================================");
		
		
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
	public List<Cart> findAllOrder(Order order) {
		
		System.out.println("================================================");
		System.out.println(order);
		System.out.println("================================================");
		
		String sql = "SELECT o.id, o.order_number, o.user_id, o.status, o.total_price, o.date, i.id, i.cinema_id, i.quantity FROM orders AS o INNER JOIN order_items AS i ON o.id = i.order_id WHERE o.status = 1 AND o.user_id = :user_id";
		
		System.out.println("================================================");
		System.out.println(order);
		System.out.println("================================================");
		
		System.out.println("================================================");
		System.out.println(order.getUserId());
		System.out.println("================================================");
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("user_id", order.getUserId());
		List<Cart> orderList = template.query(sql, param, orderListRowMapper);
		return orderList;
	}
	
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
	
	/**
	 * 映画の詳細検索
	 * 
	 * @param id
	 * @return
	 */
	public Cinema findOne(long cinemaId) {
		String sql = "SELECT id, title, price, genre, time, release_date, media_type, company, directed_by, rating, description, image_path, deleted FROM cinemas WHERE id=:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", cinemaId);
		Cinema cinema = template.queryForObject(sql, param, cinemaRowMapper);
		return cinema;
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
	
	public Order findByOrderNumber(String orderNumber) {
		String sql = "SELECT * FROM orders WHERE order_number = :orderNumber";
		SqlParameterSource param = new MapSqlParameterSource().addValue("orderNumber", orderNumber);
		Order order = template.queryForObject(sql, param, orderRowMapper);
		return order;
	}
	
	public List<Order> findAll() {
		String sql = "SELECT * FROM orders ORDER BY order_number";
		List<Order> orderList = template.query(sql, orderRowMapper);
		return orderList;
	}
	
	public void statusUpdate(Integer status, String orderNumber) {
		String sql = "UPDATE orders SET status = :status WHERE order_number = :orderNumber";
		SqlParameterSource param = new MapSqlParameterSource().addValue("status", status).addValue("orderNumber", orderNumber);
		template.update(sql,param);
	}
	
}
