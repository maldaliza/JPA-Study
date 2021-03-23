package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * ControllerV3를 지원하는 Adapter 구현체
 */
public class ControllerV3HandlerAdapter implements MyHandlerAdapter {

    /**
     * Adapter가 해당 Controller를 처리할 수 있는지 판단하는 메소드
     * @param handler
     * @return true(처리 가능), false(처리 불가능)
     */
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV3);       // handler가 ControllerV3의 인스턴스인가?
    }

    /**
     * Controller를 호출하고, 그 결과로 ModelView를 반환하는 메소드
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {

        // 1. handler 파라미터가 Object 타입이므로 ControllerV3 타입으로 캐스팅.
        ControllerV3 controller = (ControllerV3) handler;

        // 2. 각 Controller의 파라미터로 넘겨줄 paramMap 생성.
        Map<String, String> paramMap = createParamMap(request);

        // 3. 생성된 paramMap을 Controller에 넘겨준다. => 리턴 값은 "ModelView"
        ModelView modelView = controller.process(paramMap);

        // 4. ModelView 반환.
        return modelView;
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {

        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));

        return paramMap;
    }
}
