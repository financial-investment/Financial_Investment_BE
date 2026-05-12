package com.financialinvestment.domain.user.service;

import com.financialinvestment.domain.auth.entity.User;
import com.financialinvestment.domain.auth.repository.UserRepository;
import com.financialinvestment.domain.user.dto.UserMeResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public UserMeResponse getMyInfo(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + userId));

        return UserMeResponse.from(user);
    }
}
