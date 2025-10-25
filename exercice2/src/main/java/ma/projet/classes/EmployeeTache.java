package ma.projet.classes;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "employee_tache")
public class EmployeeTache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "tache_id")
    private Tache tache;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_debut_reelle")
    private Date dateDebutReelle;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_fin_reelle")
    private Date dateFnReelle;

    // Constructeurs
    public EmployeeTache() {}

    public EmployeeTache(Employee employee, Tache tache, Date dateDebutReelle, Date dateFnReelle) {
        this.employee = employee;
        this.tache = tache;
        this.dateDebutReelle = dateDebutReelle;
        this.dateFnReelle = dateFnReelle;
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }

    public Tache getTache() { return tache; }
    public void setTache(Tache tache) { this.tache = tache; }

    public Date getDateDebutReelle() { return dateDebutReelle; }
    public void setDateDebutReelle(Date dateDebutReelle) { this.dateDebutReelle = dateDebutReelle; }

    public Date getDateFnReelle() { return dateFnReelle; }
    public void setDateFnReelle(Date dateFnReelle) { this.dateFnReelle = dateFnReelle; }
}