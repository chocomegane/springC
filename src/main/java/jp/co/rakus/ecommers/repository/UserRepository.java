package jp.co.rakus.ecommers.repository;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.rakus.ecommers.domain.AdminUser;
import jp.co.rakus.ecommers.domain.Order;
import jp.co.rakus.ecommers.domain.User;

@Repository
public class UserRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * ResultSetオブジェクトからAdminUserオブジェクトに変換するためのクラス実装&インスタンス化
	 */
	private static final RowMapper<User> UserRowMapper = (rs, i) -> {
		Long id = rs.getLong("id");
		String name = rs.getString("name");
		String email = rs.getString("email");
		String address = rs.getString("address");
		String password = rs.getString("password");
		String telephone = rs.getString("telephone");
		return new User(id, name, email, address, password, telephone);
	};
	
	public User findById(long id) {
		String sql = "SELECT * FROM users WHERE id = :user_id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("user_id", id);
		User user = template.queryForObject(sql, param, UserRowMapper);
		return user;
	}

}
