package jp.co.rakus.ecommers.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import jp.co.rakus.ecommers.domain.Guest;
import jp.co.rakus.ecommers.service.CinemaListService;

/**
 * 商品一覧を取得するクラス.
 * 
 * @author tsubasa.kaneko
 *
 */
@Controller
@Transactional
@SessionAttributes("guest")
@RequestMapping
public class UserCinemaListController {
	@Autowired
	private CinemaListService service;

	
	/**
	 * 商品一覧を全件取得するメソッド.
	 * 
	 * @param model
	 * @return　利用者の商品一覧画面.
	 */
	@RequestMapping
	public String list( Model model){
		
		Guest guest = new Guest();
		guest.setName("ゲスト");
		model.addAttribute("guest",guest);
		long cinemaNumber =  service.cinemaNumber();
		long pageNumber = cinemaNumber%20;
 		model.addAttribute("pageNumber",pageNumber);
 		model.addAttribute("cinemaNumber",cinemaNumber);
		
		return "userCinemaList";
	}
	
}
