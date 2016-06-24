package jp.co.rakus.ecommers.web;

import java.util.Date;

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
	private Integer userId;
	/** ユーザ名 */
	private String userName;
	/** ステータス */
	private String status;
	/** 小計 */
	private Integer totalPrice;
	/** 最終購入日 */
	private Date date;
	// private Timestamp date;
}
