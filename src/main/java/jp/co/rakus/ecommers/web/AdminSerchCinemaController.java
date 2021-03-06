package jp.co.rakus.ecommers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.rakus.ecommers.service.CinemaListService;

/* 取り扱ってる商品一覧のメソッド */

/**
 * 管理者の商品検索の画面遷移をするクラス.
 * 
 * @author tsubasa.kaneko
 *
 */
@Controller
@Transactional
@RequestMapping(value = "/admin/serchCinema")
public class AdminSerchCinemaController {
	@Autowired
	private CinemaListService service;

	/**
	 * ジャンルを条件に商品の情報を取得するメソッド.
	 * 
	 * @param genreStr
	 *            送られてきたリクエストパラメータ.
	 * @param model
	 *            スコープに格納するパラム.
	 * @return フォワード先の名前.
	 */
	@RequestMapping(value = "/genre")
	public String listGenre(@RequestParam String genre, Model model) {
		CinemaListPage listPage = service.findByGenre(genre);

		// 何も取得できなかったらメッセージを表示する
		if (listPage.getChildPageList().size() == 0) {
			model.addAttribute("message2", "商品がありません");
			// findAllで全件取得をする
			model.addAttribute("listPage", service.findAll());
			return "administerCinemaList";
		}

		// 商品結果をjspで表示
		model.addAttribute("searchResult", "検索結果：" + genre);

		model.addAttribute("listPage", listPage);

		return "administerCinemaList";
	}

	/**
	 * メディアタイプを条件に商品の情報を取得するメソッド.
	 * 
	 * @param mediaTypeStr
	 *            リクエストパラメータ.
	 * @param model
	 *            スコープに格納するパラム.
	 * @return フォワード先の名前.
	 */
	@RequestMapping(value = "/mediaType")
	public String listMediaType(@RequestParam String mediaType, Model model) {
		CinemaListPage listPage = service.findByMediaType(mediaType);

		// 何も取得できなかったらメッセージを表示する
		if (listPage.getChildPageList().size() == 0) {
			model.addAttribute("message2", "商品がありません");
			// 商品結果をjspで表示
			model.addAttribute("searchResult", "検索結果：" + mediaType);
			// findAllで全件取得をする
			model.addAttribute("listPage", service.findAll());
			return "administerCinemaList";
		}

		// 商品結果をjspで表示
		model.addAttribute("searchResult", "検索結果：" + mediaType);

		model.addAttribute("listPage", listPage);

		return "administerCinemaList";
	}

	/**
	 * 価格を条件に商品検索をするメソッド.
	 * 
	 * @param minPriceStr
	 * @param maxPriceStr
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/price")
	public String listPrice(@RequestParam String price, Model model) {

		Integer minPrice = 0;
		Integer maxPrice = 0;

		// リクエストパラメータのprice値を条件式にかける
		if (price.equals("0")) {
			minPrice = 0;
			maxPrice = 1000;
			price = "～1000円";
		} else if (price.equals("1")) {
			minPrice = 1000;
			maxPrice = 2000;
			price = "1000円～2000円";
		} else if (price.equals("2")) {
			minPrice = 2000;
			maxPrice = 3000;
			price = "2000円～3000円";
		} else if (price.equals("3")) {
			minPrice = 3000;
			price = "3000円～";
		}

		CinemaListPage listPage = new CinemaListPage();

		if (maxPrice.equals(0)) {
			listPage = service.findByMinPrice(minPrice);
		} else {
			listPage = service.findByMinMaxPrice(minPrice, maxPrice);
		}

		// 何も取得できなかったらメッセージを表示する
		if (listPage.getChildPageList().size() == 0) {
			model.addAttribute("message2", "商品がありません");
			// 商品結果をjspで表示
			model.addAttribute("searchResult", "検索結果：" + price);
			// findAllで全件取得をする
			model.addAttribute("listPage", service.findAll());
			return "administerCinemaList";
		}

		// 商品結果をjspで表示
		model.addAttribute("searchResult", "検索結果：" + price);

		model.addAttribute("listPage", listPage);

		return "administerCinemaList";

	}

	/**
	 * タイトルを条件に商品を検索するメソッド.
	 * 
	 * @param title
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/title")
	public String listTitle(@RequestParam String title, Model model) {

		// titleの中身が空だったらエラーメッセージを返す
		if (title.isEmpty()) {
			model.addAttribute("message", "何か入力してください");
			// findAllで全件取得をする
			model.addAttribute("listPage", service.findAll());
			return "administerCinemaList";
		}

		// あいまい検索のためにtitleに%を付ける
		String titleSearch = "%" + title + "%";

		CinemaListPage listPage = service.findByTitle(titleSearch);

		// 何も取得できなかったらメッセージを表示する
		if (listPage.getChildPageList().size() == 0) {
			model.addAttribute("message2", "商品がありません");
			// 商品結果をjspで表示
			model.addAttribute("searchResult", "検索結果：" + title);
			// findAllで全件取得をする
			model.addAttribute("listPage", service.findAll());
			return "administerCinemaList";
		}

		// 商品結果をjspで表示
		model.addAttribute("searchResult", "検索結果：" + title);

		model.addAttribute("listPage", listPage);

		return "administerCinemaList";
	}

	/* 削除済み商品一覧のメソッド */

	/**
	 * ジャンルを条件に削除された商品の情報を取得するメソッド.
	 * 
	 * @param genreStr
	 *            送られてきたリクエストパラメータ.
	 * @param model
	 *            スコープに格納するパラム.
	 * @return フォワード先の名前.
	 */
	@RequestMapping(value = "/deleteGenre")
	public String listDeleteGenre(@RequestParam String genre, Model model) {
		CinemaListPage listPage = service.findByDeleteGenre(genre);

		// 何も取得できなかったらメッセージを表示する
		if (listPage.getChildPageList().size() == 0) {
			model.addAttribute("message2", "商品がありません");
			// findbyDeleteで全件取得をする
			model.addAttribute("listPage", service.findByDelete());
			return "administerDeleteCinemaList";
		}

		// 商品結果をjspで表示
		model.addAttribute("searchResult", "検索結果：" + genre);

		model.addAttribute("listPage", listPage);

		return "administerDeleteCinemaList";
	}

	/**
	 * メディアタイプを条件に削除された商品の情報を取得するメソッド.
	 * 
	 * @param mediaTypeStr
	 *            リクエストパラメータ.
	 * @param model
	 *            スコープに格納するパラム.
	 * @return フォワード先の名前.
	 */
	@RequestMapping(value = "/deleteMediaType")
	public String listDeleteMediaType(@RequestParam String mediaType, Model model) {
		CinemaListPage listPage = service.findByDeleteMediaType(mediaType);

		// 何も取得できなかったらメッセージを表示する
		if (listPage.getChildPageList().size() == 0) {
			model.addAttribute("message2", "商品がありません");
			// 商品結果をjspで表示
			model.addAttribute("searchResult", "検索結果：" + mediaType);
			// findByDeleteで全件取得をする
			model.addAttribute("listPage", service.findByDelete());
			return "administerDeleteCinemaList";
		}

		// 商品結果をjspで表示
		model.addAttribute("searchResult", "検索結果：" + mediaType);

		model.addAttribute("listPage", listPage);

		return "administerDeleteCinemaList";
	}

	/**
	 * タイトルを条件に商品を検索するメソッド.
	 * 
	 * @param title
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/deleteTitle")
	public String listDeleteTitle(@RequestParam String title, Model model) {

		// titleの中身が空だったらエラーメッセージを返す
		if (title.isEmpty()) {
			model.addAttribute("message", "何か入力してください");
			// findBydeleteで全件取得をする
			model.addAttribute("listPage", service.findByDelete());
			return "administerDeleteCinemaList";
		}

		// あいまい検索のためにtitleに%を付ける
		String titleSearch = "%" + title + "%";

		CinemaListPage listPage = service.findByDeleteTitle(titleSearch);

		// 何も取得できなかったらメッセージを表示する
		if (listPage.getChildPageList().size() == 0) {
			model.addAttribute("message2", "商品がありません");
			// 商品結果をjspで表示
			model.addAttribute("searchResult", "検索結果：" + title);
			// findByDeleteで全件取得をする
			model.addAttribute("listPage", service.findByDelete());
			return "administerDeleteCinemaList";
		}

		// 商品結果をjspで表示
		model.addAttribute("searchResult", "検索結果：" + title);

		model.addAttribute("listPage", listPage);

		return "administerDeleteCinemaList";
	}

	/**
	 * 価格を条件に削除された商品の一覧を取得するメソッド.
	 * 
	 * @param price
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/deletePrice")
	public String listDeletePrice(@RequestParam String price, Model model) {

		Integer minPrice = 0;
		Integer maxPrice = 0;

		// リクエストパラメータのprice値を条件式にかける
		if (price.equals("0")) {
			minPrice = 0;
			maxPrice = 1000;
			price = "～1000円";
		} else if (price.equals("1")) {
			minPrice = 1000;
			maxPrice = 2000;
			price = "1000円～2000円";
		} else if (price.equals("2")) {
			minPrice = 2000;
			maxPrice = 3000;
			price = "2000円～3000円";
		} else if (price.equals("3")) {
			minPrice = 3000;
			price = "3000円～";
		}

		CinemaListPage listPage = new CinemaListPage();

		if (maxPrice.equals(0)) {
			listPage = service.findByDeleteMinPrice(minPrice);
		} else {
			listPage = service.findByDeleteMinMaxPrice(minPrice, maxPrice);
		}

		// 何も取得できなかったらメッセージを表示する
		if (listPage.getChildPageList().size() == 0) {
			model.addAttribute("message2", "商品がありません");
			// 商品結果をjspで表示
			model.addAttribute("searchResult", "検索結果：" + price);
			// findByDeleteで全件取得をする
			model.addAttribute("listPage", service.findByDelete());
			return "administerDeleteCinemaList";
		}

		// 商品結果をjspで表示
		model.addAttribute("searchResult", "検索結果：" + price);

		model.addAttribute("listPage", listPage);

		return "administerDeleteCinemaList";

	}

}
