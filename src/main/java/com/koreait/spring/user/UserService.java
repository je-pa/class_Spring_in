package com.koreait.spring.user;

import org.apache.commons.io.FilenameUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.Filter;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service //bean등록 -> 주소값
public class UserService {
    @Autowired
    private UserMapper mapper;

    @Autowired
    private HttpSession session;

    public String login(UserEntity param){
        UserEntity result = mapper.selUser(param);
        if (result == null) { //아이디없음
            return "/user/login?err=1";
        }else if(BCrypt.checkpw(param.getUpw(),result.getUpw())){//성공
            result.setUpw(null);
            session.setAttribute("loginUser",result);
            return "/board/list";
        }else{//비밀번호 틀림
            return "/user/login?err=2";
        }

    }
    public int join(UserEntity param){
        String cryptPw = BCrypt.hashpw(param.getUpw(),BCrypt.gensalt());
        param.setUpw(cryptPw);
        return mapper.insUser(param);
    }
    //마이페이지
    public String uploadProfile(MultipartFile img){
        UserEntity loginUser = (UserEntity) session.getAttribute("loginUser");
        final String PATH = "D:/springImg/"+loginUser.getIuser();

        File folder = new File(PATH);
        folder.mkdirs();

        String ext = FilenameUtils.getExtension(img.getOriginalFilename());
        /*원래파일명확장자..! 그전에는 우리가 직접 구현했다구한다*/
        String fileNm = UUID.randomUUID().toString()+"."+ext;/*랜덤한 파일네임*/

        File target = new File(PATH + "/"+fileNm);
        try{
            img.transferTo(target);
        }catch (IOException e ){
            e.printStackTrace();
        }
        return "/user/profile";
    }
}
