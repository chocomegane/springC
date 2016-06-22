package jp.co.rakus.ecommers.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.rakus.ecommers.domain.Cinema;
import jp.co.rakus.ecommers.service.OrderListService;

/**
 * 映画の商品をDBに追加するためのクラス.
 * @author yusuke.nakano
 *
 */
@Controller
@Transactional
@RequestMapping(value = "/admin")
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
	@RequestMapping(value="/insert", method=RequestMethod.GET)
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
	@RequestMapping(value = "/insert", method=RequestMethod.POST)
	public String output(@Validated CinemaForm form, BindingResult result, Model model) throws NumberFormatException {
		/*************************************************************************/
		// エラーチェック
		if(result.hasErrors()) {
			return index(model);
		}
		/*************************************************************************/
		try {
			String releaseDate = form.getReleaseDate();
			Date date = new SimpleDateFormat("yyyy/MM/dd").parse(releaseDate);
			
			Cinema cinema = new Cinema();
			cinema.setReleaseDate(date);
			
			BeanUtils.copyProperties(form, cinema);
			service.save(cinema);
		    model.addAttribute("message", "正常に登録が完了しました");
			return "redirect:/admin/insert";
		} catch (Exception e) {
			System.err.println("不正な値が入力されました");
			return null;
		}
	}
}