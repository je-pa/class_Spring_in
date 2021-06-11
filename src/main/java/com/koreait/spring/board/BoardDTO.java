package com.koreait.spring.board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO {
    private int recordCnt;
    private int startIdx;
    private int searchType;
    private String searchText;
    private int iboard;
    private int iuser;
    private int selType; //0:기본리스트, 1: 좋아요리스트
}
