package hello.login.web.argumentresolver;

import hello.login.domain.member.Member;
import hello.login.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        log.info("supportsParameter 실행");

        boolean hasLoginAnnotation = parameter.hasParameterAnnotation(Login.class);
        // 우리가 만든 @Login 어노테이션이 넘어오는 파라미터에 있느냐
        boolean hasMemberType = Member.class.isAssignableFrom(parameter.getParameterType());
        // 파라미터가 들어올때 Member 인가 아닌가를 물어본다.

        return hasLoginAnnotation && hasMemberType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        log.info("resolveArgument 실행");
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest(); // webServletRequest에서 HttpServletRequest를 뽑아줌
        HttpSession session = request.getSession(false);
        if (session == null) {
            return null;
        }

//        Object member = session.getAttribute(SessionConst.LOGIN_MEMBER);
//        return member;

        return session.getAttribute(SessionConst.LOGIN_MEMBER);


    }
}
