package jp.co.rakus.ecommers.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.rakus.ecommers.domain.User;
@Repository
public class UserRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;
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
		return new User(id, name, email, password, address, telephone);
	};

	/**
	 * メールアドレスとパスワードからメンバーを取得.
	 * 暗号化されたパスワードはSQLでマッチングできないから、まずメールアドレスで検索したのちパスワードをチェックする。
	 * @param mailAddress メールアドレス
	 * @param password パスワード
	 * @return メンバー情報.メンバーが存在しない場合はnull.
	 */
	public User findByEmail(String email) {
		String sql = "SELECT id,name, email, password,address, telephone FROM users WHERE email=:email;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("email",email);
		User user = null;
		try{
			user = template.queryForObject(sql,param, USER_ROW_MAPPER);
			return user;
		} catch(DataAccessException e) {
			return null;
		}
	}

	public void userInsert(User user) {

		SqlParameterSource param = new BeanPropertySqlParameterSource(user);
		String sql = "INSERT INTO users(name, email, password,address, telephone) VALUES(:name, :email, :password , :address, :telephone)";

		template.update(sql, param);
	}
}

