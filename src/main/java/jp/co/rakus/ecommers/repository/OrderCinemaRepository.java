package jp.co.rakus.ecommers.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.rakus.ecommers.domain.Cart;
import jp.co.rakus.ecommers.domain.Cinema;
import jp.co.rakus.ecommers.domain.Order;
import jp.co.rakus.ecommers.domain.OrderItem;
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
		return new Cinema(id, title, price, genre, time, releaseDate, mediaType, company, directedBy, rating,
				description, imagePath, deleted);
	};

	private static final RowMapper<Cart> orderListRowMapper = (rs, i) -> {
		Long id = rs.getLong("id");
		String orderNumber = rs.getString("order_number");
		Long userId = rs.getLong("user_id");
		Integer status = rs.getInt("status");
		Integer totalPrice = rs.getInt("total_price");
		Date date = rs.getTimestamp("date");
		long orderCinemaId = rs.getLong("id");
		long cinemaId = rs.getLong("cinema_id");
		Integer quantity = rs.getInt("quantity");
		return new Cart(id, orderNumber, userId, status, totalPrice, date, orderCinemaId, cinemaId, quantity);
	};

	/**
	 * ResultSetからDTOを生成するためのオブジェクトを提供する内部クラス.<br>
	 * 
	 * @author kohei.sakata
	 */
	class OrderRowCallbackHandler implements RowCallbackHandler {
		/** 結果の記事DTOを格納するリスト */
		private List<Order> orders = new ArrayList<Order>();
		/** 構築中の記事DTO */
		private OrderItem currentItem = null;
		/** 現在渡されている行に対応するコメントのDTO */
		private Order currentOrder = null;

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.springframework.jdbc.core.RowCallbackHandler#processRow(java.sql.
		 * ResultSet) 渡された行を見て記事DTOを構築するメソッド
		 */
		public void processRow(ResultSet rs) throws SQLException {
			// 列名確認
			// ResultSetMetaData rsmd= rs.getMetaData();
			// for (int i = 1; i <= rsmd.getColumnCount(); i++) {
			// System.out.println(rsmd.getColumnName(i));
			// }
			int orderId = rs.getInt("o_id");
			if (currentOrder == null || orderId != currentOrder.getId()) {
				currentOrder = new Order();
				currentItem = null;
				orders.add(currentOrder);
				currentOrder.setId(orderId);
				currentOrder.setOrderNumber(rs.getString("order_number"));
				currentOrder.setUserId(rs.getInt("user_id"));
				currentOrder.setStatus(rs.getInt("status"));
				currentOrder.setTotalPrice(rs.getInt("total_price"));
				currentOrder.setDate(rs.getTimestamp("date"));
				currentItem = null;
			}
			int itemId = rs.getInt("i_id");
			if (!rs.wasNull()) {
				if (currentOrder.getOrderCinemaList() == null) {
					currentOrder.setOrderCinemaList(new ArrayList<OrderItem>());
				}
				currentItem = new OrderItem();
				currentItem.setId(itemId);
				currentItem.setOrderId(orderId);
				currentItem.setCinemaId(rs.getInt("cinema_id"));
				currentItem.setQuantity(rs.getInt("quantity"));
				currentOrder.getOrderCinemaList().add(currentItem);
			}
		}

		/** 結果を取り出すためのgetter */
		public List<Order> getOrders() {
			return orders;
		}
	}

	/**
	 * ユーザーのオーダー情報が存在するか全件検索.
	 *
	 * @param form
	 * @return
	 */
	public Order searchOrder(long id) {
		OrderRowCallbackHandler orderRowCallbackHandler = new OrderRowCallbackHandler();
		Order order = null;
		try{
		String sql = "SELECT o.id AS o_id, o.order_number, o.user_id, o.status, o.total_price, o.date,"
				+ " i.id AS i_id, i.cinema_id, i,quantity"
				+ " From orders AS o"
				+ " LEFT OUTER JOIN order_items AS i"
				+ " ON o.id = i.order_id"
				+ " WHERE o.user_id=:user_id"
				+ " ORDER BY i.cinema_id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("user_id", id);
		template.query(sql, param, orderRowCallbackHandler);
		order = orderRowCallbackHandler.getOrders().get(0);
		}catch(EmptyResultDataAccessException e){
			return order;
		}
		return order;
	}

	/**
	 * ユーザーのオーダー情報を新たに追加. statusには未購入(0),totalPriceには0円,dateにはカートに映画を入れた時の日時を追加.
	 * 
	 * @param order
	 */
	public void insertOrder(Order order) {
		String sql = "INSERT INTO orders(order_number, user_id, status, total_price, date)"
				+ " VALUES(:order_number, :user_id, :status, :total_price, :date)";
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);
		template.update(sql, param);
	}

	/**
	 * カートに入れる映画のcinemaId,quantity,userIdをINSERT.
	 * 
	 * 
	 * @param form
	 */
	public void insertOrderItem(InsertForm form, long orderId) {

		String sql = "INSERT INTO order_items (cinema_id, quantity, order_id)"
				+ " VALUES(:cinema_id, :quantity, :order_id)";
		SqlParameterSource param = new MapSqlParameterSource().addValue("cinema_id", form.getCinemaId())
				.addValue("quantity", form.getQuantity()).addValue("order_id", orderId);
		template.update(sql, param);
	}

	/**
	 * カートにある映画を全件検索.
	 * 
	 * @param cinemaId
	 * @return
	 */
	public List<Cart> findAllOrder(Order order) {
		String sql = "SELECT o.id, o.order_number, o.user_id, o.status, o.total_price, o.date, i.id, i.cinema_id, i.quantity FROM orders AS o INNER JOIN order_items AS i ON o.id = i.order_id WHERE o.status = 0 AND o.user_id = :user_id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("user_id", order.getUserId());
		List<Cart> orderList = template.query(sql, param, orderListRowMapper);
		return orderList;
	}

	public List<Cart> findAllOrder(Order order, long cinemaId) {
		List<Cart> orderList = new ArrayList<>();
		try {
			String sql = "SELECT o.id, o.order_number, o.user_id, o.status, o.total_price, o.date, i.id, i.cinema_id, i.quantity FROM orders AS o INNER JOIN order_items AS i ON o.id = i.order_id WHERE o.status = 0 AND o.user_id = :user_id AND i.cinema_id=cinema_id";
			SqlParameterSource param = new MapSqlParameterSource().addValue("user_id", order.getUserId())
					.addValue("cinema_id", cinemaId);
			orderList = template.query(sql, param, orderListRowMapper);
		} catch (EmptyResultDataAccessException e) {
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
		SqlParameterSource param = new MapSqlParameterSource().addValue("total_price", order.getTotalPrice())
				.addValue("date", order.getDate());
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

	/**
	 * 注文情報のステータスを1(未入金）、日付に現在の日時へ更新するメソッド
	 * 
	 * @param id
	 *            注文情報のid
	 * @return 成功したらtrueを失敗したらfalseを返す
	 */
	public Boolean updateStatus(Long id) {
		try {
			String sql = "UPDATE orders SET status=1, date=cast(now() as date) WHERE id=:id ;";
			SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
			template.update(sql, param);
			return true;
		} catch (Exception ex) {
			// ex.printStackTrace();
			return false;
		}
	}
}
