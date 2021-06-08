package com.koreait.spring.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.time.Clock;

@Controller
@RequestMapping("/user") //1차주소값
public class UserController {

    @Autowired//자동 연결시켜줌 스프링이 관리해주는 애 중에
                // UserService의 주소값을 할만한게 있으면?
                //주소값을 service에 넣어줌
    private UserService service;

    @RequestMapping(value = "/login",method = RequestMethod.GET) //2차주소값
    public String login(@RequestParam(value = "err"/*,required=false*//*(int 쓰고 싶을때)*/ , defaultValue="0") int err, Model model){
        switch (err){
            case 1://아이디없음
                model.addAttribute("errMsg","아이디를 확인해 주세요");//setAttribute
                break;
            case 2://비밀번호 틀림
                model.addAttribute("errMsg","비밀번호를 확인해 주세요");
                break;
        }
        return "user/login"; //겟디스페쳐.forward
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST) //2차주소값
    public String login(UserEntity param){
        return "redirect:"+service.login(param);
        //return "redirect:/board/list";
    }

    @RequestMapping(value = "/join") //2차주소값
    public String join(){
        return "user/join"; //겟디스페쳐.forward
        //redirect : response.sendRedirect
    }
    @RequestMapping(value = "/join",method = RequestMethod.POST) //2차주소값
    public String join(UserEntity param) {
        System.out.println(param);
        service.join(param);
        return "redirect:/user/join"; //겟디스페쳐.forward
        //redirect : response.sendRedirect
    }
    //마이페이지
    @RequestMapping("/profile")
    public String profile(){
        return "user/profile";
    }
    @PostMapping(value = "/profile")
    //같은의미 @RequestMapping(value = "/profile",method = RequestMethod.POST)
    public String profile(@RequestParam("profileImg") MultipartFile/*여러파일이면 []*/ profileImg /*형식이 파일인 것들 전부 여기로*/){
        return "redirect:"+service.uploadProfile(profileImg);
    }
}
