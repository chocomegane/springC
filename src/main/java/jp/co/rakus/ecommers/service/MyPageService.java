package jp.co.rakus.ecommers.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommers.domain.Order;
import jp.co.rakus.ecommers.domain.User;
import jp.co.rakus.ecommers.repository.OrderCinemaRepository;
import jp.co.rakus.ecommers.repository.UserRepository;
import jp.co.rakus.ecommers.web.OrderListChildPage;
import jp.co.rakus.ecommers.web.OrderListPage;

/**
 * userページ関連のserviceです
 * 
 * @author rakus
 *
 */
@Service
public class MyPageService {
	// 依存性の注入
	@Autowired
	private UserRepository repository;
	@Autowired
	private OrderCinemaRepository repository2;
	@Autowired
	private UserRepository repository3;

	/**
	 * レポジトリのメソッドを呼び出します
	 * 
	 * @param id
	 * @return
	 */
	public User findById(Long id) {
		return repository.findById(id);

	}

	/** ユーザー情報を更新します */
	public void userUpdate(String name, String email, String telephone, String address, long id) {
		repository.updetaUser(name, email, telephone, address, id);
	}

	/**
	 * パスワードを更新するためのレポジトリを呼び出します。
	 * 
	 * @param password
	 *            新規パスワード
	 * @param id
	 *            Id
	 */
	public void passWordUpdate(String password, long id) {
		repository.passWordUpdate(password, id);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * DBからfindAllするためのメソッド. 取得してきた映画のリストを別に定義してあるPageクラスに反映させる
	 * 
	 * @param id
	 * @return
	 */
	public OrderListPage findByOne(long id) {

		List<Order> orderList = repository2.findById(id);
		List<OrderListChildPage> init = new ArrayList<>();
		OrderListPage page = new OrderListPage();

		page.setCinemaList(init);

		for (Order order : orderList) {
			OrderListChildPage child = new OrderListChildPage();
			switch (order.getStatus()) {
			case 1:
				child.setStatus("未入金");
				break;
			case 2:
				child.setStatus("入金済み");
				break;
			case 3:
				child.setStatus("発送済み");
				break;
			case 4:
				child.setStatus("キャンセル");
				break;
			default:
				child.setStatus(null);
				break;
			}
			BeanUtils.copyProperties(order, child);
			if (child.getStatus() != null) {
				System.out.println(order);
				User user = repository3.findById(id);
				child.setUserName(user.getName());
				page.getCinemaList().add(child);
			}

		}
		return page;
	}
	
}
