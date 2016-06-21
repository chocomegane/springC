package jp.co.rakus.ecommers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ecommers.service.CinemaListService;

/**
 * @author tsubasa.kaneko
 *
 */
@Controller
@Transactional
@RequestMapping(value="/")
public class AdminCinemaListController {

	@Autowired
	private CinemaListService service;
	
	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value="adminCinemaList")
	public String list(Model model){
		CinemaListPage listPage = service.findAll();
		
		model.addAttribute("listPage", listPage);
		
		return "administerCinemaList";
	}
}
