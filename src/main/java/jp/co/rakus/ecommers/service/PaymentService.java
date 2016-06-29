package jp.co.rakus.ecommers.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.rakus.ecommers.domain.Cinema;
import jp.co.rakus.ecommers.domain.Order;
import jp.co.rakus.ecommers.domain.OrderItem;
import jp.co.rakus.ecommers.domain.User;
import jp.co.rakus.ecommers.repository.CinemaRepository;
import jp.co.rakus.ecommers.repository.OrderCinemaRepository;
import jp.co.rakus.ecommers.web.PaymentChildPage;
import jp.co.rakus.ecommers.web.PaymentPage;

/**
 * 決済機能を提供するサービスクラス.
 * 
 * @author kohei.sakata
 *
 */
@Service
public class PaymentService {
	public static final double TAX_RATIO = 0.08;
	public static final int POSTAGE = 500;

	@Autowired
	private OrderCinemaRepository orderRepository;
	@Autowired
	private CinemaRepository cinemaRepository;

	/**
	 * カート内の商品一覧表示
	 * 
	 * @param user
	 * @return page情報
	 */
	public PaymentPage createPaymentPage(User user) {
		// オーダーの情報を取得
		// Order order = orderRepository.searchOrder(user);
		Order order = orderRepository.findCart(user);
		System.out.println(order);
		// チャイルドページのリストを作成と価格計算
//		int totalPrice = 0;
		List<PaymentChildPage> paymentChildPageList = new ArrayList<PaymentChildPage>();
		for (OrderItem orderItem : order.getOrderCinemaList()) {
			PaymentChildPage paymentChildPage = new PaymentChildPage();
			// cinemaテーブルから商品名と商品一つの値段を取得
			Long cinemaId = orderItem.getCinemaId();
			Cinema orderedCinema = cinemaRepository.findOne(cinemaId);
			paymentChildPage.setOrderCinemaTitle(orderedCinema.getTitle());
			int price = orderedCinema.getPrice();
			paymentChildPage.setPrice(price);
			// orderItemテーブルから個数を取得
			int quantity = orderItem.getQuantity();
			paymentChildPage.setQuantity(quantity);
			// 商品の値段×個数で小計を算出と全合計に加算
			int subTotalPrice = quantity * price;
			paymentChildPage.setSubTotalPrice(subTotalPrice);
//			totalPrice += subTotalPrice;
//			// 商品の小計（税込み）を算出
//			paymentChildPage.setPretaxTotalPrice((int) (subTotalPrice * TAX_RATIO));

			paymentChildPageList.add(paymentChildPage);
		}
		Integer totalPrice = order.getTotalPrice();
		Integer totalTax = (int) (totalPrice * TAX_RATIO);
		Integer postage = POSTAGE;
		Integer AllTotalPrice = totalTax + postage + totalPrice;
		// userテーブルから注文者情報を取得
		PaymentPage page = new PaymentPage(paymentChildPageList, totalTax, postage, AllTotalPrice, user.getName(),
				user.getEmail(), user.getAddress(), user.getTelephone(), order.getId());
		return page;

	}

	/**
	 * 注文情報のステータスを1(未入金）、日付に現在の日時へ更新する
	 * 
	 * order_numberの更新処理を追加
	 * 
	 * @param orderId
	 *            注文情報のid
	 * @return 成功したらtrueを失敗したらfalseを返す
	 */
	@Transactional
	public synchronized Boolean updateOrder(Long orderId) {
		
		/*******************************************************************************/
		/** 追加分 */
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat();
		
		sdf.applyPattern("yyyyMMdd");
		Order lastOrder = orderRepository.findLastOrder(orderId);
		int orderNumberAfter = 0;
		if(lastOrder != null){
			orderNumberAfter = Integer.parseInt(lastOrder.getOrderNumber().substring(8));
		}
		orderNumberAfter = orderNumberAfter + 1;
		String number = String.format("%1$06d", orderNumberAfter);
		String orderNumber = sdf.format(cal.getTime()) + number;
		orderRepository.updateOrderNumber(orderId, orderNumber);
		/*******************************************************************************/
		
		return orderRepository.updateStatus(orderId);
	}
}
