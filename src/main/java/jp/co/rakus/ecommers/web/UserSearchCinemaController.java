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
@RequestMapping
public class UserSearchCinemaController {

	@Autowired
	private CinemaListService service;
	
	/**
	 * ジャンルを条件に商品の情報を取得するメソッド.
	 * 
	 * @param genre 送られてきたリクエストパラメータ.
	 * @param model スコープに格納するパラム.
	 * @return フォワード先の名前.
	 */
	@RequestMapping(value="/searchCinemaGenre")
	public String listGenre(@RequestParam String genre, Model model){
		
		System.out.println("genreの中身の確認" + genre);
		
		CinemaListPage listPage = service.findByGenre(genre);
		
		model.addAttribute("listPage", listPage);
		
		return "userCinemaList";
	}
	
	/**
	 * メディアタイプを条件に商品の情報を取得するメソッド.
	 * 
	 * @param mediaType リクエストパラメータ.
	 * @param model スコープに格納するパラム.
	 * @return フォワード先の名前.
	 */
	@RequestMapping(value="/searchCinemaMediaType")
	public String listMediaType(@RequestParam String mediaType, Model model){
		
		CinemaListPage listPage = service.findByMediaType(mediaType);
		
		model.addAttribute("listPage", listPage);
		
		return "userCinemaList";
	}
	
	/**
	 * 価格を条件に商品検索をするメソッド.
	 * 
	 * @param price
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/searchCinemaPrice")
	public String listPrice(@RequestParam String price, Model model){

		Integer minPrice = 0;
		Integer maxPrice = 0;
		
		//リクエストパラメータのprice値を条件式にかける
		if(price.equals("0")){
			minPrice = 0;
			maxPrice = 1000;
		}else if(price.equals("1")){
			minPrice = 1000;
			maxPrice = 2000;
		}else if(price.equals("2")){
			minPrice = 2000;
			maxPrice = 3000;
		}else if(price.equals("3")){
			minPrice = 3000;
		}
		
		CinemaListPage listPage = new CinemaListPage();
		
		if(maxPrice.equals(0)){
			listPage = service.findByMinPrice(minPrice);
		}else{
			listPage = service.findByMinMaxPrice(minPrice, maxPrice);
		}
		
		//何も取得できなかったらメッセージを表示する
		if(listPage.getChildPageList().size()==0){
		model.addAttribute("message2", "商品がありません");
		//findAllで全件取得をする
		model.addAttribute("listPage", service.findAll());
		return "userCinemaList";
				}
		
		model.addAttribute("listPage", listPage);
		
		return "userCinemaList";
		
	}
	
	/**
	 * タイトルを条件に商品を検索するメソッド.
	 * 
	 * @param title
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/searchCinemaTitle")
	public String listTitle(@RequestParam String title, Model model){
		System.out.println("/searchCinemaTitle");
		//titleの中身が空だったらエラーメッセージを返す
		if(title.isEmpty()){
			model.addAttribute("message", "何か入力してください");
			//findAllで全件取得をする
			model.addAttribute("listPage", service.findAll());
			return "userCinemaList";
		}
		
		//あいまい検索のためにtitleに%を付ける
		title = "%" + title + "%";
		System.out.println("titleの中身の確認" + title);
		
		CinemaListPage listPage = service.findByTitle(title);
		
		System.out.println("listPAgeの中身を確認" + listPage);
		
		//何も取得できなかったらメッセージを表示する
		if(listPage.getChildPageList().size()==0){
			model.addAttribute("message2", "商品がありません");
			//findAllで全件取得をする
			model.addAttribute("listPage", service.findAll());
			return "userCinemaList";
		}
		
		model.addAttribute("listPage", listPage);
		
		return "userCinemaList";
	}
}
