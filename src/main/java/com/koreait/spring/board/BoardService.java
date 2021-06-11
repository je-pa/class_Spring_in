package com.koreait.spring.board;

import com.koreait.spring.MyUtils;
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
    private MyUtils myUtils;

    public List<BoardDomain> selBoardList(BoardDTO param){
        param.setIuser(myUtils.getLoginUserPk());
        /*final int RECORD_CNT =5;
        int startIdx = (param.getPage()-1)*RECORD_CNT;

        param.setStartIdx(startIdx);
        param.setRecordCnt(RECORD_CNT);*/
        int startIdx = (param.getPage()-1)*param.getRecordCnt();
        param.setStartIdx(startIdx);

        return mapper.selBoardList(param);
    }

    public int selMaxPageVal(BoardDTO param){
        return mapper.selMaxPageVal(param);
    }

    public BoardDomain selBoard(BoardDTO param){
        return mapper.selBoard(param);
    }

    //return 값은 iboard값
    public int writeMod(BoardEntity param){
        param.setIuser(myUtils.getLoginUserPk());
        //iboard(0), title, ctnt, iuser
        if(param.getIboard()==0){
            mapper.insBoard(param);
        }else{
            mapper.updBoard(param);
        }
        //수정
        return param.getIboard();
    }

    public int delBoard(BoardEntity param){
        BoardCmtEntity cmtParam = new BoardCmtEntity();
        cmtParam.setIboard(param.getIboard());
        cmtMapper.delBoardCmt(cmtParam);

        System.out.println("ser del 1: "+param);
        param.setIuser(myUtils.getLoginUserPk());
        //iboard(0), title, ctnt, iuser
        System.out.println("ser del 2: "+param.getIuser());

        System.out.println("ser del 3: "+param);
        //삭제
        return mapper.delBoard(param);
    }
/*-----------------댓글----------------------*/
    public int insBoardCmt(BoardCmtEntity param){
        param.setIuser(myUtils.getLoginUserPk());
        System.out.println(param);
        return cmtMapper.insBoardCmt(param);
    }

    public List<BoardCmtDomain> selBoardCmtList(BoardCmtEntity param){
        return cmtMapper.selBoardCmtList(param);
    }

    public int updBoardCmt(BoardCmtEntity param){
        param.setIuser(myUtils.getLoginUserPk());
        System.out.println("service upd : "+param);
        return cmtMapper.updBoardCmt(param);
    }

    public int delBoardCmt(BoardCmtEntity param){
        param.setIuser(myUtils.getLoginUserPk());
        System.out.println("service : "+param);
        return cmtMapper.delBoardCmt(param);
    }
}
