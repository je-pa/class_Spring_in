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
}
