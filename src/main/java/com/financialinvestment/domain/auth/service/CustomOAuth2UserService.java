package com.financialinvestment.domain.auth.service;

import com.financialinvestment.domain.auth.entity.OauthProvider;
import com.financialinvestment.domain.auth.entity.Role;
import com.financialinvestment.domain.auth.entity.User;
import com.financialinvestment.domain.auth.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        Map<String, Object> attributes = oAuth2User.getAttributes();
        System.out.println(attributes);
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        String providerUserId = (String) response.get("id");
        String email = (String) response.get("email");
        String name = (String) response.get("name");

//        System.out.println(registrationId);
//        System.out.println(providerUserId);
//        System.out.println(email);
//        System.out.println(name);

        User user = userRepository
                .findByProviderAndProviderUserId(OauthProvider.NAVER, providerUserId)
                .orElseGet(() -> {
                    User newUser = new User(name, email, OauthProvider.NAVER, providerUserId, Role.USER);
                    return userRepository.save(newUser);
                });

        Map<String, Object> customAttributes = new HashMap<>(response);
        customAttributes.put("userId", user.getUserId());
        customAttributes.put("role", user.getRole().name());

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getRole().name())),
                customAttributes,
                "userId"
        );
    }

}