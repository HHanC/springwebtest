package web.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
    public void lists(HttpServletResponse response){
        List<BoardDto> dtos = BoardService.list();
        JSONArray jsonArray = new JSONArray();

        for(BoardDto dto : dtos){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("bno" ,dto.getBno());
            jsonObject.put("btitle" , dto.getBtitle());
            jsonObject.put("bcontent" , dto.getBcontent());
            jsonObject.put("bwrite" , dto.getBwrite());
            jsonArray.put(jsonObject);
            try{
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json");
                response.getWriter().print(jsonArray);
            }catch (Exception e){System.out.println(e);}
        }
    }

}
