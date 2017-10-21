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
	 * ※URLの記載が（localhost:8081/ecommerce-springC-sawamura/）の場合このメソッドを通る
	 * つまり一番最初に実行される予定のメソッド
	 * 
	 * URLをしていないので見つけることに時間がかかってしまうため補足 URLを全体的に見直したい
	 * 
	 * @param model
	 * @return 利用者の商品一覧画面.
	 */
	@RequestMapping
	public String list(Model model) {
		// 使用しているユーザー名を初期値としてゲストとする
		Guest guest = new Guest();
		guest.setName("ゲスト");
		model.addAttribute("guest", guest);
		// 件数を全件数を取得する
		long cinemaNumber = service.cinemaNumber();
		// ページング数を決めるために20で固定
		// TODO
		// 外部化します
		long pageNumber = cinemaNumber % 20;
		// セッションに渡す
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("cinemaNumber", cinemaNumber);
		//
		return "userCinemaList";
	}

}
