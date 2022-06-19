package web.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.domain.BoardEntity;
import web.domain.BoardRepository;
import web.domain.dto.BoardDto;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

@Autowired
private BoardRepository boardRepository;

    public boolean board(BoardDto boardDto){
        BoardEntity boardEntity = boardDto.toentity();
        boardRepository.save(boardEntity);
        return true;
    }

    public List<BoardDto> list(){
        List<BoardEntity> entitys = boardRepository.findAll();

        List<BoardDto> dtos = new ArrayList<>();
        for(BoardEntity entity : entitys){
            dtos.add(BoardDto.builder()
                            .bno(entity.getBno())
                            .bcontent(entity.getBcontent())
                            .bwrite(entity.getBwrite())
                            .bpassword(entity.getBwrite())
                    .build());
        }
        return dtos;
    }

    public JSONObject view(int bno){
        // 1. 해당 룸 번호의 룸 엔티티 찾기
        Optional<BoardEntity> optional = boardRepository.findById(bno);
        // 2. 해당 엔티티 -> json객체 변환
        BoardEntity boardEntity = optional.get();
            // 1. json에 엔티티 필드 넣기
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("bno" , boardEntity.getBno());
            jsonObject.put("bcontent" , boardEntity.getBcontent());
            jsonObject.put("bwrite" , boardEntity.getBwrite());
        return jsonObject;
    }


}
