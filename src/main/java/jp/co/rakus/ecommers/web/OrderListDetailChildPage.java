package jp.co.rakus.ecommers.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderListDetailChildPage {

	private String taitle;
	private Integer price;
	private Integer quantity;
	private Integer totalPrice;

}
