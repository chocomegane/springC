package jp.co.rakus.ecommers.web;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class CinemaForm {
	/** 主キー */
	private Long id;
	/** 映画名 */
	@Size(min=0, max= 127, message= "文字数上限はは127文字です")
	@NotEmpty(message = "タイトルは必須です")
	private String title;
	/** 価格 */
	@Max(value=500000, message= "500001以上まだは文字は入力はできません" )
	@Min(value =1 ,message = "0または文字入力はできません")
	@NotNull(message = "価格は必須です")
	private String price;//メッセージ：価格の上限５０００００にします
	/** ジャンル */
	@Size(min=0, max= 127, message= "文字数上限はは127文字です")
	@NotEmpty(message = "ジャンルは必須です")
	private String genre;
	/** 上映時間(単位:分) */
	@Max(value=601, message= "601以上まだは文字は入力はできません" )
	@Min(value =1 ,message = "0をまたは文字は入力はできません")
	@NotNull(message = "上映時間は必須です")
	private String time;  //メッセージ：数字じゃない場合err 
	/** 公開日 */
	private String releaseDate; //メッセージ：date型いがいはerr
	/** メディアタイプ */
	@NotEmpty(message = "メディアタイプは必須です")
	private String mediaType;
	/** 制作会社 */
	@Size(min=0, max= 127, message= "文字数上限はは127文字です")
	@NotEmpty(message = "制作会社は必須です")
	private String company;
	/** 監督 */
	@Size(min=0, max= 127, message= "文字数上限はは127文字です")
	@NotEmpty(message = "監督は必須です")
	private String directedBy;
	/** レーティング */
	@NotEmpty(message = "レーディングは必須です")
	private String rating;
	/** 概要(ストーリー) */
	@NotEmpty(message = "ストーリーは必須です")
	private String description;
	/** イメージ画像 */
	private MultipartFile imagePath;
	/** フラグ（削除） */
	private boolean deleted;
	
	public Integer getIntTime()
	{
		return Integer.parseInt(time);
	}
	
	public Integer getIntPrice()
	{
		return Integer.parseInt(price);
	}
}
