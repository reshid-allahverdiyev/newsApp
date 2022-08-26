package newsApp.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tokens")
@Data
public class TokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "token")
    private String token;



}
