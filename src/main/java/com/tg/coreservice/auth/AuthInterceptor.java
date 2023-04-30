package com.tg.coreservice.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

@Configuration
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (ResourceHttpRequestHandler.class.isAssignableFrom(handler.getClass())) {
            return true; // spring docs를 위한 처리
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
        if (auth == null) { // Auth 어노테이션이 없으면, 인증이 필요없는 요청이므로 true를 반환한다.
            return true;
        }

        HttpSession session = request.getSession();
        if (session.getAttribute("userId") == null) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "로그인이 필요합니다.");
            return false;
        }

        Long userId = (Long) session.getAttribute("userId");
        UserContext.CONTEXT.set(userId);
        return true;
    }
}
