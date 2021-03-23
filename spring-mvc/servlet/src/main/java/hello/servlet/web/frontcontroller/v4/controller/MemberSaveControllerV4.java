package hello.servlet.web.frontcontroller.v4.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

public class MemberSaveControllerV4 implements ControllerV4 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {

        // 1. 요청 정보를 받는다.
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        // 2. Member 객체의 인스턴스 생성, 저장.
        Member member = new Member(username, age);
        memberRepository.save(member);

        // 3. Model 객체에 member를 담는다. (View에서 데이터를 참조하기 위해..)
        model.put("member", member);

        // 4. 논리이름 "save-result"를 반환.
        return "save-result";
    }
}
