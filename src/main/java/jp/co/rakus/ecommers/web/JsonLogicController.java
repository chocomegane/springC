package jp.co.rakus.ecommers.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jp.co.rakus.ecommers.domain.Cinema;
import jp.co.rakus.ecommers.service.CinemaListService;
import net.arnx.jsonic.JSON;

@RestController
@RequestMapping("/json")
public class JsonLogicController {

	@Autowired
	private CinemaListService service;

	/**
	 * jsonの実装メソッドfindAllします
	 * 
	 * @return
	 */
	@RequestMapping("/exe")
	@ResponseBody
	public String jsonExe() {

		// json

		int firstListNumber = 0;
		List<Cinema> cinemaList = service.cinemaNumberSearch(firstListNumber);
		String json = JSON.encode(cinemaList);

		return json;

	}

	@RequestMapping("/exe/paging")
	@ResponseBody
	public String jsonExe(@RequestParam int page) {
		int firstListNumber = 20 * page;
		List<Cinema> cinemaList = service.cinemaNumberSearch(firstListNumber);
		String json = JSON.encode(cinemaList);

		return json;
	}
	
	@RequestMapping("/exe/searchCinemaPrice")
	@ResponseBody
	public String earchCinemaPriceExe(@RequestParam String price, Model model) {
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
			return "userCinemaList";
		}

		// 商品結果をjspで表示
		model.addAttribute("searchResult", "検索結果：" + price);
		model.addAttribute("listPage", listPage);
		String json = JSON.encode(listPage);

		return json;
	}
	
	
}
