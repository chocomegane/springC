package jp.co.rakus.ecommers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.rakus.ecommers.service.CinemaListService;

/**
 * 管理者の画面遷移を行うクラス.
 * 
 * @author tsubasa.kaneko
 *
 */
@Controller
@Transactional
@RequestMapping(value = "/admin")
public class AdminCinemaListController {

	@Autowired
	private CinemaListService service;

	/**
	 * 管理者の商品一覧表示を表示するメソッド.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/displayList")
	public String list(Model model) {
		CinemaListPage listPage = service.findAll();
		long cinemaNumber = service.cinemaNumber();
		long pageNumber = cinemaNumber % 20;
		model.addAttribute("pageNumber", pageNumber);

		model.addAttribute("listPage", listPage);

		return "administerCinemaList";
	}

	/**
	 * 商品削除機能．
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public String deleteCinema(@RequestParam long id) {
		service.deleteCinema(id);
		return "redirect:/admin/displayList";
	}

	/**
	 * 削除した商品の再表示.
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/redisplay")
	public String redisplay(@RequestParam long id, Model model) {
		service.redisplay(id);
		return "redirect:/admin/deleteList";
	}

	/**
	 * 削除された商品の一覧を取得する .
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/deleteList")
	public String deleteCinemaList(Model model) {
		CinemaListPage listPage = service.findByDelete();
		model.addAttribute("listPage", listPage);
		return "administerDeleteCinemaList";
	}

}
