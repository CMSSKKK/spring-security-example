package kr.ron2.security.auth.config;

import kr.ron2.security.auth.OAuth2Service;
import kr.ron2.security.auth.handler.OAuthFailHandler;
import kr.ron2.security.auth.handler.OAuthSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final OAuth2Service oAuth2Service;
    private final OAuthSuccessHandler successHandler;
    private final OAuthFailHandler failHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .httpBasic().disable()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
                .authorizeRequests()
                    .anyRequest().authenticated()
        .and()
                .oauth2Login()
                    .userInfoEndpoint()
                        .userService(oAuth2Service)
                .and()
                .successHandler(successHandler)
                .failureHandler(failHandler)
        .and()
                .build();
    }
    

}
