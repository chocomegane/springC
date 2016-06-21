package jp.co.rakus.ecommers.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommers.domain.Cinema;
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
	public CinemaListPage findAll() {
		List<Cinema> cinemaList = repository.findAll();

		CinemaListPage listPage = new CinemaListPage();

		// forの中でgetをしているため一度インスタンスを生成してsetしておく
		List<CinemaChildPage> childSet = new ArrayList<>();
		listPage.setChildPageList(childSet);

		for (Cinema cinema : cinemaList) {
			CinemaChildPage child = new CinemaChildPage();
			BeanUtils.copyProperties(cinema, child);

			listPage.getChildPageList().add(child);
		}

		return listPage;

	}

	/**
	 * ジャンルを検索条件に映画の情報を取得するメソッド.
	 * 
	 * @param genre
	 *            ジャンル.
	 * @return CinemaListPage childが入ったList.
	 */
	public CinemaListPage findByGenre(String genre) {
		List<Cinema> cinemaList = repository.findByGenre(genre);

		CinemaListPage listPage = new CinemaListPage();

		List<CinemaChildPage> childSet = new ArrayList<>();
		listPage.setChildPageList(childSet);

		for (Cinema cinema : cinemaList) {
			CinemaChildPage child = new CinemaChildPage();
			BeanUtils.copyProperties(cinema, child);

			listPage.getChildPageList().add(child);
		}

		return listPage;
	}

	/**
	 * メディアタイプを条件に商品検索をするメソッド.
	 * 
	 * @param mediaType
	 *            メディアタイプ.
	 * @return CinemaListPage childが入ったList.
	 */
	public CinemaListPage findByMediaType(String mediaType) {
		List<Cinema> cinemaList = repository.findByMediaType(mediaType);

		CinemaListPage listPage = new CinemaListPage();

		List<CinemaChildPage> childSet = new ArrayList<>();
		listPage.setChildPageList(childSet);

		for (Cinema cinema : cinemaList) {
			CinemaChildPage child = new CinemaChildPage();
			BeanUtils.copyProperties(cinema, child);

			listPage.getChildPageList().add(child);
		}

		return listPage;
	}
	
	/**
	 * 二つの価格の条件から商品を検索するメソッド.
	 * 
	 * @param minPrice 最低価格.
	 * @param maxPrice　最高価格.
	 * @return CinemaListPage childが入ったList.
	 */
	public CinemaListPage findByMinMaxPrice(Integer minPrice, Integer maxPrice){
		List<Cinema> cinemaList = repository.findByMinMaxPrice(minPrice, maxPrice);

		CinemaListPage listPage = new CinemaListPage();

		List<CinemaChildPage> childSet = new ArrayList<>();
		listPage.setChildPageList(childSet);

		for (Cinema cinema : cinemaList) {
			CinemaChildPage child = new CinemaChildPage();
			BeanUtils.copyProperties(cinema, child);

			listPage.getChildPageList().add(child);
		}

		return listPage;
	}
	
	public CinemaListPage findByMinPrice(Integer minPrice){
		List<Cinema> cinemaList = repository.findByMinPrice(minPrice);

		CinemaListPage listPage = new CinemaListPage();

		List<CinemaChildPage> childSet = new ArrayList<>();
		listPage.setChildPageList(childSet);

		for (Cinema cinema : cinemaList) {
			CinemaChildPage child = new CinemaChildPage();
			BeanUtils.copyProperties(cinema, child);

			listPage.getChildPageList().add(child);
		}

		return listPage;
	}

}
