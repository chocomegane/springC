package jp.co.rakus.ecommers.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ecommers.domain.Order;
import jp.co.rakus.ecommers.service.OrderListService;

/**
 * 注文詳細一覧を表示するためのControllerクラス.
 * 
 * @author yusuke.nakano
 *
 */
@Controller
@Transactional
@RequestMapping(value = "/admin")
public class OrderListController {

	/** OrderListServiceを利用するためのDI */
	@Autowired
	private OrderListService service;

	/**
	 * 注文一覧ページを表示する
	 * 
	 * @param model
	 *            requestスコープを扱うための変数
	 * @return orderList.jspへフォワード
	 */
	@RequestMapping("/orderList")
	public String output(Model model) {

		OrderListPage page = service.findAllOfOrderList();
		System.out.println("err");
		if (!page.getCinemaList().isEmpty()) {
			model.addAttribute("page", page);
			model.addAttribute("flag", true);
			return "orderList";
		} else {
			System.out.println("eles");
			model.addAttribute("flag", false);
			return "orderList";
		}

	}

	@RequestMapping("csvDownload")
	public String csvDownload(HttpServletResponse response, Model model) {

		// 文字コードと出力するCSVファイル名を設定
		response.setContentType(MimeTypeUtils.APPLICATION_OCTET_STREAM_VALUE + ";charset=SJIS");
		response.setHeader("Content-Disposition", "attachment; filename=\"orderList.csv\"");

		// try-with-resources文を使うことでclose処理を自動化
		OrderListPage orderListPage = service.findAllOfOrderList();
		// OrderListChildPage orderListChildPage =
		List<OrderListChildPage> orderList = orderListPage.getCinemaList();
		try (PrintWriter pw = response.getWriter()) {
			service.findAllOfOrderList();
			for (int i = 0; i < orderList.size(); i++) {
				Date date = orderList.get(i).getDate();
				String orderNumber = orderList.get(i).getOrderNumber();
				String status = orderList.get(i).getStatus();
				Integer totalPrice = orderList.get(i).getTotalPrice();
				String userName = orderList.get(i).getUserName();

				String outputString = orderNumber + "," + date + "," + userName + "," + status + "," + totalPrice
						+ "\r\n";

				// CSVファイル内部に記載する形式で文字列を設定

				// CSVファイルに書き込み
				if (i == 0) {
					pw.print("注文番号,日付,利用者名,現在のステータス,総計（税込み）\r\n");
				}
				pw.print(outputString);

			}
		} catch (IOException e) {

			e.printStackTrace();
			String errMessage = "失敗しました";
			model.addAttribute("errMessage", errMessage);
		}
		return "orderList";

	}
}