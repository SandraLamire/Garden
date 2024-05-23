package fr.eni.garden.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Garden {
    @Id
    @GeneratedValue
    private int idGarden;

    @Column
    private String location;

    @Column
    private String name;

    @Column
    private Integer surface;

    @Column
    private String city;

}
