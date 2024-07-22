package org.zerock.b02.service;

import org.zerock.b02.dto.MemberJoinDTO;

public interface MemberService {
    //회원 가입시 같은 아이디가 존재하면 예외처리
    static class MidExistException extends Exception{
    }

    void join(MemberJoinDTO memberJoinDTO) throws MidExistException;
}
