package jpabook.signup.repository;

import jpabook.signup.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public List<Member> findAll() {
        // JPQL
        List<Member> result = em.createQuery("select m from Member m", Member.class).getResultList();
        return result;
    }
}
