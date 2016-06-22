package jp.co.rakus.ecommers.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 管理者のメニュー画面を出力するコントローラー.
 * @author yusuke.nakano
 *
 */
@Controller
@RequestMapping(value = "/administer")
public class AdminMenuController {	
	/**
	 * 管理者メニュー画面を表示する
	 * @return administerMenu.jspへフォワード
	 */
	@RequestMapping(value = "/top")
	public String index() {
		return "/administerMenu";
	}
}