package ma.projet.service;

import ma.projet.classes.EmployeeTache;
import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.dao.Dao;
import org.hibernate.Session;
import org.hibernate.query.Query;
import ma.projet.util.HibernateUtil;

import java.text.SimpleDateFormat;
import java.util.List;

public class ProjetService extends Dao<Projet> {

    public ProjetService() {
        super(Projet.class);
    }

    // Afficher la liste des tâches planifiées pour un projet
    public void afficherTachesPlanifieesPourProjet(int projetId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "SELECT t FROM Tache t WHERE t.projet.id = :projetId";
            Query<Tache> query = session.createQuery(hql, Tache.class);
            query.setParameter("projetId", projetId);
            List<Tache> taches = query.list();

            Projet projet = findById(projetId);
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");

            System.out.println("| Projet : " + projet.getId() + " | Nom : " + projet.getNom() +
                    " | Date début : " + sdf.format(projet.getDateDebut()) + " |");
            System.out.println("Liste des tâches planifiées:");
            System.out.println("Num\tNom\tDate Début\tDate Fin\tPrix");
            for (Tache t : taches) {
                System.out.println(t.getId() + "\t" + t.getNom() + "\t" +
                        t.getDateDebut() + "\t" + t.getDateFn() + "\t" + t.getPrt());
            }
        } finally {
            session.close();
        }
    }

    // Afficher la liste des tâches réalisées avec les dates réelles
    public void afficherTachesRealiseesAvecDatesReelles(int projetId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "SELECT et FROM EmployeeTache et WHERE et.tache.projet.id = :projetId";
            Query<EmployeeTache> query = session.createQuery(hql, EmployeeTache.class);
            query.setParameter("projetId", projetId);
            List<EmployeeTache> employeeTaches = query.list();

            Projet projet = findById(projetId);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            System.out.println("| Projet : " + projet.getId() + " | Nom : " + projet.getNom() +
                    " | Date début : " + sdf.format(projet.getDateDebut()) + " |");
            System.out.println("Liste des tâches réalisées:");
            System.out.println("Num\tNom\tDate Début Réelle\tDate Fin Réelle");
            for (EmployeeTache et : employeeTaches) {
                System.out.println(et.getTache().getId() + "\t" +
                        et.getTache().getNom() + "\t" +
                        sdf.format(et.getDateDebutReelle()) + "\t" +
                        sdf.format(et.getDateFnReelle()));
            }
        } finally {
            session.close();
        }
    }
}