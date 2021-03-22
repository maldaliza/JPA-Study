package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {

    /**
     * 매핑 정보(key: URL, value: ControllerV2)
     */
    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    /**
     * 받아온 매핑 정보(URL)에 따라 실행될 Controller를 controllerMap에 넘겨준다.
     */
    public FrontControllerServletV2() {
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("FrontControllerServletV2.service");

        // 1. 요청 받은 URI를 추출.
        String requestURI = request.getRequestURI();

        // 2. 추출된 URI(key)를 통한 실행할 Controller 찾음.
        ControllerV2 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 3. 선정된 Controller의 process() 메소드에 request, response 값을 넘겨준다. => 리턴 값은 "MyView"
        MyView view = controller.process(request, response);

        // 4. MyView 클래스의 render() 메소드로 렌더링.
        view.render(request, response);
    }
}
