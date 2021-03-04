package jpabook.SpringIntroduction;

import jpabook.SpringIntroduction.repository.MemberRepository;
import jpabook.SpringIntroduction.repository.implementation.JpaMemberRepository;
import jpabook.SpringIntroduction.repository.implementation.MemoryMemberRepository;
import jpabook.SpringIntroduction.service.MemberService;
import jpabook.SpringIntroduction.service.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
        return new JpaMemberRepository(em);
    }
}
