package com.koreait.spring;

import com.koreait.spring.user.UserEntity;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

public class AuthCheckInterceptor implements HandlerInterceptor {
    //private final String[] needLoginUriArr = {"/board/writeMod","/board/favList","/user/profile"};

    //controller보내기 전에 처리
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;//false면 가지않음
    }

    //controller까지 갔다와서 데이터를 받아온 상태 jsp파일 열기직전
    //controller의 handler가 끝나면 처리
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        String uri = request.getRequestURI();
        System.out.println("uri : "+uri);

//        if(Arrays.asList(needLoginUriArr).contains(uri)){
            UserEntity loginUser = (UserEntity) request.getSession().getAttribute("loginUser");
            if(loginUser==null){
                System.out.println("view"+modelAndView.getViewName());
                modelAndView.setViewName("/user/needLogin");
            }
//        }
    }

    //view까지 처리가 끝난 후에 처리
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
