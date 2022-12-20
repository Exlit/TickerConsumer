package com.remote100Test.configuration;

import com.remote100Test.service.security.TickerBasicAuthenticationEntryPoint;
import com.remote100Test.service.security.TickerUserDetailsService;
import com.remote100Test.service.security.UnauthorizedEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableWebMvc
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private TickerBasicAuthenticationEntryPoint tickerBasicAuthenticationEntryPoint;

    @Autowired
    private TickerUserDetailsService tickerUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public SecurityConfig() {
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(tickerUserDetailsService).passwordEncoder(passwordEncoder).getUserDetailsService();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000"));
        corsConfiguration.setAllowedMethods(List.of("GET"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setExposedHeaders(List.of("Authorization"));

        http
                .cors()
                .configurationSource(request -> corsConfiguration)
                .and()
                .authorizeRequests()
                .mvcMatchers("/getTicks/**").permitAll()
                .mvcMatchers("/tickConsume")
                .access("@userAuthorizationControl.checkAccessBasedOnRole(authentication)")
                .anyRequest()
                .authenticated().and().httpBasic()
                .authenticationEntryPoint(tickerBasicAuthenticationEntryPoint);
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.exceptionHandling().authenticationEntryPoint(new UnauthorizedEntryPoint());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
