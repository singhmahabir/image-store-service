/**
 * All rights reserved.
 */

package singh.mahabir.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Mahabir Singh
 *
 */
@Configurable
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, proxyTargetClass = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(WebSecurity web) throws Exception {
	web.ignoring()
		.antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security",
			"/swagger-ui.html", "/webjars/**");
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
////	http.authorizeRequests()
////		.antMatchers("/album**")
////		.hasRole("ADMIN")
////		.antMatchers("/user")
////		.hasAnyRole("ADMIN", "USER")
////		.antMatchers("/h2-console/**", "/**")
////		.permitAll()
////		.and()
////		.formLogin();
////
////	http.csrf().disable();
////	http.headers().frameOptions().disable();
//
//	http.csrf()
//		.disable()
//		.sessionManagement()
//		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//		.and()
//		.authorizeRequests()
//		.antMatchers("/oauth/token", "/h2-console/**", "/swagger-ui.html")
//		.permitAll()
//		.anyRequest()
//		.authenticated()
//		.and()
//		.formLogin();
//	http.headers().frameOptions().disable();
//    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
	DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	provider.setPasswordEncoder(bCryptPasswordEncoder());
	provider.setUserDetailsService(userDetailsService);
	return provider;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
	return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
	return super.authenticationManagerBean();
    }

}
