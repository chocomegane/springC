package jp.co.rakus.ecommers.web;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
public class CartForm {

	/** 映画ID */
	private long cinemaId;

	/** 個数 */
	@NotNull(message = "注文個数を入力してください")
	@Range(min = 1, max = 99, message = "1～99の数値を入力してください")
	private Integer quantity;
}
