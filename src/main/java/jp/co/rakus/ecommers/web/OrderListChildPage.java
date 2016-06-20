package jp.co.rakus.ecommers.web;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jp.co.rakus.ecommers.domain.Cinema;

@Service
public class OrderListChildPage {

	public void addScope(Model model, Cinema cinema) {
		model.addAttribute("cinema", cinema);
	}	
}
