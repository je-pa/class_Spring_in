package com.koreait.spring.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController // 목표는 json @ResponseBody 안적어줘도됨
@RequestMapping("/board")
public class BoardFavController {
    @Autowired
    private BoardFavService service;

    @Autowired
    private BoardService service2;

    @PostMapping("/fav")           //다시 객체(jackson)
    public Map<String,Integer> insFav(@RequestBody BoardFavEntity param){
        Map<String,Integer> result = new HashMap<>();
        result.put("result", service.insFav(param)); //string
        return result;
    }
    @GetMapping("/fav")
    public Map<String,Object> selFavBoardList(BoardDTO param){
        Map<String,Object> result = new HashMap<>();

        param.setSelType(1);
        result.put("list",service2.selBoardList(param));
        result.put("maxPageVal",service2.selMaxPageVal(param));

        return result;
    }
    /*
    public List<BoardDomain> selFavBoardList(BoardDTO param){
        param.setSelType(1);
        return service2.selBoardList(param);
    }*/
    @GetMapping("/fav/{iboard}")
    public Map<String,Integer> selFav(BoardFavEntity param, @PathVariable int iboard){
        param.setIboard(iboard);
        Map<String,Integer> result = new HashMap<>();
        result.put("result", service.selFav(param));
        return result;
    }
    @DeleteMapping("/fav")//favList
    public Map<String,Integer> delFav(BoardFavEntity param){
        Map<String,Integer> result = new HashMap<>();
        result.put("result", service.delFav(param));
        System.out.println("del" + param);
        return result;
    }
}
