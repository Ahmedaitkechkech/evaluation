package ma.projet.test;

import ma.projet.classes.*;
import ma.projet.service.*;
import ma.projet.util.HibernateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

    public static void main(String[] args) {
        try {
            // Initialisation des services
            HommeService hommeService = new HommeService();
            FemmeService femmeService = new FemmeService();
            MariageService mariageService = new MariageService();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            // Création de 5 hommes
            Homme h1 = new Homme("SAFI", "SAID", sdf.parse("15/03/1960"));
            Homme h2 = new Homme("ALAMI", "MOHAMED", sdf.parse("20/07/1965"));
            Homme h3 = new Homme("BENNANI", "AHMED", sdf.parse("10/11/1970"));
            Homme h4 = new Homme("CHRAIBI", "HASSAN", sdf.parse("05/05/1975"));
            Homme h5 = new Homme("DAOUDI", "KARIM", sdf.parse("25/12/1980"));

            hommeService.create(h1);
            hommeService.create(h2);
            hommeService.create(h3);
            hommeService.create(h4);
            hommeService.create(h5);

            // Création de 10 femmes
            Femme f1 = new Femme("RAMI", "SALIMA", sdf.parse("12/04/1962"));
            Femme f2 = new Femme("ALI", "ANAL", sdf.parse("18/09/1968"));
            Femme f3 = new Femme("ALAOUI", "FATIMA", sdf.parse("22/01/1972"));
            Femme f4 = new Femme("ALAMI", "KARIMA", sdf.parse("30/06/1965"));
            Femme f5 = new Femme("BENNANI", "NADIA", sdf.parse("14/08/1970"));
            Femme f6 = new Femme("CHRAIBI", "SOUAD", sdf.parse("08/03/1978"));
            Femme f7 = new Femme("DAOUDI", "LATIFA", sdf.parse("19/11/1982"));
            Femme f8 = new Femme("EL FASSI", "AMINA", sdf.parse("27/02/1975"));
            Femme f9 = new Femme("HASSANI", "ZINEB", sdf.parse("03/07/1980"));
            Femme f10 = new Femme("MOUTAOUAKIL", "HAJAR", sdf.parse("15/12/1985"));

            femmeService.create(f1);
            femmeService.create(f2);
            femmeService.create(f3);
            femmeService.create(f4);
            femmeService.create(f5);
            femmeService.create(f6);
            femmeService.create(f7);
            femmeService.create(f8);
            femmeService.create(f9);
            femmeService.create(f10);

            // Création des mariages
            // SAFI SAID a 4 mariages
            Mariage m1 = new Mariage(h1, f1, sdf.parse("03/09/1990"), null, 4);
            Mariage m2 = new Mariage(h1, f2, sdf.parse("03/09/1995"), null, 2);
            Mariage m3 = new Mariage(h1, f3, sdf.parse("04/11/2000"), null, 3);
            Mariage m4 = new Mariage(h1, f4, sdf.parse("03/09/1989"), sdf.parse("03/09/1990"), 0);

            // ALAMI MOHAMED a 3 mariages
            Mariage m5 = new Mariage(h2, f5, sdf.parse("10/01/1992"), null, 2);
            Mariage m6 = new Mariage(h2, f6, sdf.parse("15/06/1998"), sdf.parse("20/05/2005"), 1);
            Mariage m7 = new Mariage(h2, f7, sdf.parse("25/12/2006"), null, 3);

            // BENNANI AHMED a 4 mariages
            Mariage m8 = new Mariage(h3, f8, sdf.parse("08/03/1995"), null, 2);
            Mariage m9 = new Mariage(h3, f9, sdf.parse("14/07/2000"), sdf.parse("18/09/2010"), 1);
            Mariage m10 = new Mariage(h3, f10, sdf.parse("20/01/2012"), null, 4);
            Mariage m11 = new Mariage(h3, f1, sdf.parse("05/05/2015"), null, 1);

            mariageService.create(m1);
            mariageService.create(m2);
            mariageService.create(m3);
            mariageService.create(m4);
            mariageService.create(m5);
            mariageService.create(m6);
            mariageService.create(m7);
            mariageService.create(m8);
            mariageService.create(m9);
            mariageService.create(m10);
            mariageService.create(m11);

            // Tests des fonctionnalités

            System.out.println("=== TEST 1: Liste des femmes ===");
            femmeService.findAll().forEach(f ->
                    System.out.println("- " + f.getNomComplet() + " (Née: " + f.getDateNaissance() + ")"));

            System.out.println("\n=== TEST 2: Femme la plus âgée ===");
            femmeService.afficherFemmeLaPlusAgee();

            System.out.println("\n=== TEST 3: Épouses d'un homme entre deux dates ===");
            hommeService.afficherEpousesEntreDates(h1.getId(), sdf.parse("01/01/1990"), sdf.parse("31/12/2000"));

            System.out.println("\n=== TEST 4: Nombre d'enfants d'une femme entre deux dates ===");
            femmeService.afficherNombreEnfantsEntreDates(f1.getId(), sdf.parse("01/01/1990"), sdf.parse("31/12/2000"));

            System.out.println("\n=== TEST 5: Femmes mariées au moins deux fois ===");
            femmeService.afficherFemmesMarieesAuMoinsDeuxFois();

            System.out.println("\n=== TEST 6: Hommes mariés à quatre femmes entre deux dates ===");
            mariageService.afficherHommesMarieesQuatreFemmesEntreDates(sdf.parse("01/01/1990"), sdf.parse("31/12/2020"));

            System.out.println("\n=== TEST 7: Mariages d'un homme avec détails ===");
            hommeService.afficherMariagesAvecDetails(h1.getId());

        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
