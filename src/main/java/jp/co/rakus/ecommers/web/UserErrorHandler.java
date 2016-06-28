//package jp.co.rakus.ecommers.web;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerExceptionResolver;
//import org.springframework.web.servlet.ModelAndView;
//
//@Component
//public class UserErrorHandler implements HandlerExceptionResolver {
////    private static final Logger logger = 
////            LoggerFactory.getLogger(GlobalExceptionResolver.class);
// 
//    public ModelAndView resolveException(
//                        HttpServletRequest request,
//                        HttpServletResponse response,
//                        Object object,
//                        Exception ex) {
// 
////        logger.error("例外をキャッチしました。", ex);
// 
//        ModelAndView mav = new ModelAndView();
// 
//        // JSPに表示するメッセージをセットします。
//        mav.addObject("message", "予期せぬエラーが発生しました。" +
//                        " 詳細：【" + ex + "】");
// 
//        // 遷移先のJSPを指定します。(error.jspに遷移します。)
//        mav.setViewName("error");
//        return mav;
// 
//    }
//}
