package jpabook.signup.controller.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberForm {

//    @NotEmpty(message = "회원 이름은 필수입니다.")        // 값이 비어있으면 오류발생
    private String name;
}
