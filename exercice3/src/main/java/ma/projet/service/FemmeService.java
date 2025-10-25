package ma.projet.service;

import ma.projet.classes.Femme;
import ma.projet.dao.Dao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class FemmeService extends Dao<Femme> {

    public FemmeService() {
        super(Femme.class);
    }

    /**
     * 🔹 Retourner le nombre d'enfants d'une femme entre deux dates
     * en utilisant une requête JPQL (pas SQL natif)
     */
    public long getNombreEnfantsEntreDates(int femmeId, Date dateDebut, Date dateFin) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = """
                SELECT COALESCE(SUM(m.nbrEnfants), 0)
                FROM Mariage m
                WHERE m.femme.id = :femmeId
                AND m.dateDebut BETWEEN :dateDebut AND :dateFin
                """;

            Query<Number> query = session.createQuery(hql, Number.class);
            query.setParameter("femmeId", femmeId);
            query.setParameter("dateDebut", dateDebut);
            query.setParameter("dateFin", dateFin);

            Number result = query.getSingleResult();
            return result != null ? result.longValue() : 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            session.close();
        }
    }

    /**
     * 🔹 Afficher le nombre d'enfants d'une femme entre deux dates
     */
    public void afficherNombreEnfantsEntreDates(int femmeId, Date dateDebut, Date dateFin) {
        long nbrEnfants = getNombreEnfantsEntreDates(femmeId, dateDebut, dateFin);
        Femme femme = findById(femmeId);
        if (femme != null) {
            System.out.println("Nombre d'enfants de " + femme.getNomComplet() +
                    " entre " + dateDebut + " et " + dateFin + ": " + nbrEnfants);
        } else {
            System.out.println("Femme non trouvée avec l'ID: " + femmeId);
        }
    }

    /**
     * 🔹 Retourner les femmes mariées au moins deux fois
     */
    public List<Femme> getFemmesMarieesAuMoinsDeuxFois() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = """
                SELECT f FROM Femme f
                WHERE SIZE(f.mariages) >= 2
                """;
            Query<Femme> query = session.createQuery(hql, Femme.class);
            return query.list();
        } finally {
            session.close();
        }
    }

    public void afficherFemmesMarieesAuMoinsDeuxFois() {
        List<Femme> femmes = getFemmesMarieesAuMoinsDeuxFois();
        if (femmes == null || femmes.isEmpty()) {
            System.out.println("Aucune femme mariée au moins deux fois.");
            return;
        }
        System.out.println("Femmes mariées au moins deux fois :");
        for (Femme f : femmes) {
            System.out.println("- " + f.getNomComplet() + " (" + f.getMariages().size() + " mariages)");
        }
    }

    /**
     * 🔹 Trouver la femme la plus âgée
     */
    public Femme getFemmeLaPlusAgee() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "FROM Femme f ORDER BY f.dateNaissance ASC";
            Query<Femme> query = session.createQuery(hql, Femme.class);
            query.setMaxResults(1);
            return query.uniqueResult();
        } finally {
            session.close();
        }
    }

    public void afficherFemmeLaPlusAgee() {
        Femme femme = getFemmeLaPlusAgee();
        if (femme != null) {
            System.out.println("Femme la plus âgée : " + femme.getNomComplet() +
                    " (née le " + femme.getDateNaissance() + ")");
        } else {
            System.out.println("Aucune femme trouvée.");
        }
    }

    /**
     * 🔹 Afficher toutes les femmes
     */
    public void afficherListeFemmes() {
        List<Femme> femmes = findAll();
        if (femmes == null || femmes.isEmpty()) {
            System.out.println("Aucune femme trouvée.");
            return;
        }
        System.out.println("Liste des femmes :");
        for (Femme f : femmes) {
            System.out.println("- " + f.getNomComplet() +
                    " (née le: " + f.getDateNaissance() +
                    ", mariages: " + (f.getMariages() != null ? f.getMariages().size() : 0) + ")");
        }
    }
}
