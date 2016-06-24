package jp.co.rakus.ecommers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommers.domain.OrderItem;
import jp.co.rakus.ecommers.repository.OrderItemRepository;

/**
 * OrderItemRepositoryを扱うserviceクラス.
 * @author yusuke.nakano
 *
 */
@Service
public class OrderItemService {

	@Autowired
	private OrderItemRepository repository;
	
	public List<OrderItem> findById(Long id) {
		return repository.findById(id);
	}
	
}
