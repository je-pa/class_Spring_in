package com.koreait.spring.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.Clock;

@Controller
@RequestMapping("/user") //1차주소값
public class UserController {

    @Autowired//자동 연결시켜줌 스프링이 관리해주는 애 중에
                // UserService의 주소값을 할만한게 있으면?
                //주소값을 service에 넣어줌
    private UserService service;

    @RequestMapping(value = "/login",method = RequestMethod.GET) //2차주소값
    public String login(){
        return "user/login"; //겟디스페쳐.forward
    }
    @RequestMapping(value = "/join") //2차주소값
    public String join(){
        return "user/join"; //겟디스페쳐.forward
        //redirect : response.sendRedirect
    }
    @RequestMapping(value = "/join",method = RequestMethod.POST) //2차주소값
    public String join(UserEntity param){
        System.out.println(param);
        service.join(param);
        return "redirect:/user/join"; //겟디스페쳐.forward
        //redirect : response.sendRedirect
    }
}
