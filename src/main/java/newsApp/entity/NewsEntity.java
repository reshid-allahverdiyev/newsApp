package newsApp.entity;


import lombok.Data;
import newsApp.config.statemachine.NewsState;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "news")
public class NewsEntity  extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "body")
    private String body;

    @ManyToOne
    @JoinColumn(name="typeId",insertable = false)
    private TypeEntity type;

    @ManyToMany(mappedBy = "newsEntities")
    private List<AuthorEntity>  authorEntities;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private NewsState state;

}
