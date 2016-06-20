package jp.co.rakus.ecommers.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jp.co.rakus.ecommers.web.AdminUserRegisterForm;

/**
 * 管理者追加repository
 * @author rakus
 */
@Repository
@Transactional
public class AdministerRegisterRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void adminInsert(AdminUserRegisterForm form)
	{
		SqlParameterSource param = new BeanPropertySqlParameterSource(form);
		String sql = "INSERT INTO admin_user(name, password, email) VALUES(:name, :password :email)";
		namedParameterJdbcTemplate.update(sql, param);
	}

}
