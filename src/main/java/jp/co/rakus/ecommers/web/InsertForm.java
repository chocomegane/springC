package jp.co.rakus.ecommers.web;

import lombok.Data;

@Data
public class InsertForm {

	/** 注文個数 */
	private Integer quantity;
	/** 映画ID */
	private long cinemaId;
	/** ユーザID */
	private long userId;
}