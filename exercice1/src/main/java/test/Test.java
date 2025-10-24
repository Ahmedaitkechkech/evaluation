package test;

import ma.projet.classes.*;
import ma.projet.service.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class Test {
    public static void main(String[] args) {
        ProduitService produitService = new ProduitService();
        CommandeService commandeService = new CommandeService();
        LigneCommandeService ligneCommandeService = new LigneCommandeService();

        // ----------------------------
        // 1️⃣ Création des produits
        // ----------------------------
        Produit p1 = new Produit();
        p1.setReference("REF001");
        p1.setPrix(100);
        produitService.create(p1);

        Produit p2 = new Produit();
        p2.setReference("REF002");
        p2.setPrix(250);
        produitService.create(p2);

        Produit p3 = new Produit();
        p3.setReference("REF003");
        p3.setPrix(150);
        produitService.create(p3);

        // ----------------------------
        // 2️⃣ Création de la commande
        // ----------------------------
        Commande c = new Commande();
        c.setDate(LocalDate.of(2013, 3, 14));
        commandeService.create(c); // ✅ Enregistrer avant utilisation

        // ----------------------------
        // 3️⃣ Création des lignes de commande
        // ----------------------------
        LigneCommandeProduit l1 = new LigneCommandeProduit();
        l1.setCommande(c);
        l1.setProduit(p1);
        l1.setQuantite(7);
        ligneCommandeService.create(l1);

        LigneCommandeProduit l2 = new LigneCommandeProduit();
        l2.setCommande(c);
        l2.setProduit(p2);
        l2.setQuantite(9);
        ligneCommandeService.create(l2);

        LigneCommandeProduit l3 = new LigneCommandeProduit();
        l3.setCommande(c);
        l3.setProduit(p3);
        l3.setQuantite(4);
        ligneCommandeService.create(l3);

        // ----------------------------
        // 4️⃣ Affichage des produits commandés
        // ----------------------------
        System.out.println("=== Liste des produits de la commande du 14/03/2013 ===");
        List<LigneCommandeProduit> lignes = ligneCommandeService.findByCommande(c);

        for (LigneCommandeProduit l : lignes) {
            System.out.println(
                    l.getProduit().getReference() + "\t" +
                            l.getProduit().getPrix() + "\t" +
                            l.getQuantite()
            );
        }
    }
}

