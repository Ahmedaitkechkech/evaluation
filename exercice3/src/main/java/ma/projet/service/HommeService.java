package ma.projet.service;

import ma.projet.classes.Homme;
import ma.projet.classes.Mariage;
import ma.projet.dao.Dao;
import org.hibernate.Session;
import org.hibernate.query.Query;
import ma.projet.util.HibernateUtil;

import java.util.Date;
import java.util.List;

public class HommeService extends Dao<Homme> {

    public HommeService() {
        super(Homme.class);
    }

    // Afficher les épouses d'un homme entre deux dates
    public void afficherEpousesEntreDates(int hommeId, Date dateDebut, Date dateFin) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "SELECT m FROM Mariage m WHERE m.homme.id = :hommeId " +
                    "AND m.dateDebut BETWEEN :dateDebut AND :dateFin";
            Query<Mariage> query = session.createQuery(hql, Mariage.class);
            query.setParameter("hommeId", hommeId);
            query.setParameter("dateDebut", dateDebut);
            query.setParameter("dateFin", dateFin);
            List<Mariage> mariages = query.list();

            Homme homme = findById(hommeId);
            if (homme != null) {
                System.out.println("Épouses de " + homme.getNomComplet() + " entre " +
                        dateDebut + " et " + dateFin + ":");

                if (mariages != null && !mariages.isEmpty()) {
                    for (Mariage m : mariages) {
                        System.out.println("- " + m.getFemme().getNomComplet() +
                                " (Marié le: " + m.getDateDebut() +
                                ", Enfants: " + m.getNbrEnfants() + ")");
                    }
                } else {
                    System.out.println("Aucune épouse trouvée dans cette période.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Afficher les mariages d'un homme avec détails
    public void afficherMariagesAvecDetails(int hommeId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "SELECT m FROM Mariage m WHERE m.homme.id = :hommeId ORDER BY m.dateDebut";
            Query<Mariage> query = session.createQuery(hql, Mariage.class);
            query.setParameter("hommeId", hommeId);
            List<Mariage> mariages = query.list();

            Homme homme = findById(hommeId);
            if (homme != null) {
                System.out.println("| Nom : " + homme.getNomComplet() + " |    |");

                if (mariages != null && !mariages.isEmpty()) {
                    System.out.println("| Mariages En Cours :    |    |");
                    int countEnCours = 1;
                    int countEchoues = 1;

                    // Afficher d'abord les mariages en cours
                    for (Mariage m : mariages) {
                        if (m.getDateFin() == null) { // Mariage en cours
                            System.out.println(countEnCours + ". Femme : " + m.getFemme().getNomComplet() +
                                    " | Date Début : " + m.getDateDebut() +
                                    " | Nbr Enfants : " + m.getNbrEnfants());
                            countEnCours++;
                        }
                    }

                    System.out.println("\n| Mariages échoués :    |    |");
                    // Afficher les mariages terminés
                    for (Mariage m : mariages) {
                        if (m.getDateFin() != null) { // Mariage terminé
                            System.out.println(countEchoues + ". Femme : " + m.getFemme().getNomComplet() +
                                    " | Date Début : " + m.getDateDebut() +
                                    " | Date Fin : " + m.getDateFin() +
                                    " | Nbr Enfants : " + m.getNbrEnfants());
                            countEchoues++;
                        }
                    }
                } else {
                    System.out.println("Aucun mariage trouvé pour cet homme.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}