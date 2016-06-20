package jp.co.rakus.ecommers.web;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author takeshi.fujimoto
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CinemaDetailPage {

	/** 主キー */
	private Long id;
	/** 映画名 */
	private String title;
	/** 価格 */
	private Integer price;
	/** ジャンル */
	private String genre;
	/** 上映時間(単位:分) */
	private Integer time;
	/** 公開日 */
	private Date releaseDate;
	/** メディアタイプ */
	private String mediaType;
	/** 制作会社 */
	private String company;
	/** 監督 */
	private String directedBy;
	/** レーティング */
	private String rating;
	/** 概要(ストーリー) */
	private String description;
	/** イメージ画像 */
	private String imagePath;
	
}
