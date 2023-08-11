package com.lti.blog.config;
  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.lti.blog.security.CustomUserDetailService;
import com.lti.blog.security.JwtAuthenticationEntryPoint;
import com.lti.blog.security.JwtAuthenticationFilter;
  
  
@Configuration  
@EnableWebSecurity 
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	  
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	public static final String[] PUBLIC_URLS = {
			"/api/v1/auth/**", 
			"/v3/api-docs",
			"/v2/api-docs",
            "/swagger-resources/**",
            "/swagger-ui/**", 
            "/webjars/**"
            };
	  
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeHttpRequests()
		 .antMatchers(PUBLIC_URLS)
         .permitAll()
         .antMatchers(HttpMethod.GET).permitAll()
         .anyRequest().authenticated().and().exceptionHandling()
		.authenticationEntryPoint(this.jwtAuthenticationEntryPoint).and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.customUserDetailService).passwordEncoder(passwordEncoder());
		super.configure(auth);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	/*
	 * @Bean public FilterRegistrationBean coresFilter() {
	 * 
	 * UrlBasedCorsConfigurationSource configurationSource = new
	 * UrlBasedCorsConfigurationSource();
	 * 
	 * CorsConfiguration configuration = new CorsConfiguration();
	 * configuration.setAllowCredentials(true);
	 * configuration.addAllowedOriginPattern("*");
	 * configuration.addAllowedHeader("Authorization");
	 * configuration.addAllowedHeader("Content-Type");
	 * configuration.addAllowedHeader("Accept");
	 * configuration.addAllowedHeader("POST");
	 * configuration.addAllowedHeader("GET");
	 * configuration.addAllowedHeader("DELETE");
	 * configuration.addAllowedHeader("PUT");
	 * configuration.addAllowedHeader("OPTIONS"); configuration.setMaxAge(3600L);
	 * 
	 * configurationSource.registerCorsConfiguration("/**", configuration);
	 * 
	 * 
	 * FilterRegistrationBean filterRegistrationBean = new
	 * FilterRegistrationBean(new CorsFilter(configurationSource)); return
	 * filterRegistrationBean; }
	 */
  
	@Bean
    public FilterRegistrationBean coresFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedOriginPattern("*");
        corsConfiguration.addAllowedHeader("Authorization");
        corsConfiguration.addAllowedHeader("Content-Type");
        corsConfiguration.addAllowedHeader("Accept");
        corsConfiguration.addAllowedMethod("POST");
        corsConfiguration.addAllowedMethod("GET");
        corsConfiguration.addAllowedMethod("DELETE");
        corsConfiguration.addAllowedMethod("PUT");
        corsConfiguration.addAllowedMethod("OPTIONS");
        corsConfiguration.setMaxAge(3600L);

        source.registerCorsConfiguration("/**", corsConfiguration);

        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));

        bean.setOrder(-110);

        return bean;
    }
  }
 