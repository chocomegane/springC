package jp.co.rakus.ecommers.web;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderListDetailPage {

	private String orderNumber;
	private String userName;
	private String email;
	private String address;
	private String telephone;

	private List<OrderListDetailChildPage> childPage;

	private Integer subTotal;
	private Integer tax;
	private Integer grandTotal;
	private String status;
	private Map<Integer, String> statusMap;
}
