package web.domain.dto;

import lombok.*;
import web.domain.BoardEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
@Builder
public class BoardDto {

    private int bno;
    private String btitle;
    private String bcontent;
    private String bwrite;
    private String bpassword;
    private String categoty;

    public BoardEntity toentity(){
        BoardEntity boardEntity = BoardEntity.builder()
                .bno(this.bno)
                .btitle(this.btitle)
                .bwrite(this.bwrite)
                .bcontent(this.bcontent)
                .bpassword(this.bpassword)
                .build();
        return boardEntity;
    }
}
