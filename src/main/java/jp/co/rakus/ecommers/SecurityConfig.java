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

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig {

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
            auth
                .userDetailsService(loginAdminUserService)
                    .passwordEncoder(new BCryptPasswordEncoder());
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
            	.antMatcher("/admin/**")
                .authorizeRequests()
                	.antMatchers("/admin/loginForm").permitAll()
                    .anyRequest().hasRole("ADMIN")
                .and()
                .formLogin()
                	.loginProcessingUrl("/admin/login")
                	.loginPage("/admin/loginForm")
                	.failureUrl("/admin/loginForm?error")
                	.defaultSuccessUrl("/admin/menu",true)
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
