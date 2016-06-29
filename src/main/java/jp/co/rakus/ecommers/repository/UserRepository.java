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
		String sql = "SELECT id,name,email,password,address,telephone FROM " + TABLE_NAME + " WHERE email=:email;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("email",email);
		User user = null;
		try{
			user = jdbcTemplate.queryForObject(sql,param,USER_ROW_MAPPER);
			return user;
		} catch(DataAccessException e) {
			System.err.println("user not found");
			e.printStackTrace();
			return null;
		}
	}
	
	public User findById(Long id) {
		String sql = "SELECT id,name, email, password,address, telephone FROM " + TABLE_NAME + " WHERE id=:id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id",id);
		User user = jdbcTemplate.queryForObject(sql,param, USER_ROW_MAPPER);
		return user;
	}

	public void userInsert(User user) {
		
		

	    System.err.println(user);
		SqlParameterSource param = new BeanPropertySqlParameterSource(user);
		String sql = "INSERT INTO users(name, email, password,address, telephone) VALUES(:name, :email, :password , :address, :telephone)";
		System.out.println("repg err6");
		jdbcTemplate.update(sql, param);
	}
}