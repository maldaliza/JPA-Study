package hello.servlet.web.frontcontroller.v4;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    // 매핑 정보
    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    // 받아온 URL에 따라 실행할 Controller를 controllerMap에 넘겨준다.
    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("FrontControllerServletV4.service");

        // 1. 요청 받은 URI를 추출.
        String requestURI = request.getRequestURI();

        // 2. 추출된 URI(key)를 통해 실행할 Controller를 찾는다.
        ControllerV4 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 3. 각 Controller의 파라미터로 넘겨줄 paramMap 생성
        Map<String, String> paramMap = createParamMap(request);

        // 4. Model 생성
        Map<String, Object> model = new HashMap<>();

        // 5. 생성된 paramMap, model을 Controller에 넘겨준다. => 리턴 값으로 나오는 것은 "viewName"
        String viewName = controller.process(paramMap, model);

        // 6. 논리이름인 viewName을 물리이름으로 바꾼다.
        MyView view = viewResolver(viewName);

        // 7. view를 렌더링.
        view.render(model, request, response);
    }

    private MyView viewResolver(String viewName) {

        MyView myView = new MyView("/WEB-INF/views/" + viewName + ".jsp");
        return myView;
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {

        Map<String, String> paramMap = new HashMap<>();

        // request의 모든 파라미터를 꺼내서 paramMap에 담는다.
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));

        return paramMap;
    }
}
