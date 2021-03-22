package hello.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 1. "/WEB-INF/views/new-form.jsp" 다음 경로로 넘어가겠다.
        String viewPath = "/WEB-INF/views/new-form.jsp";        // View 경로
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);      // Controller에서 View로 이동할 때 사용.
        dispatcher.forward(request, response);       // 다른 서블릿이나 JSP로 이동할 수 있는 기능(서버 내부에서 다시 호출이 발생한다.)
    }
}
