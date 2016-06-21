package jp.co.rakus.ecommers.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.rakus.ecommers.domain.Order;

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
		Timestamp date = rs.getTimestamp("date");
		return new Order(id, orderNumber, userId, status, null, totalPrice, date);
	}; 

	public List<Order> findAll() {
		String sql = "SELECT * FROM orders ORDER BY order_number";
		List<Order> orderList = template.query(sql, orderRowMapper);
		return orderList;
	}
	
}
