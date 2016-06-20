package jp.co.rakus.ecommers.repository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.rakus.ecommers.domain.Cinema;

/**
 * 
 * @author yusuke.nakano
 *
 */
@Repository
public class CinemaRepository {

	/** NamedParameterJdbcTemplateを利用するためのDI */
	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * データベースからホテルのテーブルに接続し、カラムを得る
	 */
	private static final RowMapper<Cinema> cinemaRowMapper = (rs, i) -> {
		Integer id = rs.getInt("id");
		String title = rs.getString("title");
		Integer price = rs.getInt("price");
		String genre = rs.getString("genre");
		Integer time = rs.getInt("time");
		Date releaseDate = rs.getTimestamp("release_date");
		String mediaType = rs.getString("media_type");
		String company = rs.getString("company");
		String directedBy = rs.getString("directed_by");
		String rating = rs.getString("rating");
		String description = rs.getString("description");
		return new Cinema(id, title, price, genre, time, releaseDate, mediaType, company, directedBy, rating, description);
	};

	public List<Cinema> findAll() {
		String sql = "SELECT id, title, price, genre, time, release_date, media_type, company, directed_by, rating, description FROM cinema ORDER BY title";
		List<Cinema> cinemaList = template.query(sql, cinemaRowMapper);
		return cinemaList;
	}
}