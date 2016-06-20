package jp.co.rakus.ecommers.web;

import java.util.List;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jp.co.rakus.ecommers.domain.Cinema;

@Service
public class OrderListPage {

//	@Autowired
//	private OrderListChildPage childPage;
	
	public void addScope(Model model, List<Cinema> cinemaList){
		model.addAttribute("cinemaList", cinemaList);
		
//		for(Cinema cinema : cinemaList) {
//			childPage.addScope(model, cinema);
//		}
	}
}
