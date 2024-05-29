package fr.eni.garden.entity;

import fr.eni.garden.enums.PlantType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table
public class Plant {

    @Id
    @GeneratedValue
    private Integer idPlant;

    @Column
    @NotBlank
    private String name;

    @Column
    @NotNull
    private PlantType plantType;

    @Column
    @NotBlank
    private String variety;

    @Column
    @NotNull
    private Integer plantSurface;

    @Override
    public String toString() {
        return "\n\t\t\t" + "Plant{" +
                "idPlant=" + idPlant +
                ", name='" + name + '\'' +
                ", plantType=" + plantType +
                ", variety='" + variety + '\'' +
                ", plantSurface=" + plantSurface +
                '}';
    }

    public String getNameAndVariety(){
        return name + " " + variety;
    }

    public String getNameVarietyAndSurface(){
        return name + " " + variety + " " + plantSurface + " cm²";
    }
}
