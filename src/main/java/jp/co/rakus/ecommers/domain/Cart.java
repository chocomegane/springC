package jp.co.rakus.ecommers.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 未購入の商品情報をもつエンティティ.
 * 
 * @author takeshi.fujimoto
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

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
	private Date date;
	/** カート内にある映画のID */
	private long orderCinemaId;
	/** 映画ID */
	private long cinemaId;
	/** 注文個数 */
	private Integer quantity;
}
