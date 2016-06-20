package jp.co.rakus.ecommers.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommers.domain.Cinema;
import jp.co.rakus.ecommers.repository.CinemaRepository;
import jp.co.rakus.ecommers.web.CinemaDetailPage;

/**
 * 
 * @author yusuke.nakano
 *
 */
@Service
public class CinemaService {
	
	/** CinemaRepositoryを利用するためのDI */
	@Autowired
	private CinemaRepository repository;
	
	/**
	 * @return
	 */
	public List<Cinema> findAll(){
		return repository.findAll();
	}
	
	/**
	 * @param id
	 * @return
	 */
	public Cinema findOne(long id) {
		return repository.findOne(id);
	}
	
	public CinemaDetailPage copyCinemaToPage(Cinema cinema) {
		CinemaDetailPage cinemaDetail = new CinemaDetailPage();
		BeanUtils.copyProperties(cinema, cinemaDetail);
		return cinemaDetail;
	}
}
