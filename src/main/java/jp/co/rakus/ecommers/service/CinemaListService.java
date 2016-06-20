package jp.co.rakus.ecommers.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommers.domain.Cinema;
import jp.co.rakus.ecommers.repository.AdminUserRepository;
import jp.co.rakus.ecommers.repository.CinemaRepository;
import jp.co.rakus.ecommers.web.CinemaChildPage;
import jp.co.rakus.ecommers.web.CinemaListPage;

/**
 * 映画の情報を取得するのを操作するクラス.
 * 
 * @author tsubasa.kaneko
 *
 */
@Service
public class CinemaListService {

	@Autowired
	private CinemaRepository repository;
	
	/**
	 * 映画の情報をすべて取得するメソッド.
	 * 
	 * @return CinemaListPage childが入ったList.
	 */
	public CinemaListPage findAll(){
		List<Cinema> cinemaList = repository.findAll();
		
		CinemaListPage childPage = new CinemaListPage();
		
		for(Cinema cinema : cinemaList){
			CinemaChildPage child = new CinemaChildPage();
			BeanUtils.copyProperties(cinema, child);
			childPage.getChilePageList().add(child);
		}
		
		return childPage;
		
		
		/*
		
		BeanUtils.copyProperties(cinemaList, childPage);
		
		return childPage;
		
		*/
		
		/*
		for(Cinema cinema: cinemaList){
			
			for(CinemaChildPage page : childPage){
				
			CinemaChildPage page = new CinemaChildPage();
			BeanUtils.copyProperties(cinema, page);
			
			}
		}
		*/
		
		
	}
	
}
