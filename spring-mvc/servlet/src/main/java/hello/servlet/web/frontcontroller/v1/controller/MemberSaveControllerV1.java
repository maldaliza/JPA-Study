package hello.servlet.web.frontcontroller.v1.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.v1.ControllerV1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Member Save Controller 구현체
 */
public class MemberSaveControllerV1 implements ControllerV1 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 1. 요청 정보를 받는다.
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        // 2. Member 객체의 인스턴스 생성, 저장
        Member member = new Member(username, age);
        memberRepository.save(member);

        // 3. Model에 데이터를 보관한다.
        request.setAttribute("member", member);

        // 4. "/WEB-INF/views/save-result.jsp" 다음 경로로 넘어가겠다.
        String viewPath = "/WEB-INF/views/save-result.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
