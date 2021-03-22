package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    /**
     * 매핑 정보(key: URL, value: ControllerV3)
     */
    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    /**
     * 받아온 매핑 정보(URL)에 따라 실행될 Controller를 controllerMap에 넘겨준다.
     */
    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    /**
     * 서블릿 구현 부분
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("FrontControllerServletV3.service");

        // 1. 요청 받은 URI를 추출.
        String requestURI = request.getRequestURI();

        // 2. 추출된 URI(key)를 통한 실행할 Controller 찾음.
        ControllerV3 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 3. paramMap 생성.
        Map<String, String> paramMap = createParamMap(request);

        // 4. 선정된 Controller의 process() 메소드에 paramMap을 넘겨준다. => 리턴 값은 "MyView"
        ModelView modelView = controller.process(paramMap);

        // 5. ModelView의 논리이름을 추출하여 viewResolver() 메소드를 통해 URI를 생성.
        String viewName = modelView.getViewName();
        MyView view = viewResolver(viewName);

        // 6. MyView 클래스의 render() 메소드로 렌더링 + Model도 같이 넘겨준다.
        view.render(modelView.getModel(), request, response);
    }

    private MyView viewResolver(String viewName) {

        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {

        Map<String, String> paramMap = new HashMap<>();

        // request에서 모든 파라미터를 다 꺼내야한다.
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));

        return paramMap;
    }
}
