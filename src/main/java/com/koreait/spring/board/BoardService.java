package com.koreait.spring.board;

import com.koreait.spring.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardMapper mapper;
    @Autowired
    private BoardCmtMapper cmtMapper;
    @Autowired
    private HttpSession session;

    public List<BoardDomain> selBoardList(){
        return mapper.selBoardList();
    }

    public BoardDomain selBoard(BoardDTO param){
        return mapper.selBoard(param);
    }

    //return 값은 iboard값
    public int writeMod(BoardEntity param){
        UserEntity loginUser = (UserEntity) session.getAttribute("loginUser");
        param.setIuser(loginUser.getIuser());
        //iboard(0), title, ctnt, iuser
        if(param.getIboard()==0){
            mapper.insBoard(param);
        }else{

        }
        //수정
        return param.getIboard();
    }

/*-----------------댓글----------------------*/
    public int insBoardCmt(BoardCmtEntity param){
        UserEntity loginUser = (UserEntity) session.getAttribute("loginUser");
        param.setIuser(loginUser.getIuser());
        System.out.println(param);
        return cmtMapper.insBoardCmt(param);
    }

    public List<BoardCmtDomain> selBoardCmtList(BoardCmtEntity param){
        return cmtMapper.selBoardCmtList(param);
    }

    public int updBoardCmt(BoardCmtEntity param){
        UserEntity loginUser = (UserEntity) session.getAttribute("loginUser");
        param.setIuser(loginUser.getIuser());
        System.out.println("service upd : "+param);
        return cmtMapper.updBoardCmt(param);
    }

    public int delBoardCmt(BoardCmtEntity param){
        UserEntity loginUser = (UserEntity) session.getAttribute("loginUser");
        param.setIuser(loginUser.getIuser());
        System.out.println("service : "+param);
        return cmtMapper.delBoardCmt(param);
    }
}
