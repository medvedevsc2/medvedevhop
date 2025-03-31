package medvedev.dao.entities;

import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sneakers")
public class Sneaker { // Renamed from Product to Sneaker
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand; // Changed to reflect sneaker brand
    private String model; // Added model for sneakers
    private Integer size; // Added size for sneakers
    private Integer price;
    private String color; // Added color for sneakers
    private String description;
    private String city;

    @ManyToMany(mappedBy = "sneakers") // Updated to reflect sneakers
    private List<Order> orders;
}
