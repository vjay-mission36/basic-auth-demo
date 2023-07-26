package com.mission48.basicsuth.config;

import com.mission48.basicsuth.service.UserInfoDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class BasicSecurityAuth {

    @Bean
    public UserDetailsService getUserDetailsService() {
      /* UserDetails user1 = User
                .withUsername("user1").password(getPasswordEncoder().encode("user1")).roles("ADMIN")
                .build();

        UserDetails user2 = User
                .withUsername("user2").password(getPasswordEncoder().encode("user2")).roles("READ")
                .build();
        return new InMemoryUserDetailsManager(user1, user2);*/
        return new UserInfoDetailService();
    }

    @Bean(name = "passwordEncoder")
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req.requestMatchers(new AntPathRequestMatcher("/hello")).permitAll())
                .authorizeHttpRequests(req -> req.requestMatchers(new AntPathRequestMatcher("/userinfo/add/**")).permitAll())
                .authorizeHttpRequests(req -> req.requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll())
                .authorizeHttpRequests(req -> req.requestMatchers(new AntPathRequestMatcher("/actuator/**")).permitAll())
                .authorizeHttpRequests(req -> req.anyRequest().authenticated())
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll);
        httpSecurity.headers(head -> head.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(getUserDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(getPasswordEncoder());
        return daoAuthenticationProvider;
    }
}
