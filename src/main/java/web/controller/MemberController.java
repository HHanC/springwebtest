package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import web.dto.MemberDto;
import web.service.MemberService;

@Controller
public class MemberController {

    @Autowired
    MemberService memberService;

    @GetMapping("/")
    public String main(){
        return "main";
    }

    @GetMapping("/board")
    @ResponseBody
    public boolean write(MemberDto memberDto){

        memberService.board(memberDto);
        return true;
    }


}
