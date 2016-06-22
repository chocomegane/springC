package jp.co.rakus.ecommers.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.rakus.ecommers.domain.AdminUser;

/**
 * データベース "ecommerces" のテーブル "admin_users" を操作するためのRepositoryクラス.
 * 
 * @author kohei.sakata
 *
 */
@Repository
public class AdminUserRepository {
	/** このRepositoyで扱うテーブルの名前  */
	private static final String TABLE_NAME = "admin_users";

	/** NamedParameterJdbcTemplateを利用するためのDI */
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	/**
	 * ResultSetオブジェクトからAdminUserオブジェクトに変換するためのクラス実装&インスタンス化
	 */
	private static final RowMapper<AdminUser> ADMIN_ROW_MAPPER = (rs, i) -> {
		Long id = rs.getLong("id");
		String name = rs.getString("name");
		String email = rs.getString("email");
		String password = rs.getString("password");
		return new AdminUser(id, name, email, password);
	};

	/**
	 * メールアドレスから管理者を取得.
	 * @param email メールアドレス
	 * @return 管理者情報.管理者が存在しない場合はnull.
	 */
	public AdminUser findByEmail(String email) {
		String sql = "SELECT id,name,email,password FROM " + TABLE_NAME + " WHERE email=:email;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("email",email);
		AdminUser adminUser = null;
		try{
			adminUser = jdbcTemplate.queryForObject(sql,param, ADMIN_ROW_MAPPER);
			return adminUser;
		} catch(DataAccessException e) {
			System.err.println("admin not found");
			e.printStackTrace();
			return null;
		}
		
		
		
		
	}
	
	
	/**
	 * 管理者追加メソッド
	 * 
	 * @param adminUser
	 */
	public void adminInsert(AdminUser adminUser)
	{
		SqlParameterSource param = new BeanPropertySqlParameterSource(adminUser);
		String sql = "INSERT INTO admin_users(name, email, password) VALUES(:name, :email, :password)";
		jdbcTemplate.update(sql, param);	
	}
	
	
	
	
	
	
	
}
