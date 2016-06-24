package jp.co.rakus.ecommers.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommers.domain.Cinema;
import jp.co.rakus.ecommers.domain.Order;
import jp.co.rakus.ecommers.domain.User;
import jp.co.rakus.ecommers.repository.CinemaRepository;
import jp.co.rakus.ecommers.repository.OrderCinemaRepository;
import jp.co.rakus.ecommers.repository.UserRepository;
import jp.co.rakus.ecommers.web.CinemaDetailPage;
import jp.co.rakus.ecommers.web.OrderListChildPage;
import jp.co.rakus.ecommers.web.OrderListPage;

/**
 * 注文詳細一覧を表示するためのserviceクラス.
 * 
 * @author yusuke.nakano
 *
 */
@Service
public class OrderListService {

	/** CinemaRepositoryを利用するためのDI */
	@Autowired
	private CinemaRepository repository1;
	
	@Autowired
	private OrderCinemaRepository repository2;
	
	@Autowired
	private UserRepository repository3;
	
	/**********************************************************************************************************************/
	
	// findBy()系
	
	/**
	 * @param id
	 * @return
	 */
	public Cinema findOne(long id) {
		return repository1.findOne(id);
	}
	
	public Order findByOrderNumber(String orderNumber) {
		return repository2.findByOrderNumber(orderNumber);
	}
	
	/**********************************************************************************************************************/
	
	// findAll()系
	
	/**
	 * DBからfindAllするためのメソッド. 取得してきた映画のリストを別に定義してあるPageクラスに反映させる
	 * 
	 * @return 映画のリストを持つPageクラス
	 */
	public OrderListPage findAllOfCinemaList() {
		List<Cinema> cinemaList = repository1.findAll();

		OrderListPage page = new OrderListPage();
		List<OrderListChildPage> init = new ArrayList<>();

		page.setCinemaList(init);

		for (Cinema cinema : cinemaList) {
			OrderListChildPage child = new OrderListChildPage();
			BeanUtils.copyProperties(cinema, child);
			page.getCinemaList().add(child);
		}

		return page;
	}
	
	/**
	 * DBからfindAllするためのメソッド. 取得してきた映画のリストを別に定義してあるPageクラスに反映させる
	 * 
	 * @return 映画のリストを持つPageクラス
	 */
	public OrderListPage findAllOfOrderList() {
		List<Order> orderList = repository2.findAll();
		
		OrderListPage page = new OrderListPage();
		List<OrderListChildPage> init = new ArrayList<>();

		page.setCinemaList(init);

		for (Order order : orderList) {
			OrderListChildPage child = new OrderListChildPage();
			switch (order.getStatus()) {
			case 1:
				child.setStatus("入金済み");
				break;
			case 2:
				child.setStatus("未入金");
				break;
			case 3:
				child.setStatus("発送済み");
				break;
			case 4:
				child.setStatus("キャンセル");
				break;
			default :
				child.setStatus(null);				
				break;
			}
			
			BeanUtils.copyProperties(order, child);
			if( child.getStatus() != null ) {
				User user = repository3.findById(order.getUserId());
				child.setUserName(user.getName());
				page.getCinemaList().add(child);
			}
		}
		return page;
	}

	/**********************************************************************************************************************/
	
	// save()系
	
	/**
	 * DBへのinsert, updateを行うためのメソッド.
	 * 
	 * @param cinema
	 *            映画のオブジェクト
	 */
	public void save(Cinema cinema) {
		repository1.save(cinema);
	}

	public void statusUpdate(Integer status, String orderNumber) {
		repository2.statusUpdate(status, orderNumber);
	}
	
	/**********************************************************************************************************************/
	
	// copyPage()系
	
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
	
	/**********************************************************************************************************************/	
}
