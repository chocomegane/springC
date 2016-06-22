package jp.co.rakus.ecommers.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommers.domain.Cinema;
import jp.co.rakus.ecommers.domain.Order;
import jp.co.rakus.ecommers.repository.OrderCinemaRepository2nd;
import jp.co.rakus.ecommers.web.CinemaDetailPage;
import jp.co.rakus.ecommers.web.OrderListChildPage;
import jp.co.rakus.ecommers.web.OrderListPage;

/**
 * 注文詳細一覧を表示するためのServiceクラス.
 * 藤本君がOrderCinemaServiceを編集中なので管理者側が<br>
 * 注文詳細画面を表示する操作を行うためにこのクラスを作った。
 * 藤本君がOrderCinemaServiceを作成し終えたらそちらに統合する予定
 * @author yusuke.nakano
 *
 */
@Service
public class OrderListService2nd {

	/** CinemaRepositoryを利用するためのDI */
	@Autowired
	private OrderCinemaRepository2nd repository;
	
	/**
	 * DBへのinsert, updateを行うためのメソッド.
	 * 
	 * @param cinema
	 *            映画のオブジェクト
	 */
//	public void save(Cinema cinema) {
//		repository.save(cinema);
//	}

	/**
	 * DBからfindAllするためのメソッド. 取得してきた映画のリストを別に定義してあるPageクラスに反映させる
	 * 
	 * @return 映画のリストを持つPageクラス
	 */
	public OrderListPage findAll() {
		List<Order> orderList = repository.findAll();

		OrderListPage page = new OrderListPage();
		List<OrderListChildPage> init = new ArrayList<>();

		page.setCinemaList(init);

		for (Order order : orderList) {
			OrderListChildPage child = new OrderListChildPage();
			BeanUtils.copyProperties(order, child);
			page.getCinemaList().add(child);
		}

		return page;
	}
		
	public Order findByUserId(String userId) {
		return repository.findByUserId(userId);
	}

	public void statusUpdate(Integer status, String orderNumber) {
		repository.statusUpdate(status, orderNumber);
	}
	/**
	 * @param id
	 * @return
	 */
//	public Cinema findOne(long id) {
//		return repository.findOne(id);
//	}

	/**
	 * Cinemaの情報をCinemaDetailPageにコピー.
	 * 
	 * @param cinema
	 *            Cinemaの詳細情報
	 * @return Cinemaの内容をコピーしたCinemaDetailPage
	 */
	public CinemaDetailPage copyCinemaToPage(Cinema cinema) {
		CinemaDetailPage cinemaDetail = new CinemaDetailPage();
		BeanUtils.copyProperties(cinema, cinemaDetail);
		return cinemaDetail;
	}
}
