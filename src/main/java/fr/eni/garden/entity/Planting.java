package fr.eni.garden.entity;

import fr.eni.garden.validator.DateComparison;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
@DateComparison(startDate = "setUpDate", endDate = "harvestDate", message = "Harvest date must be after set-up date")
public class Planting {

    @Id
    @GeneratedValue
    private Integer idPlanting;

    @Column
    @NotNull
    private Integer quantity;

    @Column
    private Date setUpDate;

    @Column
    private Date harvestDate;

    @ManyToOne
    @JoinColumn
    @ToString.Exclude
    private Square square;

    @ManyToOne
    @JoinColumn
    @NotNull
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

    public Integer getPlantingSurface() {
        return this.getPlant().getPlantSurface() * this.getQuantity();
    }
}
