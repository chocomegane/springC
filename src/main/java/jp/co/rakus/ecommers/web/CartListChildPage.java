package jp.co.rakus.ecommers.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * カートにある商品の情報を持つエンティティクラス.
 * 
 * @author takeshi.fujimoto
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartListChildPage {

	/** 商品ID */
	private long itemId;
	/** タイトル */
	private String title;
	/** 価格 */
	private Integer price;
	/** 注文個数 */
	private Integer quantity;
}
