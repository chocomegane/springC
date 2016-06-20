package jp.co.rakus.ecommers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ecommers.service.CinemaListService;
import jp.co.rakus.ecommers.service.ListViewService;

@Controller
@Transactional
@RequestMapping(value="/")
public class CinemaListController {

	@Autowired
	private CinemaListService service;
	
	/**
	 * 書籍情報を全件取得するメソッド.
	 * 
	 * @param model
	 * @return　フォワード先の名前.
	 */
	@RequestMapping(value="list")
	public String list(Model model){
		CinemaListPage childPage = new CinemaListPage();
		childPage = service.findAll();
		
		model.addAttribute("childPage", childPage);
		
		return "userCinemaList";
	}
	
}
