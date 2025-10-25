package ma.projet.service;

import ma.projet.classes.Mariage;
import ma.projet.classes.Homme;
import ma.projet.dao.Dao;
import org.hibernate.Session;
import org.hibernate.query.Query;
import ma.projet.util.HibernateUtil;

import java.util.Date;
import java.util.List;

public class MariageService extends Dao<Mariage> {

    public MariageService() {
        super(Mariage.class);
    }

    // Méthode pour afficher le nombre d'hommes mariés à quatre femmes entre deux dates
    public void afficherHommesMarieesQuatreFemmesEntreDates(Date dateDebut, Date dateFin) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            // Requête corrigée
            String hql = "SELECT h FROM Homme h " +
                    "WHERE (SELECT COUNT(m) FROM Mariage m WHERE m.homme = h AND m.dateDebut BETWEEN :dateDebut AND :dateFin) >= 4";

            Query<Homme> query = session.createQuery(hql, Homme.class);
            query.setParameter("dateDebut", dateDebut);
            query.setParameter("dateFin", dateFin);
            List<Homme> hommes = query.list();

            System.out.println("Hommes mariés à au moins 4 femmes entre " +
                    dateDebut + " et " + dateFin + ":");

            if (hommes != null && !hommes.isEmpty()) {
                for (Homme h : hommes) {
                    System.out.println("- " + h.getNomComplet());
                }
            } else {
                System.out.println("Aucun homme trouvé.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}