package jp.co.rakus.ecommers.web;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class CinemaForm {
	/** 主キー */
	private Long id;
	/** 映画名 */
	@NotEmpty(message = "タイトルは必須です")
	private String title;
	/** 価格 */
	@NotNull(message = "価格は必須です")
	private Integer price;
	/** ジャンル */
	@NotEmpty(message = "ジャンルは必須です")
	private String genre;
	/** 上映時間(単位:分) */
	@NotNull(message = "上映時間は必須です")
	private Integer time;
	/** 公開日 */
	@NotEmpty(message = "公開日は必須です")
	private String releaseDate;
	/** メディアタイプ */
	@NotEmpty(message = "メディアタイプは必須です")
	private String mediaType;
	/** 制作会社 */
	@NotEmpty(message = "制作会社は必須です")
	private String company;
	/** 監督 */
	@NotEmpty(message = "監督は必須です")
	private String directedBy;
	/** レーティング */
	@NotEmpty(message = "レーディングは必須です")
	private String rating;
	/** 概要(ストーリー) */
	@NotEmpty(message = "ストーリーは必須です")
	private String description;
	/** イメージ画像 */
	// @NotEmpty(message = "画像入力は必須です")
	private MultipartFile imagePath;
	/** フラグ（削除） */
	private boolean deleted;
}
