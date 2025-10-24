package ma.projet.service;

import ma.projet.classes.Commande;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class CommandeService {
    private List<Commande> commandes = new ArrayList<>();

    public void create(Commande c) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(c);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<Commande> getAll() {
        return commandes;
    }

    public Commande findById(int id) {
        for (Commande c : commandes) {
            if (c.getId() == id) return c;
        }
        return null;
    }

    public void update(Commande c) {
        for (int i = 0; i < commandes.size(); i++) {
            if (commandes.get(i).getId() == c.getId()) {
                commandes.set(i, c);
                break;
            }
        }
    }

    public void delete(Commande c) {
        commandes.remove(c);
    }
}

