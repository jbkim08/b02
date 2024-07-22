package org.zerock.b02.dto;

import lombok.Data;

//회원가입 페이지에서 사용할 dto 객체
@Data
public class MemberJoinDTO {
    private String mid;
    private String mpw;
    private String email;
    private boolean del;  //회원 탈퇴? yes,no
    private boolean social; // 소셜계정(카카오,네이버,구글)로 가입? yes no
}
