package jp.co.rakus.ecommers.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.If;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.rakus.ecommers.domain.Cart;
import jp.co.rakus.ecommers.domain.Order;
import jp.co.rakus.ecommers.domain.OrderItem;
import jp.co.rakus.ecommers.domain.User;
import jp.co.rakus.ecommers.web.InsertForm;

/**
 * ordersテーブルを操作するRepositoryクラス
 * 
 * @author takeshi.fujimoto
 *
 */
@Repository
public class OrderCinemaRepository {

	private static final int ORDER_STATUS = 0;
	private static final int ORDER_TOTAL_PRICE = 0;

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

	private static final RowMapper<OrderItem> orderItemListRowMapper = (rs, i) -> {
		Long id = rs.getLong("id");
		Long cinemaId = rs.getLong("cinema_id");
		Long orderId = rs.getLong("order_id");
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
		Long cinemaId = rs.getLong("cinema_id");
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
		private List<Order> orders = null;
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
			if (orders == null) {
				orders = new ArrayList<>();
			}
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
	public List<Order> searchOrder(User user) {
		OrderRowCallbackHandler orderRowCallbackHandler = new OrderRowCallbackHandler();
		try {
			String sql = "SELECT o.id AS o_id, o.order_number, o.user_id, o.status, o.total_price, o.date,"
					+ " i.id AS i_id, i.cinema_id, i,quantity" + " From orders AS o"
					+ " LEFT OUTER JOIN order_items AS i" + " ON o.id = i.order_id" + " WHERE o.user_id=:user_id"
					+ " ORDER BY i.cinema_id";
			SqlParameterSource param = new MapSqlParameterSource().addValue("user_id", user.getId());
			template.query(sql, param, orderRowCallbackHandler);
			if (orderRowCallbackHandler.getOrders().isEmpty()) {
				throw new EmptyResultDataAccessException(1);
			} else {
				return orderRowCallbackHandler.getOrders();
			}
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	/**
	 * @param user
	 * @return
	 */
	public Order findCart(User user) {
		OrderRowCallbackHandler orderRowCallbackHandler = new OrderRowCallbackHandler();
		try {
			String sql = "SELECT o.id AS o_id, o.order_number, o.user_id, o.status, o.total_price, o.date,"
					+ " i.id AS i_id, i.cinema_id, i,quantity" + " From orders AS o"
					+ " LEFT OUTER JOIN order_items AS i" + " ON o.id = i.order_id"
					+ " WHERE o.user_id=:user_id AND o.status = 0" + " ORDER BY i.cinema_id";
			SqlParameterSource param = new MapSqlParameterSource().addValue("user_id", user.getId());
			template.query(sql, param, orderRowCallbackHandler);
			if (orderRowCallbackHandler.getOrders() == null) {
				throw new EmptyResultDataAccessException(1);
			} else {
				return orderRowCallbackHandler.getOrders().get(0);
			}
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	/**
	 * ユーザーのオーダー情報を新たに追加. statusには未購入(0),totalPriceには0円,dateにはカートに映画を入れた時の日時を追加.
	 * 
	 * @param user
	 * @param date
	 */
	public void insertOrder(User user, Order order) {

		String sql = "INSERT INTO orders(order_number, user_id, status, total_price, date)"
				+ " VALUES(:order_number, :user_id, :status, :total_price, :date)";
		SqlParameterSource param = new MapSqlParameterSource().addValue("order_number", order.getOrderNumber())
				.addValue("user_id", user.getId()).addValue("status", ORDER_STATUS)
				.addValue("total_price", ORDER_TOTAL_PRICE).addValue("date", order.getDate());

		template.update(sql, param);
	}

	/**
	 * カート内に商品を追加、すでに追加したい商品がある場合は個数を追加.
	 * 
	 * @param form
	 * @param order
	 */

	public void saveOrderItem(OrderItem orderItem, Order order) {
		try {
			String sql = "SELECT quantity FROM order_items WHERE cinema_id = :cinema_id AND order_id = :order_id";
			SqlParameterSource param = new MapSqlParameterSource().addValue("cinema_id", orderItem.getCinemaId())
					.addValue("order_id", order.getId());
			int  existingQuantity = template.queryForObject(sql, param, Integer.class);

			int totalQuantity = existingQuantity + orderItem.getQuantity();
			String updateSql = "UPDATE order_items SET quantity=:quantity WHERE cinema_id = :cinema_id AND order_id = :order_id";
			SqlParameterSource updateParam = new MapSqlParameterSource().addValue("quantity", totalQuantity)
					.addValue("cinema_id", orderItem.getCinemaId()).addValue("order_id", order.getId());
			template.update(updateSql, updateParam);
		} catch (EmptyResultDataAccessException e) {
			String insertSql = "INSERT INTO order_items (cinema_id, order_id, quantity)"
					+ " VALUES(:cinema_id, :order_id, :quantity)";
			SqlParameterSource insertParam = new MapSqlParameterSource().addValue("cinema_id", orderItem.getCinemaId())
					.addValue("order_id", order.getId()).addValue("quantity", orderItem.getQuantity());
			template.update(insertSql, insertParam);
		}
	}

	/**
	 * カートにある映画を全件検索.
	 * 
	 * @param cinemaId
	 * @return
	 */
	public List<OrderItem> findAllOrderItem(Order order) {
		List<OrderItem> orderItemList = new ArrayList<>();
		try {
			String sql = "SELECT id, cinema_id, order_id, quantity FROM order_items WHERE order_id = :order_id ORDER BY cinema_id";
			SqlParameterSource param = new MapSqlParameterSource().addValue("order_id", order.getId());
			orderItemList = template.query(sql, param, orderItemListRowMapper);
		} catch (EmptyResultDataAccessException e) {
		}
		return orderItemList;
	}

	/**
	 * カート内の商品を検索.
	 * 
	 * @param order
	 * @param cinemaId
	 * @return
	 */
	public List<Cart> findAllCart(Order order) {
		List<Cart> orderList = new ArrayList<>();
		try {
			String sql = "SELECT o.id, o.order_number, o.user_id, o.status, o.total_price,"
					+ " o.date, i.id, i.cinema_id, i.quantity" + " FROM orders AS o" + " INNER JOIN order_items AS i"
					+ " ON o.id = i.order_id" + " WHERE o.status = 0" + " AND o.user_id = :user_id";
			SqlParameterSource param = new MapSqlParameterSource().addValue("user_id", order.getUserId());
			orderList = template.query(sql, param, orderListRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return orderList;
	}

	/**
	 * ユーザーのオーダー情報を更新.
	 * 
	 * @param order
	 */
	public void updateOrder(Order order) {
		String sql = "UPDATE orders SET total_price=:total_price, date=:date WHERE id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("total_price", order.getTotalPrice())
				.addValue("date", order.getDate()).addValue("id", order.getId());
		template.update(sql, param);
	}

	/**
	 * 商品の削除.
	 * 
	 * @param orderCinemaId
	 */
	public void deleteByCinemaId(long orderCinemaId) {
		String sql = "DELETE FROM order_items WHERE id=:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", orderCinemaId);
		template.update(sql, param);
	}

	/**
	 * ordersをIDで検索
	 * 
	 * @param id
	 * @return
	 */
	public Order load(long id) {
		OrderRowCallbackHandler orderRowCallbackHandler = new OrderRowCallbackHandler();
		try {
			String sql = "SELECT o.id AS o_id, o.order_number, o.user_id, o.status, o.total_price, o.date,"
					+ " i.id AS i_id, i.cinema_id, i,quantity" + " From orders AS o"
					+ " LEFT OUTER JOIN order_items AS i" + " ON o.id = i.order_id"
					+ " WHERE o.id=:id";
			SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
			template.query(sql, param, orderRowCallbackHandler);
			if (orderRowCallbackHandler.getOrders().isEmpty()) {
				throw new EmptyResultDataAccessException(1);
			} else {
				return orderRowCallbackHandler.getOrders().get(0);
			}
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
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
		SqlParameterSource param = new MapSqlParameterSource().addValue("status", status).addValue("orderNumber",
				orderNumber);
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
			String sql = "UPDATE orders SET status=1, date=cast(now() as date) WHERE id=:id";
			SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
			template.update(sql, param);
			return true;
		} catch (Exception ex) {
			// ex.printStackTrace();
			return false;
		}
	}
}
