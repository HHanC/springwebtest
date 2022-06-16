package web.dto;

import lombok.*;
import web.domain.MemberEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
@Builder
public class MemberDto {

    private int mno;
    private String mtitle;
    private String mcontent;
    private String mwrite;
    private String mpassword;



    public MemberEntity toentity(){
        return MemberEntity.builder()
                .mno(this.mno)
                .mtitle(this.mtitle)
                .mwrite(this.mwrite)
                .mpassword(this.mpassword)
                .build();

    }

}
