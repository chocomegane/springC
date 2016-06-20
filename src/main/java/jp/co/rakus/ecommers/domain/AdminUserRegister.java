package jp.co.rakus.ecommers.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminUserRegister {
	
	private String name;
	private String password;
	private String email;
	

}