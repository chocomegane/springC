package jp.co.rakus.ecommers.web;


import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BulkCinemaForm {
	/** csv*/
	private MultipartFile csvFile;
}
