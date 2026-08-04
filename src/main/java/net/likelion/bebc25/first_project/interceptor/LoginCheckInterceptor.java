package net.likelion.bebc25.first_project.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Null;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 1. 현재 클라이언트가 요청한 URL 경로를 가져옴
        String requestUri = request.getRequestURI();
        // 2. 현재 요청한 세션을 가져옴
        // -기본 세션이 없으면 생성하지 않고 null값 반환
        HttpSession session = request.getSession(false);
        // 3. 미인증 사용자 검증
        if(session == null || session.getAttribute("loginmembver") == null){
            log.info("로그인 안된 사용자의 요청: " + requestUri);
            // 미인증인 사용자일 경우 로그인 페이지로 다이렉트 요청
            response.sendRedirect("/member/login");
            return false;
        }
        return true; // HandlerInterceptor가 true를 리턴할 경우 다음 HandlerInterceptor나 컨트롤러 핸들러를 실행함
    }

}
