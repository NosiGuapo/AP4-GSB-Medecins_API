package io.github.nosiguapo.ap4medecins.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(
        schema = "gsb_api",
        name = "pays"
)
@JsonIdentityInfo(
        property = "id",
        generator = ObjectIdGenerators.PropertyGenerator.class
)
public class Pays {
    @Id
    @SequenceGenerator(
            name = "pays_id_seq",
            sequenceName = "pays_id_seq",
            schema = "gsb_api",
            allocationSize = 1
    )
    @Column(
            name = "ID",
            columnDefinition = "serial"
    )
    private Long id;

    @NotBlank(message="Veuillez préciser un nom.")
    @Length(min = 4, message = "Le nom doit comporter au moins 3 caractères")
    private String nom;

    @OneToMany(
            mappedBy = "pays"
    )
    private Set<Departement> departement;

    public Pays(Long id, String nom, Set<Departement> departement) {
        this.id = id;
        this.nom = nom;
        this.departement = departement;
    }

    public Pays() {
    }

    @JsonBackReference
    public Set<Departement> getDepartements() {
        return departement;
    }

    public void setDepartements(Set<Departement> departements) {
        this.departement = departements;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
