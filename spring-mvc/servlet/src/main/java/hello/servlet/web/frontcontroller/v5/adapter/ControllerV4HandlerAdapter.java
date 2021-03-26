package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * ControllerV4를 지원하는 Adapter 구현체
 */
public class ControllerV4HandlerAdapter implements MyHandlerAdapter {

    /**
     * Adapter가 해당 Controller를 처리할 수 있는지 판단하는 메소드
     * @param handler
     * @return true(처리 가능), false(처리 불가능)
     */
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV4);
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

        // 1. handler 파라미터가 Object 타입이므로 ControllerV4 타입으로 캐스팅.
        ControllerV4 controller = (ControllerV4) handler;

        // 2. 각 Controller의 파라미터로 넘겨줄 paramMap 생성.
        Map<String, String> paramMap = createParamMap(request);

        // 3. 각 Controller의 파라미터로 넘겨줄 model 생성.
        HashMap<String, Object> model = new HashMap<>();

        // 4. 생성된 paramMap, model을 Controller에 넘겨준다. => 리턴 값은 "viewName"
        String viewName = controller.process(paramMap, model);

        // 5. viewName으로 ModelView 생성 및 Model 생성.
        ModelView modelView = new ModelView(viewName);
        modelView.setModel(model);

        // 6. ModelView 반환.
        return modelView;
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));

        return paramMap;
    }
}
