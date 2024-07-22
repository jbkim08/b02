package org.zerock.b02.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.b02.dto.MemberJoinDTO;

@Controller
@RequestMapping("/member")
@Log4j2
@RequiredArgsConstructor
public class MemberController {

    //시큐리티 로그인주소 /member/login 으로 커스텀 로그인페이지 이동
    @GetMapping("/login")
    public void loginGET(String error, String logout) {
        log.info("login GET .....");
        log.info("로그아웃 : " + logout );
        if(logout != null){
            log.info("유저 로그아웃....");
        }
    }

    //회원가입 페이지를 보여주기
    @GetMapping("/join")
    public void joinGET() {
        log.info("join GET .....");
    }

    //회원가입 요청
    @PostMapping("/join")
    public String joinPOST(MemberJoinDTO memberJoinDTO) {
        log.info("join POST .....");
        log.info(memberJoinDTO.toString()); //입력한 멤버정보를 출력

        return "redirect:/board/list"; //회원 가입후 리스트로 이동
    }
}
