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
}
