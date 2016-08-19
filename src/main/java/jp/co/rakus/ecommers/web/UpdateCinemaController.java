package jp.co.rakus.ecommers.web;

import java.text.SimpleDateFormat;
import java.util.Base64;
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
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.rakus.ecommers.domain.Cinema;
import jp.co.rakus.ecommers.service.OrderListService;

/**
 * 映画を編集するためのクラス.
 * 
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

	private static final int MAX_FILE_SIZE = 5242880; // 1024*1024*5 5MB =
														// 5242880byte

	@ModelAttribute
	public CinemaForm setUpForm() {
		return new CinemaForm();
	}

	/**
	 * 初期ページを表示するメソッド.
	 * 
	 * @param model
	 *            requestスコープを扱うための変数
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
	 * 
	 * @param form
	 *            CinemaForm型の変数
	 * @param result
	 *            BindingResult型の変数
	 * @param model
	 *            requestスコープを扱うための変数
	 * @return updateCinema.jspへフォワード
	 */
	@RequestMapping(value = "/execute", method = RequestMethod.POST)
	public String output(@Validated CinemaForm form, BindingResult result, Model model) {

		boolean errorFlag = false;
		boolean errorFlagOfTitle = false;

		if (form.getImagePath().getSize() > MAX_FILE_SIZE) {
			model.addAttribute("err3", "【容量オーバー】5MB以内の画像を選択してください");
			errorFlag = true;
		}

		if (result.hasErrors()) {
			errorFlag = true;
		}

		// 追加
		List<Cinema> cinemaList = service.findAll();
		for (Cinema item : cinemaList) {
			if (form.getTitle().equals(item.getTitle())) {
				if (!form.getOriginallyTitle().equals(item.getTitle())) {
					errorFlagOfTitle = true;
				}
			}
		}

		if (errorFlagOfTitle == true) {
			String err2 = "そのタイトルはすでに使われています";
			model.addAttribute("err2", err2);
			if (errorFlag == true) {
				return index(form.getId(), model);
			}
			return index(form.getId(), model);
		}

		if (errorFlag == true) {
			return index(form.getId(), model);
		}
		System.out.println("testdate=");
		try {
			// 公開日のフォーマット変更

			Cinema cinema = new Cinema();
			String releaseDate = form.getReleaseDate();
			System.out.println("date" + releaseDate);

			//
			// String path = context.getRealPath("/img/");

			BeanUtils.copyProperties(form, cinema);

			Date before = new SimpleDateFormat("yyyy-MM-dd").parse(releaseDate);
			String strReleaseDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(before);
			Date after = new SimpleDateFormat("yyyy-MM-dd").parse(strReleaseDate);
			cinema.setReleaseDate(after);

			if (!form.getImagePath().isEmpty()) {

				String encode = Base64.getEncoder().encodeToString(form.getImagePath().getBytes());
				cinema.setImagePath(encode);
				/* ↓イメージの画像の名前を分けて取得しています */
				// form.getImagePath().transferTo( new File( path +
				// form.getImagePath().getOriginalFilename() ));
				// cinema.setImagePath(form.getImagePath().getOriginalFilename());
			} else {
				cinema.setImagePath(form.getOriginallyImagePath());
			}

			service.save(cinema);
			model.addAttribute("message", "正常に登録が完了しました");
			return index(form.getId(), model);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("不正な値が入力されました");
			return "updateCinema";
		}
	}
}