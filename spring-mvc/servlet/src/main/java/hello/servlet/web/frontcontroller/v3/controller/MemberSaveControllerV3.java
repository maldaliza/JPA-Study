package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {

        // 1. 요청 정보를 받는다.
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        // 2. Member 객체의 인스턴스 생성, 저장.
        Member member = new Member(username, age);
        memberRepository.save(member);

        // 3. 논리이름이 "save-result"를 담는 ModelView 객체 생성.
        ModelView modelView = new ModelView("save-result");

        // 4. ModelView 객체에서 Model을 가져와서 보관. (View에서 데이터를 참조하기 위해..)
        modelView.getModel().put("member", member);

        // 5. ModelView를 반환.
        return modelView;
    }
}
