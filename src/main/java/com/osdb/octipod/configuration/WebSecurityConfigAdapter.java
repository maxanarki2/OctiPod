package com.osdb.octipod.configuration;

import com.osdb.octipod.jwt.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

//@Configuration
//@EnableGlobalMethodSecurity(
//        prePostEnabled = true
//        //securedEnabled = true,
//        //jsr250Enabled = true
//)
//class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {
//}

@Configuration
@EnableWebSecurity
public class WebSecurityConfigAdapter extends WebSecurityConfigurerAdapter {

	//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//				.withUser("user1").password(passwordEncoder().encode("pwd"))
//				.authorities("ROLE_USER");
//	}


	//private static final String ADMIN_ENDPOINT = "/api/v1/admin/**";
	//private static final String LOGIN_ENDPOINT = "/api/v1/auth/login";


	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}


//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}


	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf()
				//.disable()
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				.and()
				.authorizeRequests()
//				//.antMatchers("/*").permitAll()
//				.antMatchers("/api/v1/public/auth/hello-world").authenticated()
				.antMatchers("/**/hello-world").authenticated()
				.anyRequest().permitAll()
				.and()

//				//.formLogin().disable()
				.httpBasic()
		;

//		JwtTokenFilter jwtTokenFilter = new JwtTokenFilter(//jwtTokenProvider
//		);
		httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

		//http.addFilterAfter(new CustomFilter(), BasicAuthenticationFilter.class);
	}

//	@Autowired
//	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	JwtTokenFilter jwtTokenFilter;

//	@Bean
//	@Override
//	public UserDetailsService userDetailsService() {
//		UserDetails user1 =
//				User.withDefaultPasswordEncoder()
//						.username("user")
//						.password("pwd")
//						.roles("USER")
//						.build();
//		UserDetails user2 =
//				User.withDefaultPasswordEncoder()
//						.username("user2")
//						.password("pwd")
//						.roles("USER")
//						.build();
//
//		return new InMemoryUserDetailsManager(user1, user2);
//	}

}