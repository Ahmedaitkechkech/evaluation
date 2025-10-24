package ma.projet.service;

import ma.projet.classes.Commande;
import ma.projet.classes.LigneCommandeProduit;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class LigneCommandeService {

    public void create(LigneCommandeProduit lcp) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(lcp);
        tx.commit();
        session.close();
    }

    public List<LigneCommandeProduit> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<LigneCommandeProduit> lignes = session.createQuery("from LigneCommandeProduit", LigneCommandeProduit.class).list();
        session.close();
        return lignes;
    }

    public LigneCommandeProduit findById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        LigneCommandeProduit ligne = session.get(LigneCommandeProduit.class, id);
        session.close();
        return ligne;
    }

    public void update(LigneCommandeProduit lcp) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(lcp);
        tx.commit();
        session.close();
    }

    public void delete(LigneCommandeProduit lcp) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(lcp);
        tx.commit();
        session.close();
    }

    public List<LigneCommandeProduit> findByCommande(Commande commande) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<LigneCommandeProduit> lignes = session.createQuery(
                        "from LigneCommandeProduit where commande.id = :id", LigneCommandeProduit.class)
                .setParameter("id", commande.getId())
                .list();
        session.close();
        return lignes;
    }
}
