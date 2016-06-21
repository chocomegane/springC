package jp.co.rakus.ecommers.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ユーザー登録関連のコントローラー
 * @author rakus
 */

@Controller
@RequestMapping(value = "/administer")
public class AdminMenuController {
	
	@RequestMapping(value = "/")
	public String index()
	{
		return "/administerMenu";
	}
	
}