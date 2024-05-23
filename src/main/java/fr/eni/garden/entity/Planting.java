package fr.eni.garden.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Square square;

    @ManyToOne
    @JoinColumn
    private Plant plant;

}
