package ma.projet.service;

import ma.projet.classes.Employee;
import ma.projet.classes.EmployeeTache;
import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.dao.Dao;
import org.hibernate.Session;
import org.hibernate.query.Query;
import ma.projet.util.HibernateUtil;

import java.util.List;

public class EmployeeService extends Dao<Employee> {

    public EmployeeService() {
        super(Employee.class);
    }

    // Afficher la liste des tâches réalisées par un employé
    public void afficherTachesRealiseesParEmploye(int employeeId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "SELECT et FROM EmployeeTache et WHERE et.employee.id = :employeeId";
            Query<EmployeeTache> query = session.createQuery(hql, EmployeeTache.class);
            query.setParameter("employeeId", employeeId);
            List<EmployeeTache> employeeTaches = query.list();

            System.out.println("Tâches réalisées par l'employé " + employeeId + ":");
            System.out.println("Num\tNom Tâche\tDate Début Réelle\tDate Fin Réelle");
            for (EmployeeTache et : employeeTaches) {
                System.out.println(et.getTache().getId() + "\t" +
                        et.getTache().getNom() + "\t" +
                        et.getDateDebutReelle() + "\t" +
                        et.getDateFnReelle());
            }
        } finally {
            session.close();
        }
    }

    // Afficher la liste des projets gérés par un employé
    public void afficherProjetsGeresParEmploye(int employeeId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "SELECT p FROM Projet p WHERE p.chefProjet.id = :employeeId";
            Query<Projet> query = session.createQuery(hql, Projet.class);
            query.setParameter("employeeId", employeeId);
            List<Projet> projets = query.list();

            System.out.println("Projets gérés par l'employé " + employeeId + ":");
            for (Projet p : projets) {
                System.out.println("Projet: " + p.getId() + " - " + p.getNom() +
                        " (Du " + p.getDateDebut() + " au " + p.getDateFn() + ")");
            }
        } finally {
            session.close();
        }
    }
}