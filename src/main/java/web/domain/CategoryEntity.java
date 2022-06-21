package web.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter @Setter @ToString @Builder
@AllArgsConstructor @NoArgsConstructor
@Table(name = "category")
public class CategoryEntity {

    @Id
    @GeneratedValue
    private int cno;
    private String cname;

    @Builder.Default
    @OneToMany(mappedBy = "categoryEntity" , cascade = CascadeType.ALL)
    private List<BoardEntity> boardEntityList = new ArrayList<>();

}
