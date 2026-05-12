package com.financialinvestment.domain.config;

import com.financialinvestment.domain.auth.entity.Role;
import com.financialinvestment.domain.auth.handler.OAuth2LoginSuccessHandler;
import com.financialinvestment.domain.auth.jwt.JWTFilter;
import com.financialinvestment.domain.auth.jwt.JwtTokenProvider;
import com.financialinvestment.domain.auth.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
    private final JwtTokenProvider jwtTokenProvider;


    @Bean

    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .formLogin((auth) -> auth.disable())
                .httpBasic((auth) -> auth.disable())
                .headers(headers -> headers.frameOptions(frame -> frame.disable()))
                .addFilterBefore(new JWTFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers(
                                        "/",
                                        "/error",
                                        "/oauth2/authorization/**",
                                        "/login/oauth2/**",
                                        "/h2-console/**"
                                ).permitAll()
                                .requestMatchers(HttpMethod.GET, "/actuator/health").permitAll()
                                .requestMatchers(HttpMethod.GET, "/user/me").authenticated()
                                .requestMatchers("/api/v1/users/**","/mypage").hasRole(Role.USER.name())
                                .anyRequest().denyAll() //그 외에는 모두 거부.
                )
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                )
                .oauth2Login(oauth2 -> oauth2
                        .successHandler(oAuth2LoginSuccessHandler)
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuth2UserService)
                        )
                )
                .sessionManagement((session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)));//세션 설정을 STATELESS방식으로 함.

        return http.build();
    }


}
