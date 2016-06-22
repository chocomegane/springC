package jp.co.rakus.ecommers.web;

import java.sql.Timestamp;
import java.util.List;

import jp.co.rakus.ecommers.domain.OrderItem;
import lombok.Data;

/**
 * Orderを表すDomain.
 * 
 * @author takeshi.fujimoto
 *
 */
@Data
public class OrderForm {
	/** ID */
	private long id;
	/** オーダーナンバー */
	private String orderNumber;
	/** ユーザID */
	private long userId;
	/** ステータス */
	private Integer status;
	private List<OrderItem> orderCinemaList;
	/** 小計 */
	private Integer totalPrice;
	/** 最終購入日 */
	private Timestamp date;
}
