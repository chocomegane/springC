package jp.co.rakus.ecommers.web;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CinemaChildPageを入れるクラス.
 * 
 * @author tsubasa.kaneko
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CinemaListPage {

	private List<CinemaChildPage> childPageList;
	
}
