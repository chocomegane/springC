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

		return cinemaListPageAdd(cinemaList);

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

		return cinemaListPageAdd(cinemaList);
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

		return cinemaListPageAdd(cinemaList);
	}

	/**
	 * 二つの価格の条件から商品を検索するメソッド.
	 * 
	 * @param minPrice
	 *            最低価格.
	 * @param maxPrice
	 *            最高価格.
	 * @return CinemaListPage childが入ったList.
	 */
	public CinemaListPage findByMinMaxPrice(Integer minPrice, Integer maxPrice) {
		List<Cinema> cinemaList = repository.findByMinMaxPrice(minPrice, maxPrice);

		return cinemaListPageAdd(cinemaList);
	}

	/**
	 * 最低価格から商品の情報を検索するメソッド.
	 * 
	 * @param minPrice
	 * @return
	 */
	public CinemaListPage findByMinPrice(Integer minPrice) {
		List<Cinema> cinemaList = repository.findByMinPrice(minPrice);

		return cinemaListPageAdd(cinemaList);
	}

	public Cinema findById(Long id) {
		return repository.findOne(id);
	}

	/**
	 * タイトルを条件に商品の情報を検索するメソッド.
	 * 
	 * @param title
	 * @return
	 */
	public CinemaListPage findByTitle(String title) {
		List<Cinema> cinemaList = repository.findByTitle(title);

		return cinemaListPageAdd(cinemaList);
	}

	/**
	 * @param id
	 * @return
	 */
	public int deleteCinema(long id) {
		return repository.delete(id);
	}

	/**
	 * 削除した商品の再表示をする.
	 * 
	 * @param id
	 * @return
	 */
	public int redisplay(long id) {
		return repository.redisplay(id);
	}

	/* 削除された商品の一覧を取得するメソッド */

	/**
	 * 削除した商品の一覧を取得する.
	 * 
	 * @return
	 */
	public CinemaListPage findByDelete() {
		List<Cinema> cinemaList = repository.findByDelete();

		return cinemaListPageAdd(cinemaList);

	}

	/**
	 * タイトルを条件に削除された商品の情報を検索するメソッド.
	 * 
	 * @param title
	 * @return
	 */
	public CinemaListPage findByDeleteTitle(String title) {
		List<Cinema> cinemaList = repository.findByDeleteTitle(title);

		return cinemaListPageAdd(cinemaList);
	}

	/**
	 * 二つの価格の条件から削除された商品を検索するメソッド.
	 * 
	 * @param minPrice
	 *            最低価格.
	 * @param maxPrice
	 *            最高価格.
	 * @return CinemaListPage childが入ったList.
	 */
	public CinemaListPage findByDeleteMinMaxPrice(Integer minPrice, Integer maxPrice) {
		List<Cinema> cinemaList = repository.findByDeleteMinMaxPrice(minPrice, maxPrice);

		return cinemaListPageAdd(cinemaList);
	}

	/**
	 * 最低価格から削除された商品の情報を検索するメソッド.
	 * 
	 * @param minPrice
	 * @return
	 */
	public CinemaListPage findByDeleteMinPrice(Integer minPrice) {
		List<Cinema> cinemaList = repository.findByDeleteMinPrice(minPrice);
		return cinemaListPageAdd(cinemaList);
	}

	/**
	 * ジャンルを検索条件に削除された映画の情報を取得するメソッド.
	 * 
	 * @param genre
	 *            ジャンル.
	 * @return CinemaListPage childが入ったList.
	 */
	public CinemaListPage findByDeleteGenre(String genre) {
		List<Cinema> cinemaList = repository.findByDeleteGenre(genre);
		return cinemaListPageAdd(cinemaList);
	}

	/**
	 * メディアタイプを条件に削除された商品検索をするメソッド.
	 * 
	 * @param mediaType
	 *            メディアタイプ.
	 * @return CinemaListPage childが入ったList.
	 */
	public CinemaListPage findByDeleteMediaType(String mediaType) {
		List<Cinema> cinemaList = repository.findByDeleteMediaType(mediaType);
		return cinemaListPageAdd(cinemaList);
	}

	public long cinemaNumber() {
		return repository.cinemasNumber();
	}

	public List<Cinema> cinemaNumberSearch(int firstListNumber) {
		return repository.cinemaNumberSearch(firstListNumber);
	}

	/**
	 * @param cinemaList
	 * @return
	 */
	public CinemaListPage cinemaListPageAdd(List<Cinema> cinemaList) {
		CinemaListPage listPage = new CinemaListPage();

		List<CinemaChildPage> childSet = new ArrayList<>();
		// forの中でgetをしているため一度インスタンスを生成してsetしておく
		listPage.setChildPageList(childSet);
		for (Cinema cinema : cinemaList) {
			CinemaChildPage child = new CinemaChildPage();
			BeanUtils.copyProperties(cinema, child);
			listPage.getChildPageList().add(child);
		}
		return listPage;
	}

}
