package ma.projet.classes;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "femmes")
@PrimaryKeyJoinColumn(name = "id")
public class Femme extends Personne {

    @OneToMany(mappedBy = "femme")
    private List<Mariage> mariages;

    // Constructeurs
    public Femme() {}

    public Femme(String nom, String prenom, Date dateNaissance) {
        super(nom, prenom, dateNaissance);
    }

    // Getters et Setters

    public List<Mariage> getMariages() { return mariages; }
    public void setMariages(List<Mariage> mariages) { this.mariages = mariages; }
}