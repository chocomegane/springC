package jp.co.rakus.ecommers.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 決済確定表示画面の商品一つの情報を表すチャイルドページ
 * 
 * @author rksuser
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentChildPage {
	/** 商品名 */
	private String orderCinemaTitle;
	/** 商品の値段（税抜き） */
	private Integer price;
	/** 商品の個数 */
	private Integer quantity;
	/** 小計（税抜き） */
	private Integer subTotalPrice;
	// /** 小計（税あり） */
	// private Integer pretaxTotalPrice;
}