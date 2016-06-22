package jp.co.rakus.ecommers.repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.rakus.ecommers.domain.Order;
import jp.co.rakus.ecommers.domain.User;

/**
 * 注文詳細一覧を表示するためのRepository(仮作成).
 * 藤本君がOrderCinemaRepositoryを編集中なので管理者側が<br>
 * 注文詳細画面を表示する操作を行うためにこのクラスを作った。
 * 藤本君がOrderCinemaRepositoryを作成し終えたらそちらに統合する予定
 * @author yusuke.nakano
 *
 */
@Repository
public class OrderCinemaRepository2nd {

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

	public Order findByUserId(String userId) {
		String sql = "SELECT * FROM orders WHERE order_number = :user_id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("user_id", userId);
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