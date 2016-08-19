package jp.co.rakus.ecommers.service;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommers.domain.LoginUser;
import jp.co.rakus.ecommers.domain.User;
import jp.co.rakus.ecommers.repository.UserRepository;

@Service
public class UserService {
	/** Longに変換した時に桁あふれせずに変換できる16進数の最大桁数 */
	private static final int LONG_DIGIT = 15;

	@Autowired
	private HttpSession session;

	@Autowired
	private CartService cartService;

	@Autowired
	private UserRepository repository;

	public User findById(long id) {
		return repository.findById(id);
	}

	/**
	 * Cookieの情報とログインユーザーの情報を統合するメソッド.
	 * 
	 * @param principal
	 * @param cookie
	 * @return
	 */
	public User chkUser(Principal principal, String cookie) {
		User user = null;
		try {
			if (principal == null) {
				user = new User();
				long guestid = makeUserId(cookie);
				user.setId(guestid);
				user.setName("ゲスト");
			} else {
				LoginUser loginUser = (LoginUser) ((Authentication) principal).getPrincipal();
				user = loginUser.getUser();
				if (loginUser.getGuestId() == null) {
					String jsessionId = (String) session.getAttribute("guestid");
					long guestId = makeUserId(jsessionId);
					loginUser.setGuestId(guestId);
					cartService.joinCart(user, guestId);
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw e;
		}
		return user;
	}

	/**
	 * JSESSIONIDからDBに登録可能なUserIDを生成するメソッド.
	 * 
	 * @param jsessionId
	 * @return
	 */
	private long makeUserId(String jsessionId) {
		int digit = jsessionId.length() - LONG_DIGIT;
		digit = digit > 0 ? digit : 0;
		long guestId = -Long.parseLong(jsessionId.substring(digit), 16);
		return guestId;
	}
}
