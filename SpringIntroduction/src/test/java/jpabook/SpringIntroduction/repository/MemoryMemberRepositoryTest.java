package jpabook.SpringIntroduction.repository;

import jpabook.SpringIntroduction.domain.Member;
import jpabook.SpringIntroduction.repository.implementation.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    /**
     * Test가 끝날 때마다 repository를 지워주는 코드
     */
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");
        repository.save(member);

        Optional<Member> savedMember = repository.findById(member.getId());
        Member result = savedMember.get();

        // 검증
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findById() {
        Member member = new Member();
        member.setName("spring");
        repository.save(member);

        Optional<Member> savedMember = repository.findById(member.getId());
        Member result = savedMember.get();

        // 검증
        assertThat(result).isEqualTo(member);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Optional<Member> savedMember = repository.findByName("spring1");
        Member result = savedMember.get();

        // 검증
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        // 검증
        assertThat(result.size()).isEqualTo(2);
    }
}