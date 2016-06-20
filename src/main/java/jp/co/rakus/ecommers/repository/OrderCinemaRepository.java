package jp.co.rakus.ecommers.repository;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.validation.BeanPropertyBindingResult;

import jp.co.rakus.ecommers.domain.Order;
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
		Timestamp date = rs.getTimestamp("date");
		return new Order(id, orderNumber, userId, status, null,  totalPrice, date);
	}; 
	
	public void save(InsertForm form) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(form);
		String sql = "SELECT id, orderNumber, userId, status, orderCinemaList, totalPrice, date From orders WHERE userId=:userId";
		Order order = template.queryForObject(sql, param, orderRowMapper);
		if(order == null){
			String sql2 ="INSERT INTO orders(id, orderNumber, userId, status, orderCinemaList, totalPrice, date)" + " VALUES(:id, :orderNumber, :userId, :status, :orderCinemaList, :totalPrice, :date)";
		}
	}
}
