package org.zerock.b02.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.zerock.b02.domain.Member;
import org.zerock.b02.domain.MemberRole;
import org.zerock.b02.dto.MemberJoinDTO;
import org.zerock.b02.repository.MemberRepository;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final ModelMapper modelMapper;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void join(MemberJoinDTO memberJoinDTO) throws MidExistException {
        //새로 가입시 아이디가 같은게 있으면 예외처리
        String mid = memberJoinDTO.getMid();
        boolean exist = memberRepository.existsById(mid);
        if(exist){
            throw new MidExistException();
        }
        //같은 아이디가 아닐경우 DTO => 엔티티 변환
        Member member = modelMapper.map(memberJoinDTO, Member.class);
        //비밀번호는 변환을 해야 됨 1234 => 암호화된문자열로 변환해서 저장
        member.changePassword(passwordEncoder.encode(memberJoinDTO.getMpw()));
        // 기본 유저는 유저 권한이 주어짐
        member.addRole(MemberRole.USER);
        log.info("===============================================");
        log.info(member);
        log.info(member.getRoleSet());
        memberRepository.save(member); //저장하기
    }
}
