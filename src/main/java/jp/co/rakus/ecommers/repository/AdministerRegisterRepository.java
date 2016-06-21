package jp.co.rakus.ecommers.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jp.co.rakus.ecommers.domain.AdminUser;

/**
 * 管理者追加repository
 * @author rakus
 */
@Repository
@Transactional
public class AdministerRegisterRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public void adminInsert(AdminUser adminUser)
	{
		
		SqlParameterSource param = new BeanPropertySqlParameterSource(adminUser);
		String sql = "INSERT INTO admin_users(name, email, password) VALUES(:name, :email, :password)";
		jdbcTemplate.update(sql, param);
	}

}
