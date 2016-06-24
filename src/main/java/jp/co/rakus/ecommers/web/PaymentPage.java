package jp.co.rakus.ecommers.web;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 決済表示画面のページクラス
 * 
 * @author rksuser
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentPage {

	/** 決済表示画面の商品一つの情報を表すチャイルドページのリスト */
	private List<PaymentChildPage> paymentChildPageList;
	/** 消費税合計 */
	private Integer totalTax;
	/** 送料 */
	private Integer postage;
	/** 請求合計金額（送料、税金込み） */
	private Integer totalPrice;

	/** 注文者名 */
	private String userName;
	/** 注文者メールアドレス */
	private String userEMail;
	/** 注文者住所 */
	private String userAddress;
	/** 注文者電話番号 */
	private String userTelephone;

	/** 注文情報のID【表示には使わないがjspのフォームで送るときに使用】 */
	private Long orderId;
}
