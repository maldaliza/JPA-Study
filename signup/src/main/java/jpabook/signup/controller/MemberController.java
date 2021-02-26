package jpabook.signup.controller;

import jpabook.signup.controller.form.MemberForm;
import jpabook.signup.domain.Member;
import jpabook.signup.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping(value = "/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping(value = "/members/new")
    public String create(@Valid MemberForm memberForm) {

        Member member = new Member();
        member.setName(memberForm.getName());

        memberService.join(member);
        return "redirect:/";
    }
}
