package jp.co.rakus.ecommers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jp.co.rakus.ecommers.service.LoginAdminUserService;
import jp.co.rakus.ecommers.service.LoginUserService;

/**
 * ログイン認証用JavaConfig.
 * @author kohei.sakata
 *
 */
@Configuration
@EnableWebMvcSecurity
public class SecurityConfig {

    /**
     * 管理者認証用JavaConfig.
     * @author kohei.sakata
     *
     */
    @Configuration
    @Order(1)
    public static class AdminUserSecurityConfiguration extends WebSecurityConfigurerAdapter {
        @Autowired
        private LoginAdminUserService loginAdminUserService;
        @Override
        public void configure(WebSecurity web) throws Exception {
        	web.ignoring().antMatchers("/css/**", "/img/**");
        }
        @Override
        public void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(loginAdminUserService).passwordEncoder(new BCryptPasswordEncoder());
        }
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
            	.antMatcher("/admin/**")
                .authorizeRequests()
                    .anyRequest().hasRole("ADMIN")
                .and()
                .formLogin()
                	.loginProcessingUrl("/admin/doAuth")
                	.loginPage("/admin/loginForm")
//                	.failureUrl("/admin/loginForm?error")
                	.failureUrl("/admin/loginError")
                	.defaultSuccessUrl("/admin/menu",true)
                	.usernameParameter("email")
                	.passwordParameter("password")
                	.permitAll()
                .and()
                .logout()
                	.logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout**"))
                	.logoutSuccessUrl("/admin/loginForm")
                ;
        }
    }
    
    /**
     * ユーザー認証用JavaConfig.
     * @author kohei.sakata
     *
     */
    @Configuration
    @Order(2)
    public static class UserSecurityConfiguration extends WebSecurityConfigurerAdapter {
        @Autowired
        private LoginUserService loginUserService;
        @Override
        public void configure(WebSecurity web) throws Exception {
        	web.ignoring().antMatchers("/css/**", "/img/**");
        }
        @Override
        public void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(loginUserService).passwordEncoder(new BCryptPasswordEncoder());
        }
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
            	.antMatcher("/cinemaShop/**")
                .authorizeRequests()
                	.antMatchers("/cinemaShop/**Payment**").hasRole("USER")
                    .anyRequest().permitAll()
                .and()
                .formLogin()
                	.loginProcessingUrl("/cinemaShop/doAuth")
                	.loginPage("/cinemaShop/loginForm")
//                	.failureUrl("/cinemaShop/loginForm?error")
                	.failureUrl("/cinemaShop/loginError")
                	.defaultSuccessUrl("/cinemaShop/displayList",false)
                	.usernameParameter("email")
                	.passwordParameter("password")
                	.permitAll()
                .and()
                .logout()
                	.logoutRequestMatcher(new AntPathRequestMatcher("/cinemaShop/logout**"))
                	.logoutSuccessUrl("/cinemaShop/displayList")
                ;
        }
    }
}
