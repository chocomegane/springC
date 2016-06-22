package jp.co.rakus.ecommers.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	public CinemaForm setUpForm2() {
		return new CinemaForm();
	}
	
	/**
	 * 初期ページを表示するメソッド.
	 * @param model requestスコープを扱うための変数
	 * @return updateCinema.jspへフォワード
	 */
	@RequestMapping
	public String index(@RequestParam Integer id, Model model) {
		Cinema cinema = service.findOne(id);	// 試験的に使います
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
	@RequestMapping(value = "/execute", method=RequestMethod.POST)
	public String output(CinemaForm form, BindingResult result, Model model) {
		try {
			String releaseDate = form.getReleaseDate();
			Date date = new SimpleDateFormat("yyyy/MM/dd").parse(releaseDate);
			
			Cinema cinema = new Cinema();
			cinema.setReleaseDate(date);
			
			BeanUtils.copyProperties(form, cinema);
			service.save(cinema);
		    model.addAttribute("message", "正常に登録が完了しました");
			return "updateCinema";
		} catch (Exception e) {
			System.err.println("不正な値が入力されました");
			return null;
		}
	}
}