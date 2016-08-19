package jp.co.rakus.ecommers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommers.domain.User;
import jp.co.rakus.ecommers.repository.UserRepository;

@Service
public class UserRegisterService {
	@Autowired
	private UserRepository repository;

	public void userInsert(User user) {
		repository.userInsert(user);
	}

	public User findByEmail(String email) {
		return repository.findByEmail(email);
	}

}
