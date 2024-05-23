package fr.eni.garden.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Planting {

    @Id
    @GeneratedValue
    private Integer idPlanting;

    @Column
    private Integer quantity;

    @Column
    private LocalDate setUpDate;

    @Column
    private LocalDate harvestDate;

    @ManyToOne
    @JoinColumn
    @ToString.Exclude
    private Square square;

    @ManyToOne
    @JoinColumn
    private Plant plant;

    @Override
    public String toString() {
        return "\n\t\t" + "Planting{" +
                "idPlanting=" + idPlanting +
                ", quantity=" + quantity +
                ", setUpDate=" + setUpDate +
                ", harvestDate=" + harvestDate +
                ", plant=" + plant +
                '}';
    }
}
