package hello.login.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static hello.login.web.SessionConst.LOGIN_MEMBER;

/**
 * Created by Hunseong on 2022/04/04
 */
@Slf4j
public class LoginCheckFilter implements Filter {

    private static final String[] whitelist = {"/", "/members/add", "/login", "/logout", "/css/*"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestURI = httpRequest.getRequestURI();

        try {
            log.info("인증 필터 시작 {}", requestURI);

            if (isLoginCheckPath(requestURI)) {
                log.info("인증 체크 로직 실행 {}", requestURI);
                HttpSession session = httpRequest.getSession(false);
                if (session == null || session.getAttribute(LOGIN_MEMBER) == null) {
                    log.info("미인증 사용자 요청 {}", requestURI);

                    httpResponse.sendRedirect("/login?redirectURL=" + requestURI);
                    return; // !! 미인증 사용자는 다음 로직 수행 X (DispatcherServlet -> Controller로 넘어가지 않음)
                }
            }

            chain.doFilter(request, response);
        } finally {
            log.info("인증 필터 종료 {}", requestURI);
        }

    }

    /**
     * whitelist에 포함된 URI의 경우 로그인 체크 하지 않음
     * (= whitelist에 해당 URI가 포함되는지 체크. 포함되면 false 리턴하여 로직 수행하지 않음)
     */
    private boolean isLoginCheckPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(whitelist, requestURI);
    }
}
