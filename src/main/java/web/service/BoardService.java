package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.domain.BoardEntity;
import web.domain.BoardRepository;
import web.domain.dto.BoardDto;

import java.awt.*;

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



        return null;
    }

}
