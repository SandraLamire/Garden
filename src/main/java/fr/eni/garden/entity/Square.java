package fr.eni.garden.entity;

import fr.eni.garden.enums.ExposureType;
import fr.eni.garden.enums.SoilType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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

    @OneToMany(mappedBy = "square", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Planting> plantingList;

    @Override
    public String toString() {
        return "\n\t" + "Square{" +
                "idSquare=" + idSquare +
                ", squareSurface=" + squareSurface +
                ", soilType=" + soilType +
                ", exposureType=" + exposureType +
                ", plantingList=" + plantingList +
                '}';
    }

    public Integer getSquareRemainingSurface(){
        return this.plantingList.isEmpty() ?
                this.squareSurface :
                this.squareSurface - this.plantingList.stream().mapToInt(Planting::getPlantingSurface).sum();
    }
}
