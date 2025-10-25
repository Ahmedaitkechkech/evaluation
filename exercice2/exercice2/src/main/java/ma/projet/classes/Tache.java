package ma.projet.classes;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "taches")
@NamedQuery(
        name = "Tache.findByPrixGreaterThan",
        query = "SELECT t FROM Tache t WHERE t.prt > :prix"
)
public class Tache {
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

    private double prt;

    @ManyToOne
    @JoinColumn(name = "projet_id")
    private Projet projet;

    @OneToMany(mappedBy = "tache")
    private List<EmployeeTache> employeeTaches;

    // Constructeurs
    public Tache() {}

    public Tache(String nom, Date dateDebut, Date dateFn, double prt, Projet projet) {
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFn = dateFn;
        this.prt = prt;
        this.projet = projet;
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

    public double getPrt() { return prt; }
    public void setPrt(double prt) { this.prt = prt; }

    public Projet getProjet() { return projet; }
    public void setProjet(Projet projet) { this.projet = projet; }

    public List<EmployeeTache> getEmployeeTaches() { return employeeTaches; }
    public void setEmployeeTaches(List<EmployeeTache> employeeTaches) { this.employeeTaches = employeeTaches; }
}