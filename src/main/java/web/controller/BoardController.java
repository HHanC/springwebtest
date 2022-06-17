package web.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import web.domain.dto.BoardDto;
import web.service.BoardService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class BoardController {

    @Autowired
    BoardService boardService;

    @GetMapping("/")
    public String main(){
        return "write";
    }

    @GetMapping("/write")
    public String main2(){return "write";}

    @PostMapping("/write")
    @ResponseBody
    public boolean write(BoardDto boardDto){
        System.out.println(boardDto.toString());
        boardService.board(boardDto);
        return true;
    }

    @GetMapping("/list")
    public String list(){return "list";}

    @PostMapping("/list")
    @ResponseBody
    public void lists(HttpServletResponse response){
        List<BoardDto> dtos = boardService.list();
        JSONArray jsonArray = new JSONArray();

        for(BoardDto dto : dtos){
            JSONObject object = new JSONObject();
            object.put("bno" , dto.getBno());
            object.put("bcontent" , dto.getBcontent());
            object.put("bwrite" , dto.getBwrite());
            object.put("bpassword" , dto.getBpassword());
            jsonArray.put(object);
        }
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().print(jsonArray);
        }catch(Exception e){System.out.println(e);}
    }

    @GetMapping("/view")
    public String viewpage(){
        return "view";
    }

    @PostMapping("/view")
    @ResponseBody
    public boolean view(@RequestParam("bno") int bno,
            HttpServletResponse response){

        BoardDto dtos = boardService.view(bno);
        JSONArray jsonArray = new JSONArray();

            JSONObject object = new JSONObject();
            object.put("bno" , dtos.getBno());
            object.put("bcontent" , dtos.getBcontent());
            object.put("bwrite" , dtos.getBwrite());
            jsonArray.put(object);

        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().print(jsonArray);
        }catch(Exception e){System.out.println(e);}
        return true;
    }


}
