package project;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
		.passwordEncoder(passwordEncoder())
				.usersByUsernameQuery("select username,password,enabled from users where username = ?")
				.authoritiesByUsernameQuery(
						"select username, name from users u inner join roles r on u.role_id = r.id where username= ?");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/admin/menu/**").access("hasAnyRole('ROLE_ADMIN')")
		.antMatchers("/admin/product/**").access("hasAnyRole('ROLE_ADMIN')")
		.antMatchers("/admin/news/**").access("hasAnyRole('ROLE_ADMIN')")
		.antMatchers("/admin/newsdetail/**").access("hasAnyRole('ROLE_ADMIN')")
		.antMatchers("/admin/user/**").access("hasAnyRole('ROLE_ADMIN')")
		.antMatchers("/admin/bill/**").access("hasAnyRole('ROLE_ADMIN','ROLE_MODIFIER')")
		.antMatchers("/admin/comment/**").access("hasAnyRole('ROLE_ADMIN','ROLE_MODIFIER')")
		.antMatchers("/admin/contact/**").access("hasAnyRole('ROLE_ADMIN','ROLE_MODIFIER')")
		.antMatchers("/").permitAll()
		.and()
		.formLogin()
		.usernameParameter("username")
		.passwordParameter("password")
		.loginPage("/auth/admin/login")
		.loginProcessingUrl("/auth/admin/login")
		.failureUrl("/auth/admin/login?msg=Error")
		.defaultSuccessUrl("/admin/index",false)
		.and()
		.logout()
		.logoutUrl("/auth/admin/logout")
		.logoutSuccessUrl("/auth/admin/login")
		.and()
		.exceptionHandling()
		.accessDeniedPage("/erroradmin404")
		.and().csrf().disable();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
