package org.zerock.b02.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
@Log4j2
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("Oauth2 user Request");
        log.info(userRequest);

        log.info("==================== Oauth2 User ======================");

        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        String clientName = clientRegistration.getClientName();
        log.info("Name: " + clientName);
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> paramMap = oAuth2User.getAttributes();

        String email = null;
        switch (clientName){
            case "kakao":
                email = getKakaoEmail(paramMap);
                break;
        }
        log.info("============================================");
        log.info("Email: " + email);
        log.info("============================================");
        return oAuth2User;
    }

    private String getKakaoEmail(Map<String, Object> paramMap) {
        Object value = paramMap.get("kakao_account");
        LinkedHashMap accountMap = (LinkedHashMap)value;
        String email = (String)accountMap.get("email");
        return email;
    }
}
