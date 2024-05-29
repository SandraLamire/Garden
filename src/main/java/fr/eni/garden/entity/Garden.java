package fr.eni.garden.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Garden {
    @Id
    @GeneratedValue
    private Integer idGarden;

    @Column
    @NotBlank
    private String name;

    @Column
    @NotNull
    private Integer gardenSurface;

    @Column
    @NotBlank
    private String location;

    @Column
    @NotBlank
    private String city;

    @OneToMany(mappedBy = "garden", fetch = FetchType.EAGER)
    private List<Square> squareList;

    @Override
    public String toString() {
        return "Garden{" +
                "idGarden=" + idGarden +
                ", name='" + name + '\'' +
                ", gardenSurface=" + gardenSurface +
                ", location='" + location + '\'' +
                ", city='" + city + '\'' +
                ", squareList=" + squareList +
                '}';
    }

    public Integer getGardenRemainingSurface() {
        return this.squareList.isEmpty() ? this.gardenSurface : this.gardenSurface - this.squareList.stream().mapToInt(Square::getSquareSurface).sum();
    }
}
