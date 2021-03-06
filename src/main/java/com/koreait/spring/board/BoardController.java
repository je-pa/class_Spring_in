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
    public String list(Model model,BoardDTO param){
        List<BoardDomain> list = service.selBoardList(param);
        model.addAttribute("list",list);
        int maxPage =service.selMaxPageVal(param);
        model.addAttribute("maxPage",maxPage);
        return "board/list";
    }
    @RequestMapping("/detail")
    public String detail(BoardDTO param,Model model){
        System.out.println("iboard : " + param.getIboard());
        BoardDomain data = service.selBoard(param);
        model.addAttribute("data",data);
        return "board/detail";
    }
    @GetMapping("/writeMod")
    public void writeMod(BoardDTO param, Model model){
        System.out.println("param = "+param);
        if(param.getIboard()>0){
            model.addAttribute("data",service.selBoard(param));
        }//else 넣어서
    }
    @PostMapping("/writeMod")
    public String writeMod(BoardEntity param){
        int iboard = service.writeMod(param);
        return "redirect:detail?iboard="+iboard;
    }

    @GetMapping("/delBoard")
    public String delBoard(BoardEntity param,@RequestParam int iboard ){
        System.out.println(" d "+iboard);
        param.setIboard(iboard);
        System.out.println("del "+param.getIboard());

        service.delBoard(param);

        return "redirect:list";
    }

    @ResponseBody //ajax의 목적
    @RequestMapping(value = "/cmt", method = RequestMethod.POST)
    public Map<String,Integer> cmtIns(@RequestBody BoardCmtEntity param){
        System.out.println("param ins = "+param);
        int result = service.insBoardCmt(param);

        Map<String,Integer> data = new HashMap<>();// 키 String 값 int
        data.put("result",result);

        //jackson라이브러리로 json형태로 됩니당~!
        return data;
    }

    @ResponseBody
    @RequestMapping("/cmt/{iboard}")
    public List<BoardCmtDomain> cmtSel(BoardCmtEntity param, @PathVariable("iboard") int iboard){
        param.setIboard(iboard);
        System.out.println("param sel = " +param );
        return service.selBoardCmtList(param);
    }
    @ResponseBody //ajax의 목적
    @RequestMapping(value = "/cmt", method = RequestMethod.PUT)
    public Map<String,Integer> cmtUpd(@RequestBody BoardCmtEntity param){
        System.out.println("param upd = "+param);
        int result = service.updBoardCmt(param);
        Map<String,Integer> data = new HashMap<>();// 키 String 값 int
        data.put("result",result);

        //jackson라이브러리로 json형태로 됩니당~!
        return data;
    }

    @ResponseBody //ajax의 목적
    @RequestMapping(value = "/cmt/{icmt}", method = RequestMethod.DELETE)
    public Map<String,Integer> cmtDel(BoardCmtEntity param, @PathVariable int icmt){
        param.setIcmt(icmt);
        System.out.println("param del = "+param);
        int result = service.delBoardCmt(param);

        Map<String,Integer> data = new HashMap<>();// 키 String 값 int
        data.put("result",result);

        //jackson라이브러리로 json형태로 됩니당~!
        return data;
    }

    @GetMapping ("/favList")
    public void favList(){}

}
