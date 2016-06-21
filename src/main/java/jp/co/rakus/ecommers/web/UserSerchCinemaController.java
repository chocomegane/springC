package jp.co.rakus.ecommers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.rakus.ecommers.service.CinemaListService;

/**
 * 利用者の商品検索画面を遷移するクラス.
 * 
 * @author tsubasa.kaneko
 *
 */
@Controller
@Transactional
@RequestMapping(value="/serch")
public class UserSerchCinemaController {

	@Autowired
	private CinemaListService service;
	
	/**
	 * ジャンルを条件に商品の情報を取得するメソッド.
	 * 
	 * @param genreStr 送られてきたリクエストパラメータ.
	 * @param model スコープに格納するパラム.
	 * @return フォワード先の名前.
	 */
	@RequestMapping(value="/genre")
	public String listGenre(@RequestParam String genreStr, Model model){
		System.out.println("リクエストパラメータの確認=" + genreStr);
		CinemaListPage listPage = service.findByGenre(genreStr);
		
		model.addAttribute("listPage", listPage);
		
		return "userCinemaList";
	}
}
