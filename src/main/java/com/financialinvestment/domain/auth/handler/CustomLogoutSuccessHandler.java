package com.financialinvestment.domain.auth.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        Cookie deletedCookie = new Cookie("accessToken", null);
        deletedCookie.setMaxAge(0);
        deletedCookie.setHttpOnly(true);
        deletedCookie.setPath("/");
        response.addCookie(deletedCookie);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}
