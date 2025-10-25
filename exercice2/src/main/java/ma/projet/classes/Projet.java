package ma.projet.classes;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "projets")
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_debut")
    private Date dateDebut;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_fin")
    private Date dateFn;

    @ManyToOne
    @JoinColumn(name = "chef_projet_id")
    private Employee chefProjet;

    @OneToMany(mappedBy = "projet")
    private List<Tache> taches;

    // Constructeurs
    public Projet() {}

    public Projet(String nom, Date dateDebut, Date dateFn, Employee chefProjet) {
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFn = dateFn;
        this.chefProjet = chefProjet;
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public Date getDateDebut() { return dateDebut; }
    public void setDateDebut(Date dateDebut) { this.dateDebut = dateDebut; }

    public Date getDateFn() { return dateFn; }
    public void setDateFn(Date dateFn) { this.dateFn = dateFn; }

    public Employee getChefProjet() { return chefProjet; }
    public void setChefProjet(Employee chefProjet) { this.chefProjet = chefProjet; }

    public List<Tache> getTaches() { return taches; }
    public void setTaches(List<Tache> taches) { this.taches = taches; }
}
