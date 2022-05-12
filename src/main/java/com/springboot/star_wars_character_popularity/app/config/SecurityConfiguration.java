package com.springboot.star_wars_character_popularity.app.config;

import com.springboot.star_wars_character_popularity.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
      http.authorizeRequests()
              .antMatchers("/js/**").permitAll()
              .antMatchers("/css/**").permitAll()
              .antMatchers("/img/**").permitAll()
              .antMatchers("/api/user/registration**").permitAll()
              .antMatchers("/api/user/show-registration-form**").permitAll()
              .antMatchers("/api/characterlistdb**").permitAll()
              .antMatchers("/api/characterlistdb/fetch-characters**").permitAll()
              .antMatchers("/uploadView**").permitAll()
              .antMatchers("/upload**").permitAll()
              .anyRequest().authenticated()
              .and()
              .formLogin()
              .loginPage("/login")
              .permitAll()
              .and()
              .logout()
              .invalidateHttpSession(true)
              .clearAuthentication(true)
              .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
              .logoutSuccessUrl("/login?logout")
              .permitAll();
   }

   @Override
   public void configure(WebSecurity web) throws Exception {
      web.ignoring().antMatchers("/h2-console/**");
   }
}
