package com.example.skmj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // 아이디/비밀번호 설정
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User
                .withUsername("admin")   // 아이디
                .password("{noop}1234")  // 비밀번호 (noop = 암호화 안함)
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }

    // 인증/인가 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/",
                                "/header/**", "/swiperSpotImage/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults());

        http.formLogin(config -> config
                .loginPage("/user/login")
                .loginProcessingUrl("/user/loginProcess")
                .defaultSuccessUrl("/", true)
        );


        return http.build();
    }
}
