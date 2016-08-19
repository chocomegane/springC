package jp.co.rakus.ecommers.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author kohei.sakata
 *
 */
@ControllerAdvice
@Component
@Order(0)
public class EcControllerAdvicer {

	/**
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(AccessDeniedException.class)
	@Order(2)
	public ModelAndView handleAccessDeniedException(AccessDeniedException ex, HttpServletRequest request) {
		System.err.println("AccessExceptionHandler");
		String referer = request.getServletPath();
		System.out.println(referer);
		// どのURLで例外が発生したかを判別してViewを振り分け
		ModelAndView mav;
		if (referer.contains("admin")) {
			mav = new ModelAndView("userAccessError");
		} else {
			mav = new ModelAndView("adminAccessError");
		}
		mav.addObject("message", ex.getMessage());
		ex.printStackTrace();
		return mav;
	}

	/**
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@Order(1)
	public ModelAndView handleException(Exception ex, HttpServletRequest request) {
		System.err.println("ExceptionHandler");
		String referer = request.getServletPath();
		System.out.println(referer);
		// どのURLで例外が発生したかを判別してViewを振り分け
		ModelAndView mav;
		if (referer.contains("admin")) {
			mav = adminErrorPage(ex);
		} else {
			mav = userErrorPage(ex);
		}
		mav.addObject("message", ex.getMessage());
		ex.printStackTrace();
		return mav;
	}

	/**
	 * 例外の種類によって表示するエラーページを選択するメソッド． 管理者用。
	 * 
	 * @param ex
	 * @return
	 */
	private ModelAndView adminErrorPage(Exception ex) {
		ModelAndView mav;
		mav = new ModelAndView("adminError");
		if (ex instanceof NotFoundException) {
			mav = new ModelAndView("notFound");
		} else {
			mav = new ModelAndView("userError");
		}
		return mav;
	}

	/**
	 * 例外の種類によって表示するエラーページを選択するメソッド． 利用者用。
	 * 
	 * @param ex
	 * @return
	 */
	private ModelAndView userErrorPage(Exception ex) {
		ModelAndView mav;
		if (ex instanceof NotFoundException) {
			mav = new ModelAndView("notFound");
		} else {
			mav = new ModelAndView("userError");
		}
		return mav;
	}

}
