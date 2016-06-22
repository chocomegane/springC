package jp.co.rakus.ecommers.web;

import lombok.Data;

@Data
public class CartListChildPage {

	/** タイトル */
	private String title;
	/** 価格 */
	private Integer price;
	/** 注文個数 */
	private Integer quantity;
}
