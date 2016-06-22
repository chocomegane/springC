package jp.co.rakus.ecommers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ecommers.service.CinemaListService;

/**
 * 管理者の画面遷移を行うクラス.
 * 
 * @author tsubasa.kaneko
 *
 */
@Controller
@Transactional
@RequestMapping(value="/admin")
public class AdminCinemaListController {

	@Autowired
	private CinemaListService service;
	
	/**
	 * 管理者の商品一覧表示を表示するメソッド.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/displayList")
	public String list(Model model){
		CinemaListPage listPage = service.findAll();
		
		model.addAttribute("listPage", listPage);
		
		return "administerCinemaList";
	}
}
