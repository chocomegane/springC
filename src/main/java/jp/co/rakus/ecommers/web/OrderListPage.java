package jp.co.rakus.ecommers.web;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderListPage {
	private List<OrderListChildPage> cinemaList;
}
