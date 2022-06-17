package web.service;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.domain.BoardEntity;
import web.domain.BoardRepository;
import web.domain.dto.BoardDto;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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

    public BoardDto view(int bno){
        BoardEntity entity = boardRepository.findById(bno).get();

        BoardDto dtos = BoardDto.builder()
                .bno(entity.getBno())
                .bcontent(entity.getBcontent())
                .bwrite(entity.getBwrite())
                .build();
        return dtos;
    }


}
