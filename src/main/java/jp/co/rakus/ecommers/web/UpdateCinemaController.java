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
 * 映画を編集するためのクラス.
 * @author yusuke.nakano
 *
 */
@Controller
@Transactional
@RequestMapping(value = "/admin/updateCinema")
public class UpdateCinemaController {
	
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
	 * @return updateCinema.jspへフォワード
	 */
	@RequestMapping
	public String index(Model model) {
		Cinema cinema = service.findOne(7);	// 試験的に使います
		model.addAttribute("cinema", cinema);
		return "updateCinema";
	}
	
	/**
	 * 映画の商品を編集する処理を行うメソッド.
	 * @param form CinemaForm型の変数
	 * @param result BindingResult型の変数
	 * @param model requestスコープを扱うための変数
	 * @return updateCinema.jspへフォワード
	 */
	@RequestMapping(value = "/execute")
	public String output(CinemaForm form, BindingResult result, Model model) {
		Cinema cinema = new Cinema();
		BeanUtils.copyProperties(form, cinema);
		service.save(cinema);
	    model.addAttribute("message", "正常に登録が完了しました");
		return "updateCinema";
	}
}