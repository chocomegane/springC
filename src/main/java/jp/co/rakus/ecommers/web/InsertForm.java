package jp.co.rakus.ecommers.web;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
public class InsertForm {

	/** 注文個数 */
	@NotBlank(message = "個数を入力してください")
	@Range(min = 1, max = 99, message = "1~99の範囲で入力してください")
	private Integer quantity;
	/** 映画ID */
	private long cinemaId;
}