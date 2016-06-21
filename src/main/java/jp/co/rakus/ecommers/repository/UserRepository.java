package jp.co.rakus.ecommers.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.rakus.ecommers.domain.User;

/**
 * データベース "ecommerces" のテーブル "users" を操作するためのRepositoryクラス.
 * 
 * @author kohei.sakata
 *
 */
@Repository
public class UserRepository {
	/** このRepositoyで扱うテーブルの名前  */
	private static final String TABLE_NAME = "users";

	/** NamedParameterJdbcTemplateを利用するためのDI */
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	/**
	 * ResultSetオブジェクトからUserオブジェクトに変換するためのクラス実装&インスタンス化
	 */
	private static final RowMapper<User> USER_ROW_MAPPER = (rs, i) -> {
		Long id = rs.getLong("id");
		String name = rs.getString("name");
		String email = rs.getString("email");
		String password = rs.getString("password");
		String address = rs.getString("address");
		String telephone = rs.getString("telephone");
		return new User(id, name, email, password,address,telephone);
	};

	/**
	 * メールアドレスからUserを取得.
	 * @param email メールアドレス
	 * @return ユーザー情報.ユーザーが存在しない場合はnull.
	 */
	public User findByEmail(String email) {
		String sql = "SELECT id,name,email,password FROM " + TABLE_NAME + " WHERE email=:email;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("email",email);
		User user = null;
		try{
			user = jdbcTemplate.queryForObject(sql,param, USER_ROW_MAPPER);
			return user;
		} catch(DataAccessException e) {
			return null;
		}
	}

}
