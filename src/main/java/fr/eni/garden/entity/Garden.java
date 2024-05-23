package fr.eni.garden.entity;

import jakarta.persistence.*;
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
    private String name;

    @Column
    private Integer gardenSurface;

    @Column
    private String location;

    @Column
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

    public Integer getRemainingSurface(){
        return this.squareList.isEmpty() ? this.gardenSurface : this.squareList.stream().mapToInt(Square::getSquareSurface).sum();
    }
}
