package jp.co.rakus.ecommers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ecommers.service.AdministerRegisterService;

/**
 * ユーザー登録関連のコントローラー
 * @author rakus
 */

@Controller
@RequestMapping(value = "/Administer")
public class AdministerRegisterController {

	@Autowired
	private AdministerRegisterService service;
	
	@RequestMapping(value = "/AdministerRegister")
	public String adminInsert(AdminUserRegisterForm form)
	{
		service.adminInsert(form);
		return "/Administer/AdministerRegister";
	}
	
}