package web.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import web.domain.dto.BoardDto;
import web.service.BoardService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class BoardController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    BoardService boardService;

    @GetMapping("/")
    public String main(){
        return "main";
    }

    @GetMapping("/write")
    public String write(){return "write";}

    @PostMapping("/write")
    @ResponseBody
    public boolean write(BoardDto boardDto){
        System.out.println(boardDto.toString());
        boardService.board(boardDto);
        return true;
    }

    @GetMapping("/list")
    public String list(){return "list";}

    @GetMapping("/lists")
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

    int selectbno = 0;

    @GetMapping("/view/{bno}")
    public String view(HttpServletResponse response, @PathVariable("bno") int bno ) {
        request.getSession().setAttribute("bno", bno);
        selectbno = bno;
        return "view";
    }

    @GetMapping("/getview")
    public void getview(HttpServletResponse response){
        int bno = (Integer) request.getSession().getAttribute("bno");
        try{
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().print(boardService.getview(bno));
        }catch(Exception e){System.out.println(e);}
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public boolean delete(@RequestParam("bno") int bno){return boardService.delete(bno);}

    @GetMapping("/update")
    public String updates(){return "update";}

    @PutMapping("/update")
    @ResponseBody
    public boolean update(BoardDto boardDto){
        int bno = (Integer) request.getSession().getAttribute("bno");
        boardDto.setBno(bno);
        return boardService.update(boardDto);
    }

    @GetMapping("/getcategorylist")
    public void getcategorylist(HttpServletResponse response){
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().print(boardService.getcategorylist());
        }catch (Exception e){System.out.println(e);}
    }


}





















