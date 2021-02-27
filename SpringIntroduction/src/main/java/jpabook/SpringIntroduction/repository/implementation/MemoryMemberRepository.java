package jpabook.SpringIntroduction.repository.implementation;

import jpabook.SpringIntroduction.domain.Member;
import jpabook.SpringIntroduction.repository.MemberRepository;

import java.util.*;

/**
 * 예제에서는 동시성 문제가 고려되지 않음. 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려.
 */
public class MemoryMemberRepository implements MemberRepository {

    // 저장공간(key: Long, value: Member)
    private static Map<Long, Member> store = new HashMap<>();

    // key값을 생성해주는 변수
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));      // store 안의 값이 null인 경우 때문에 Optional 사용.
    }

    @Override
    public Optional<Member> findByName(String name) {
        // 반복문(lambda 문법)
        Optional<Member> result = store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
        return result;
    }

    @Override
    public List<Member> findAll() {
        ArrayList<Member> members = new ArrayList<>(store.values());
        return members;
    }

    /**
     * Test가 끝날 때마다 repository를 지워주는 코드
     */
    public void clearStore() {
        store.clear();
    }
}
