package newsApp.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "news")
public class NewsEntity {
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

}
