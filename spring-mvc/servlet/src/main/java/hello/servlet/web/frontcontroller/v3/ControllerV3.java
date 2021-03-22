package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {

    /**
     * Controller 입장에서는 HttpServletRequest, HttpServletResponses가 필요없다.
     * 요청 정보를 Map으로 대신 넘기도록 하여 Controller가 Servlet 기술을 몰라도 동작할 수 있다.
     */
    ModelView process(Map<String, String> paramMap);
}
