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
import jp.co.rakus.ecommers.service.ListViewService;

/**
 * 
 * @author yusuke.nakano
 *
 */
@Controller
@Transactional
@RequestMapping(value = "/insert")
public class InsertCinemaController {
	
	/** ListViewServiceを利用するためのDI */
	@Autowired
	private ListViewService service;
		
	@ModelAttribute
	public CinemaForm setUpForm() {
		return new CinemaForm();
	}
	
	@RequestMapping
	public String index(Model model) {
		return "insertCinema";
	}
	
	/**
	 * @param model
	 * @return
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