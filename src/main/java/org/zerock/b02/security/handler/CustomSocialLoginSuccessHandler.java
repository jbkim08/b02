package org.zerock.b02.security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.zerock.b02.security.dto.MemberSecurityDTO;

import java.io.IOException;

@Log4j2
@RequiredArgsConstructor
public class CustomSocialLoginSuccessHandler implements AuthenticationSuccessHandler {
    private final PasswordEncoder passwordEncoder;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("------------------------------------------------------");
        log.info("Custom Social Login Success Handler");
        log.info(authentication.getPrincipal());
        MemberSecurityDTO memberSecurityDTO = (MemberSecurityDTO) authentication.getPrincipal();

        String encodedPW = memberSecurityDTO.getMpw();
        //소셜로 가입하고 비번이 1234와 같으면 (처음 가입시)
        if(memberSecurityDTO.isSocial() && encodedPW.equals("1234")) {
            log.info("비밀번호 바꾸세요!");
            log.info("Redirect to Member Modify");
            response.sendRedirect("/member/modify");
            return;
        } else {
            response.sendRedirect("/board/list");
        }
    }
}
