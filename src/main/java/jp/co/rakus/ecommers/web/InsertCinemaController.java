package jp.co.rakus.ecommers.web;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@Autowired
	private ServletContext context;
		
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
	public String output(@Validated CinemaForm form, BindingResult result, RedirectAttributes redirectAttributes, Model model) throws NumberFormatException {
		/*************************************************************************/
		System.err.println(form.getReleaseDate());
		// エラーチェック
		
		boolean errorFlagOfImage = false;
		boolean errorFlagOfTitle = false;
		
		if(result.hasErrors()) {
			if(form.getImagePath().getOriginalFilename().equals("")) {
				String err = "画像を選択してください";
				model.addAttribute("err", err);
				errorFlagOfImage = true;
			}
			// return index(model);
		}
				
		if(form.getImagePath().getOriginalFilename().equals("")) {
			String err = "画像を選択してください";
			model.addAttribute("err", err);
			errorFlagOfImage = true;
			// return "insertCinema";
		}
		
		// 追加
		List<Cinema> cinemaList = service.findAll();
		for(Cinema item : cinemaList) {
			if(form.getTitle().equals(item.getTitle())) {
				errorFlagOfTitle = true;
			}
		}
		
		if(errorFlagOfTitle == true) {
			String err2 = "そのタイトルはすでに使われています";
			model.addAttribute("err2", err2);
			if(errorFlagOfImage == true) {
				return "insertCinema";
			}
			return "insertCinema";
		}
		
		if(errorFlagOfImage == true) {
			return "insertCinema";
		}
		
		/*************************************************************************/
		try {
			// cinemaFormのreleaseDateがString型なので、Date型に変換
			String releaseDate = form.getReleaseDate();
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(releaseDate);
			
			
			// imagePath関係の処理
			String path = context.getRealPath("/img/");
			form.getImagePath().transferTo( new File( path + form.getImagePath().getOriginalFilename() ));
			
			Cinema cinema = new Cinema();
			cinema.setReleaseDate(date);
						
			BeanUtils.copyProperties(form, cinema);
			
			System.out.println("2:" + cinema.getReleaseDate());
			
			cinema.setImagePath(form.getImagePath().getOriginalFilename());
			cinema.setPrice(form.getIntPrice());
			cinema.setTime(form.getIntTime());
			
			service.save(cinema);
			
			redirectAttributes.addFlashAttribute("message", "正常に登録が完了しました");
			return "redirect:/admin/insert";
		} catch (Exception e) {
			System.err.println("不正な値が入力されました");
			return "insertCinema";
		}
	}
}