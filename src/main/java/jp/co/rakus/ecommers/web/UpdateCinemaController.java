package jp.co.rakus.ecommers.web;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;

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

	@Autowired
	private ServletContext context;
	
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
	public String index(@RequestParam Long id, Model model) {
		Cinema cinema = service.findOne(id);
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
	public String output(@Validated CinemaForm form, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			if(form.getImagePath().getOriginalFilename().equals("")) {
				model.addAttribute("error", "画像を選択してください");
			}
			return index(form.getId(), model);
		}
		
		if(form.getImagePath().getOriginalFilename().equals("")) {
			model.addAttribute("error", "画像を選択してください");
			return index(form.getId(), model);
		}
		
		try {
			String releaseDate = form.getReleaseDate();
			Date before = new SimpleDateFormat("yyyy-MM-dd").parse(releaseDate);
			String strReleaseDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(before);
			Date after = new SimpleDateFormat("yyyy-MM-dd").parse(strReleaseDate);
			String path = context.getRealPath("/img/");
			form.getImagePath().transferTo( new File( path + form.getImagePath().getOriginalFilename() ));
			
			Cinema cinema = new Cinema();
			cinema.setReleaseDate(after);
			BeanUtils.copyProperties(form, cinema);
			cinema.setImagePath(form.getImagePath().getOriginalFilename());
			
			service.save(cinema);
		    model.addAttribute("message", "正常に登録が完了しました");
			return index(form.getId(), model);
		} catch (Exception e) {
			System.err.println("不正な値が入力されました");
			return "updateCinema";
		}
	}
}