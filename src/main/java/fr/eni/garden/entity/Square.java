package fr.eni.garden.entity;

import fr.eni.garden.enums.ExposureType;
import fr.eni.garden.enums.SoilType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table
public class Square {

    @Id
    @GeneratedValue
    private Integer idSquare;

    @Column
    private Integer squareSurface;

    @Column
    private SoilType soilType;

    @Column
    private ExposureType exposureType;

    @ManyToOne
    @JoinColumn
    private Garden garden;

    @OneToMany(mappedBy = "square", fetch = FetchType.EAGER)
    private List<Planting> plantingList;

}
