package jp.co.rakus.ecommers.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jp.co.rakus.ecommers.domain.AdminUserRegister;
import jp.co.rakus.ecommers.web.AdminUserRegisterForm;

/**
 * 管理者追加repository
 * @author rakus
 */
@Repository
@Transactional
public class AdministerRegisterRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
//	public void adminInsert(String name , String password , String email)
	public void adminInsert(AdminUserRegister adminUserRegister)
	{
		
//		System.out.println("name:password:email"+ form.getName() + form.getPassword() + form.getEmail());
//		SqlParameterSource param = new MapSqlParameterSource().addValue("name",form.getName()).addValue("password", form.getPassword()).addValue("email",form.getEmail());
		SqlParameterSource param = new BeanPropertySqlParameterSource(adminUserRegister);
//		System.out.println("name:password:email"+ form.getName() + form.getPassword() + form.getEmail());
		String sql = "INSERT INTO admin_users(name, password, email) " + "VALUES(:name, :password, :email)";
		jdbcTemplate.update(sql, param);
	}

}
