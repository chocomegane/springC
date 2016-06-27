package jp.co.rakus.ecommers.web;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
public class InsertForm {

	/** 注文個数 */
	@Range(min = 1, max = 99)
	private Integer quantity;
	/** 映画ID */
	private long cinemaId;
}