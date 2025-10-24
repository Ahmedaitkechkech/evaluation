package ma.projet.service;

import ma.projet.classes.*;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.Date;
import java.util.List;

public class ProduitService implements IDao<Produit> {

    @Override
    public void create(Produit o) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        s.persist(o);
        tx.commit();
        s.close();
    }

    @Override
    public void update(Produit o) { /* idem create */ }

    @Override
    public void delete(Produit o) { /* idem create */ }

    @Override
    public Produit findById(int id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Produit p = s.get(Produit.class, id);
        s.close();
        return p;
    }

    @Override
    public List<Produit> findAll() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Produit> produits = s.createQuery("from Produit", Produit.class).list();
        s.close();
        return produits;
    }

    // 🔹 Produits par catégorie
    public List<Produit> findByCategorie(int idCat) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query<Produit> q = s.createQuery("from Produit p where p.categorie.id = :idCat", Produit.class);
        q.setParameter("idCat", idCat);
        List<Produit> list = q.list();
        s.close();
        return list;
    }

    // 🔹 Produits dont le prix > 100 DH (requête nommée)
    public List<Produit> findPrixSuperieur100() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Produit> list = s.createNamedQuery("Produit.findPrixSuperieur100", Produit.class).list();
        s.close();
        return list;
    }

    // 🔹 Produits commandés entre deux dates
    public List<Produit> findProduitsEntreDates(Date d1, Date d2) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query<Produit> q = s.createQuery(
                "select distinct l.produit from LigneCommandeProduit l where l.commande.date between :d1 and :d2",
                Produit.class);
        q.setParameter("d1", d1);
        q.setParameter("d2", d2);
        List<Produit> list = q.list();
        s.close();
        return list;
    }

    // 🔹 Produits d’une commande donnée
    public List<LigneCommandeProduit> findProduitsByCommande(int idCommande) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query<LigneCommandeProduit> q = s.createQuery(
                "from LigneCommandeProduit l where l.commande.id = :idCommande",
                LigneCommandeProduit.class);
        q.setParameter("idCommande", idCommande);
        List<LigneCommandeProduit> list = q.list();
        s.close();
        return list;
    }
}

