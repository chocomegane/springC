package jp.co.rakus.ecommers.web;

import java.util.List;

import lombok.Data;

/**
 * ブラウザに表示する商品詳細のオブジェクトをListに格納したPageクラス.
 * 
 * @author takeshi.fujimoto
 *
 */
@Data
public class CartListPage {

	private List<CartListChildPage> cartListChildPage;
}
