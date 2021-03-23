package hello.servlet.web.frontcontroller.v4.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.List;
import java.util.Map;

public class MemberListControllerV4 implements ControllerV4 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {

        // 1. 저장소의 Member 객체 인스턴스를 전부 불러온다.
        List<Member> members = memberRepository.findAll();

        // 2. Model 객체에 members를 담는다. (View에서 데이터를 참조하기 위해..)
        model.put("members", members);

        // 3. 논리이름 "members"를 반환.
        return "members";
    }
}
