package jp.co.rakus.ecommers.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jp.co.rakus.ecommers.service.CinemaListService;
import net.arnx.jsonic.JSON;

@RestController
@RequestMapping("/json")
public class JsonLogicController {

	@Autowired
	private CinemaListService service;
	
	
	/**
	 * jsonの実装メソッドfindAllします
	 * @return
	 */
	@RequestMapping("/exe")
	@ResponseBody
	public String jsonExe() {
		

		// json
		CinemaListPage cinemaListPage = service.findAll();
		String json = JSON.encode(cinemaListPage.getChildPageList());
		return json;
	}
	
}
