package newsApp.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Coffee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String brand;
    private String origin;
    private String characteristics;

    public Coffee(String brand, String origin, String characteristics) {
        this.brand = brand;
        this.origin = origin;
        this.characteristics = characteristics;
    }

    public Coffee() {

    }
}
