package jp.co.rakus.ecommers.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
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
@RequestMapping(value = "/")
public class ListViewController {
	
	/** ListViewServiceを利用するためのDI */
	@Autowired
	private ListViewService service;
	
	@Autowired
	private OrderListPage page;
	
	/**
	 * 初期ページを表示する
	 * @return
	 */
	@RequestMapping(value = "/")
	public String index(Model model) {

		return "";
	}

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/output")
	public String output(Model model) {
		List<Cinema> cinemaList = service.findAll();
		page.addScope(model, cinemaList);
		return "";
	}
}