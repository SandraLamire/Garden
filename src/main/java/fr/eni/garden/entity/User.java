package fr.eni.garden.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Integer idUser;

    @Column(unique = true)
    private String pseudo;

    @Column
    private String password;

    @Column
    private String lastName;

    @Column
    private String firstName;

    @Column
    private String roles;

    public User(String pseudo, String password, String lastName, String firstName, String roles) {
        super();
        this.pseudo = pseudo;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.roles = roles;
    }


}
