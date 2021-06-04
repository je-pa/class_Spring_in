package com.koreait.spring.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller//고객이 요청했을때 연결시켜줌
@RequestMapping("/board")
public class
BoardController {
    @Autowired
    private BoardService service;

    @RequestMapping("/list")
    public String list(Model model){
        List<BoardDomain> list = service.selBoardList();
        model.addAttribute("list",list);
        return "board/list";
    }
    @RequestMapping("/detail")
    public String detail(BoardDTO param,Model model){
        System.out.println("iboard : " + param.getIboard());
        BoardDomain data = service.selBoard(param);
        model.addAttribute("data",data);
        return "board/detail";
    }
    @ResponseBody //ajax의 목적
    @RequestMapping(value = "/cmtInsSel", method = RequestMethod.POST)
    public Map<String,Integer> cmtInSel(@RequestBody BoardCmtEntity param){
        System.out.println("param = "+param);
        Map<String,Integer> data = new HashMap<>();// 키 String 값 int
        data.put("result",1);
        data.put("age",30);
        //jackson라이브러리로 json형태로 됩니당~!
        return data;
    }
}
