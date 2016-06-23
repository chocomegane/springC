package jp.co.rakus.ecommers.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * カートに入れた商品の情報を表すDomain.
 * 
 * @author takeshi.fujimoto
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

	/** ID */
	private long id;
	/** 映画ID */
	private long cinemaId;
	/** オーダーID */
	private long orderId;
	/** 個数 */
	private Integer quantity;
	
}
