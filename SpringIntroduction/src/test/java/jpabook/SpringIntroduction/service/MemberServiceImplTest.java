package jpabook.SpringIntroduction.service;

import jpabook.SpringIntroduction.domain.Member;
import jpabook.SpringIntroduction.repository.implementation.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceImplTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;     // Test가 끝날 때마다 repository를 지워주기 위해

    /**
     * @BeforeEach : 각 테스트 실행 전에 호출된다. 테스트가 서로 영향이 없도록 항생 새로운 객체를 생성, 의존관계도 새로 맺어준다.
     */
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberServiceImpl(memberRepository);    // service에 repository 주입
    }

    /**
     * @AfterEach : 각 테스트 실행 후에 호출된다.
     */
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    /**
     * 회원 가입
     */
    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("hello");

        // when
        Long savedId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(savedId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    /**
     * 중복 회원 검증
     */
    @Test
    void validateDuplicateMember() {
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        memberService.join(member1);

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");     // 메시지 검증

        /*
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
        */
    }
}