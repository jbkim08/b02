package org.zerock.b02.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.b02.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {

    //유저 id로 유저와 권한을 가져옴 이때 권한은 lazy 로딩이므로 즉시 가져올수 있도록 EntityGraph 사용
    @EntityGraph(attributePaths = "roleSet")
    @Query("select m from Member m where m.mid = :mid and m.social = false")
    Optional<Member> getWithRoles(String mid);

    //이메일로 가입된 유저를 찾는 메서드
    @EntityGraph(attributePaths = "roleSet")
    Optional<Member> findByEmail(String email);

    //패스워드 업데이트!
    @Modifying
    @Transactional
    @Query("update Member m set m.mpw = :mpw where m.mid = :mid")
    void updatePassword(String mpw, String mid);

}
