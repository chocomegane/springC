package jp.co.rakus.ecommers.domain;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Orderを表すDomain.
 * 
 * @author takeshi.fujimoto
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
	/** ID */
	private long id;
	/** オーダーナンバー */
	private String orderNumber;
	/** ユーザID */
	private long userId;
	/** ステータス */
	private Integer status;
	/** 注文商品リスト */
	private List<OrderItem> orderCinemaList;
	/** 小計 */
	private Integer totalPrice;
	/** 最終購入日 */
	private Date date;

}
