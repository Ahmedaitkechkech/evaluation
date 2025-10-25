package ma.projet.classes;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee {
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

    @OneToMany(mappedBy = "employee")
    private List<EmployeeTache> employeeTaches;

    @OneToMany(mappedBy = "chefProjet")
    private List<Projet> projetsGerés;

    // Constructeurs
    public Employee() {}

    public Employee(String nom, Date dateDebut, Date dateFn) {
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFn = dateFn;
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

    public List<EmployeeTache> getEmployeeTaches() { return employeeTaches; }
    public void setEmployeeTaches(List<EmployeeTache> employeeTaches) { this.employeeTaches = employeeTaches; }

    public List<Projet> getProjetsGerés() { return projetsGerés; }
    public void setProjetsGerés(List<Projet> projetsGerés) { this.projetsGerés = projetsGerés; }
}