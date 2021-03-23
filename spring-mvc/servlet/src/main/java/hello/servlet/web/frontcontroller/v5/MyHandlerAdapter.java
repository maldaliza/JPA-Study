package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface MyHandlerAdapter {

    /**
     * Adapter가 해당 Controller를 처리할 수 있는지 판단하는 메소드
     * @param handler
     * @return
     */
    boolean supports(Object handler);

    /**
     * Controller를 호출하고, 그 결과로 ModelView를 반환하는 메소드
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws ServletException
     * @throws IOException
     */
    ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException;
}
