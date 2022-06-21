package web.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.domain.BoardEntity;
import web.domain.BoardRepository;
import web.domain.CategoryEntity;
import web.domain.CategoryRepository;
import web.domain.dto.BoardDto;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {


@Autowired
private BoardRepository boardRepository;

@Autowired
private CategoryRepository categoryRepository;

    public boolean board(BoardDto boardDto){

        boolean sw = false;
        int cno = 0;
        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();
        for(CategoryEntity entity : categoryEntityList){
            if(entity.getCname().equals(boardDto.getCategoty())){
                sw = true;
                cno = entity.getCno();
            }
        }
        CategoryEntity categoryEntity = null;
            if(!sw){
                categoryEntity = CategoryEntity.builder()
                        .cname(boardDto.getCategoty())
                        .build();
                categoryRepository.save(categoryEntity);
            }else{
                categoryEntity = categoryRepository.findById(cno).get();
            }
        BoardEntity boardEntity = boardRepository.save(boardDto.toentity());

        boardEntity.setCategoryEntity(categoryEntity);
        categoryEntity.getBoardEntityList().add(boardEntity);
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

/*    public JSONObject view(int bno){
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
    }*/

    @Transactional
    public JSONObject getview(int bno){

        Optional<BoardEntity> optional = boardRepository.findById(bno);
        BoardEntity entity = optional.get();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("bno" , entity.getBno());
        jsonObject.put("bcontent" , entity.getBcontent());
        jsonObject.put("bwrite" , entity.getBwrite());
        return jsonObject;

    }

    @Transactional
    public boolean delete(int bno){
        BoardEntity boardEntity = boardRepository.findById(bno).get();
        boardRepository.delete(boardEntity);
        return true;
    }

    @Transactional
    public boolean update(BoardDto boardDto){
        Optional<BoardEntity> optional = boardRepository.findById(boardDto.getBno());
        BoardEntity boardEntity = optional.get();
        boardEntity.setBcontent(boardDto.getBcontent());
        return true;
    }

    public JSONArray getcategorylist(){

        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();
        JSONArray jsonArray = new JSONArray();
        for(CategoryEntity entity : categoryEntityList){
            JSONObject object = new JSONObject();
            object.put("cno" , entity.getCno());
            object.put("cname" , entity.getCname());
            jsonArray.put(object);
        }
        return jsonArray;
    }



}
