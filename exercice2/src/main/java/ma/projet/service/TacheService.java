package ma.projet.service;

import ma.projet.classes.Tache;
import ma.projet.dao.Dao;
import org.hibernate.Session;
import org.hibernate.query.Query;
import ma.projet.util.HibernateUtil;

import java.util.Date;
import java.util.List;

public class TacheService extends Dao<Tache> {

    public TacheService() {
        super(Tache.class);
    }

    // Afficher les tâches dont le prix est supérieur à 1000 DH (requête nommée)
    public List<Tache> getTachesPrixSuperieurA1000() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Tache> query = session.createNamedQuery("Tache.findByPrixGreaterThan", Tache.class);
            query.setParameter("prix", 1000.0);
            List<Tache> taches = query.list();

            System.out.println("Tâches avec prix > 1000 DH:");
            for (Tache t : taches) {
                System.out.println(t.getId() + " - " + t.getNom() + " - " + t.getPrt() + " DH");
            }
            return taches;
        } finally {
            session.close();
        }
    }

    // Afficher les tâches réalisées entre deux dates
    public List<Tache> getTachesEntreDates(Date dateDebut, Date dateFin) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "SELECT t FROM Tache t WHERE t.dateDebut BETWEEN :dateDebut AND :dateFin";
            Query<Tache> query = session.createQuery(hql, Tache.class);
            query.setParameter("dateDebut", dateDebut);
            query.setParameter("dateFin", dateFin);
            List<Tache> taches = query.list();

            System.out.println("Tâches entre " + dateDebut + " et " + dateFin + ":");
            for (Tache t : taches) {
                System.out.println(t.getId() + " - " + t.getNom() + " - Début: " + t.getDateDebut());
            }
            return taches;
        } finally {
            session.close();
        }
    }
}