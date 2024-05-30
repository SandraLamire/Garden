package fr.eni.garden.entity;

import fr.eni.garden.enums.ExposureType;
import fr.eni.garden.enums.SoilType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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
    @NotNull
    private Integer squareSurface;

    @Column
    @NotNull
    private SoilType soilType;

    @Column
    @NotNull
    private ExposureType exposureType;

    @ManyToOne
    @JoinColumn
    @ToString.Exclude
    private Garden garden;

    @Override
    public String toString() {
        return "\n\t" + "Square{" +
                "idSquare=" + idSquare +
                ", squareSurface=" + squareSurface +
                ", soilType=" + soilType +
                ", exposureType=" + exposureType +
                '}';
    }
}
