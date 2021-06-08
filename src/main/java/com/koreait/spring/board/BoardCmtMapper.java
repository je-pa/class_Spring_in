package com.koreait.spring.board;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardCmtMapper { //public abstract
    int insBoardCmt(BoardCmtEntity param);
    List<BoardCmtDomain> selBoardCmtList(BoardCmtEntity param);
    int delBoardCmt(BoardCmtEntity param);
}
