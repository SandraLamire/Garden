package fr.eni.garden.entity;

import fr.eni.garden.validator.DateComparison;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate setUpDate = LocalDate.now();

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate harvestDate = LocalDate.now();

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
