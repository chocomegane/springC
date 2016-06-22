package jp.co.rakus.ecommers.web;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ブラウザに表示する商品詳細のオブジェクトをListに格納したPageクラス.
 * 
 * @author takeshi.fujimoto
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartListPage {

	private List<CartListChildPage> cartListChildPage;
}
