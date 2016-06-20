package jp.co.rakus.ecommers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jp.co.rakus.ecommers.service.LoginAdminUserService;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    @Autowired
    private static PasswordEncoder passwordEncoder;
    
    @Override
    public void configure(WebSecurity web) throws Exception {
    	web.ignoring().antMatchers("/css/**", "/img/**");
    }

    @Configuration
    @Order(1)
    public static class AdminUserSecurityConfiguration extends WebSecurityConfigurerAdapter {

        @Autowired
        @Qualifier("admin")
        private LoginAdminUserService loginAdminUserService;

        @Override
        public void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth
                .userDetailsService(loginAdminUserService)
                    .passwordEncoder(passwordEncoder);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                .antMatcher("/admin/**")
                .authorizeRequests()
                    .anyRequest()
                    .hasRole("ADMIN")
                .and()
                .formLogin()
                	.loginProcessingUrl("/admin/login")
                	.loginPage("/admin/loginForm")
                	.failureUrl("/admin/loginForm?error")
                	.defaultSuccessUrl("/admin/menu")
                	.usernameParameter("email")
                	.passwordParameter("password")
                .and()
                .logout()
                	.logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout**"))
                	.logoutSuccessUrl("/admin/loginForm")
                ;
        }
    }
}
