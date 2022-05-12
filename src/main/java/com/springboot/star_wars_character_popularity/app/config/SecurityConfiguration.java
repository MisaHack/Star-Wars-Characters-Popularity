package com.springboot.star_wars_character_popularity.app.config;

import com.springboot.star_wars_character_popularity.app.filter.CustomAuthenticationFilter;
import com.springboot.star_wars_character_popularity.app.filter.CustomAuthorizationFilter;
import com.springboot.star_wars_character_popularity.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

   @Autowired
   private UserService userService;

   @Bean
   public BCryptPasswordEncoder passwordEncoder(){
      return new BCryptPasswordEncoder();
   }

   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception{
      auth.authenticationProvider(authenticationProvider());
   }

   public DaoAuthenticationProvider authenticationProvider(){
      DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
      auth.setUserDetailsService(userService);
      auth.setPasswordEncoder(passwordEncoder());
      return auth;
   }

   @Override
   public void configure(HttpSecurity http) throws Exception{
      http.csrf().disable();

      http.sessionManagement().sessionCreationPolicy(STATELESS);

      http.authorizeRequests().
              antMatchers("/api/user/registration**","/api/user/show-registration-form**","/api/characterlistdb**","/api/characterlistdb/fetch-characters**","/js/**","/css/**","/img/**").permitAll().
              anyRequest().authenticated().
              and().
              formLogin().
              loginPage("/login").
              permitAll().
              and().
              logout().
              invalidateHttpSession(true).
              clearAuthentication(true).
              logoutRequestMatcher(new AntPathRequestMatcher("/logout")).
              logoutSuccessUrl("/login?logout").
              permitAll();

      http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));

      http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
   }

   @Override
   public void configure(WebSecurity web) throws Exception {
      web.ignoring().antMatchers("/h2-console/**");
   }

   @Bean
   @Override
   public AuthenticationManager authenticationManagerBean() throws Exception{
      return super.authenticationManagerBean();
   }
}
