package com.financialinvestment.domain.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.financialinvestment.domain.user.dto.UserMeResponse;
import com.financialinvestment.domain.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ObjectMapper objectMapper;

    @GetMapping("/me")
    public UserMeResponse getMyInfo(HttpServletRequest request, Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        UserMeResponse myInfo = userService.getMyInfo(userId);
        System.out.println("myinfo : " + myInfo);
        System.out.println("request method : " + request.getMethod());
        return myInfo;
    }

    @GetMapping("/mypage")
    public UserMeResponse getMyInfoDetail(HttpServletRequest request, Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        System.out.println("마이페이지");
        UserMeResponse myInfo = userService.getMyInfo(userId);
        return myInfo;
    }
}
