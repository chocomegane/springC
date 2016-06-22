package jp.co.rakus.ecommers.web;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ecommers.domain.Cinema;
import jp.co.rakus.ecommers.service.OrderListService;

/**
 * 映画の商品をDBに追加するためのクラス.
 * @author yusuke.nakano
 *
 */
@Controller
@Transactional
@RequestMapping(value = "/admin/insertCinema")
public class InsertCinemaController {
	
	/** ListViewServiceを利用するためのDI */
	@Autowired
	private OrderListService service;
		
	@ModelAttribute
	public CinemaForm setUpForm() {
		return new CinemaForm();
	}
	
	/**
	 * 初期ページを表示するメソッド.
	 * @param model requestスコープを扱うための変数
	 * @return insertCinema.jspへフォワード
	 */
	@RequestMapping
	public String index(Model model) {
		return "insertCinema";
	}
	
	/**
	 * 映画の商品を追加する処理を行うメソッド.
	 * @param form CinemaForm型の変数
	 * @param result BindingResult型の変数
	 * @param model requestスコープを扱うための変数
	 * @return insertCinema.jspへフォワード
	 */
	@RequestMapping(value = "/execute")
	public String output(CinemaForm form, BindingResult result, Model model) {
		Cinema cinema = new Cinema();
		BeanUtils.copyProperties(form, cinema);
		service.save(cinema);
	    model.addAttribute("message", "正常に登録が完了しました");
		return "insertCinema";
	}
}