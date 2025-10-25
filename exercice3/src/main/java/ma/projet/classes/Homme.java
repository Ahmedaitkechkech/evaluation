package ma.projet.classes;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "hommes")
@PrimaryKeyJoinColumn(name = "id")
public class Homme extends Personne {

    @OneToMany(mappedBy = "homme")
    private List<Mariage> mariages;

    // Constructeurs
    public Homme() {}

    public Homme(String nom, String prenom, Date dateNaissance) {
        super(nom, prenom, dateNaissance);
    }

    // Getters et Setters
    public List<Mariage> getMariages() { return mariages; }
    public void setMariages(List<Mariage> mariages) { this.mariages = mariages; }
}
