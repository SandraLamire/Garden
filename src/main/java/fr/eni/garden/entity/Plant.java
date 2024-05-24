package fr.eni.garden.entity;

import fr.eni.garden.enums.PlantType;
import jakarta.persistence.*;
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
    private String name;

    @Column
    private PlantType plantType;

    @Column
    private String variety;

    @Column
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
}
