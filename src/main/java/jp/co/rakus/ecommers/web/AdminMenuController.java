package jp.co.rakus.ecommers.web;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ecommers.domain.AdminUserRegister;
import jp.co.rakus.ecommers.service.AdministerRegisterService;

/**
 * ユーザー登録関連のコントローラー
 * @author rakus
 */

@Controller
@RequestMapping(value = "/administer")
public class AdminMenuController {

//	@Autowired
//	private AdministerRegisterService service;
	
	@RequestMapping(value = "/")
	public String index()
	{
		return "/administerMenu";
	}
	
//	@RequestMapping(value = "/administerRegister")
//	public String adminInsert(AdminUserRegisterForm form,AdminUserRegister adminUserRegister)
//	{
//		BeanUtils.copyProperties(form, adminUserRegister);
//		service.adminInsert(name,password, email);
//		service.adminInsert(adminUserRegister);
//		return "/administerRegister";
//	}
	
}