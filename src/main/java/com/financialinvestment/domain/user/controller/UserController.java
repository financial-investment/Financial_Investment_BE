package com.financialinvestment.domain.user.controller;

import com.financialinvestment.domain.user.dto.UserMeResponse;
import com.financialinvestment.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public UserMeResponse getMyInfo(Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        return userService.getMyInfo(userId);
    }
}
