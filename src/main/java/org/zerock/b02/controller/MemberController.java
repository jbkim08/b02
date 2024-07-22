package org.zerock.b02.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.b02.dto.MemberJoinDTO;
import org.zerock.b02.service.MemberService;

@Controller
@RequestMapping("/member")
@Log4j2
@RequiredArgsConstructor
public class MemberController {
    //멤버 서비스 주입
    private final MemberService memberService;

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
    public String joinPOST(MemberJoinDTO memberJoinDTO, RedirectAttributes redirectAttributes) {
        log.info("join POST .....");
        log.info(memberJoinDTO.toString()); //입력한 멤버정보를 출력

        try {
            memberService.join(memberJoinDTO);
        } catch (MemberService.MidExistException e) {
            redirectAttributes.addFlashAttribute("error", "mid");
            return "redirect:/member/join";
        }
        //같은 아이디가 없고 가입 성공했을때 로그인 페이지로 success 메세지 포함
        redirectAttributes.addFlashAttribute("result", "success");
        return "redirect:/member/login"; //회원 가입후 로그인 페이지로
    }
}
