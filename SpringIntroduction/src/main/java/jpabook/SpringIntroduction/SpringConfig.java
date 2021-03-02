package jpabook.SpringIntroduction;

import jpabook.SpringIntroduction.repository.MemberRepository;
import jpabook.SpringIntroduction.repository.implementation.MemoryMemberRepository;
import jpabook.SpringIntroduction.service.MemberService;
import jpabook.SpringIntroduction.service.MemberServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
