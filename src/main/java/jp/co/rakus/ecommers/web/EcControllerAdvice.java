package jp.co.rakus.ecommers.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.Order;
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
public class EcControllerAdvice {

    /**
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex,
            HttpServletRequest request) {
        String referer = request.getServletPath();
        System.out.println(referer);
        // どのURLで例外が発生したかを判別してViewを振り分け
        if (referer.contains("admin")) {
            ModelAndView mav = new ModelAndView("adminError");
            mav.addObject("message", ex.getMessage());
            ex.printStackTrace();
            return mav;
        } else {
            ModelAndView mav = new ModelAndView("userError");
            mav.addObject("message", ex.getMessage());
            ex.printStackTrace();
            return mav;
        }
    }
}
