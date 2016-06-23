package jp.co.rakus.ecommers.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.rakus.ecommers.domain.OrderItem;

@Repository
public class OrderItemRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<OrderItem> orderItemRowMapper = (rs, i) -> {
		Long id = rs.getLong("id");
		Long cinemaId = rs.getLong("item_id");
		Integer quantity = rs.getInt("quantity");
		Long orderId = rs.getLong("order_id");
		return new OrderItem(id, cinemaId, quantity, orderId);
	}; 

	public List<OrderItem> findById(Long id) {
		String sql = "SELECT * FROM order_items WHERE order_id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		List<OrderItem> itemList = template.query(sql, param, orderItemRowMapper);
		return itemList;
	}
}
