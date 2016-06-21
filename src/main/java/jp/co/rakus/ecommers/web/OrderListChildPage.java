package jp.co.rakus.ecommers.web;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderListChildPage {
	/** ID */
	private long id;
	/** オーダーナンバー */
	private String orderNumber;
	/** ユーザID */
	private long userId;
	/** ステータス */
	private Integer status;
	/** 小計 */
	private Integer totalPrice;
	/** 最終購入日 */
	private Timestamp date;
}
