package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {

    // static 키워드로 MemberRepository 객체 생성이 아무리 많아도 하나만 생성된다.
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    /**
     * 싱글톤 패턴 적용
     */
    private static final MemberRepository instance = new MemberRepository();

    // public으로 열어서 객체 인스터스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용한다.
    public static MemberRepository getInstance() {
        return instance;
    }

    // 생성자를 private으로 선언하여 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다.
    private MemberRepository() {
    }

    /**
     * MemberRepository 구현 부분
     */
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
