package ma.projet.test;

import ma.projet.classes.*;
import ma.projet.service.*;
import ma.projet.util.HibernateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test{

    public static void main(String[] args) {
        try {
            // Initialisation des services
            EmployeeService employeeService = new EmployeeService();
            ProjetService projetService = new ProjetService();
            TacheService tacheService = new TacheService();
            EmployeeTacheService employeeTacheService = new EmployeeTacheService();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            // Création d'employés
            Employee emp1 = new Employee("Ahmed Hassan", sdf.parse("01/01/2020"), sdf.parse("31/12/2025"));
            Employee emp2 = new Employee("Fatima Zahra", sdf.parse("15/06/2021"), sdf.parse("15/06/2026"));
            employeeService.create(emp1);
            employeeService.create(emp2);

            // Création de projets
            Projet projet1 = new Projet("Gestion de stock", sdf.parse("14/01/2013"), sdf.parse("30/06/2013"), emp1);
            Projet projet2 = new Projet("Site E-commerce", sdf.parse("01/03/2013"), sdf.parse("30/09/2013"), emp1);
            projetService.create(projet1);
            projetService.create(projet2);

            // Création de tâches
            Tache tache1 = new Tache("Analyse", sdf.parse("01/02/2013"), sdf.parse("28/02/2013"), 1500, projet1);
            Tache tache2 = new Tache("Conception", sdf.parse("01/03/2013"), sdf.parse("31/03/2013"), 1200, projet1);
            Tache tache3 = new Tache("Développement", sdf.parse("01/04/2013"), sdf.parse("30/04/2013"), 3000, projet1);
            Tache tache4 = new Tache("Analyse", sdf.parse("01/03/2013"), sdf.parse("31/03/2013"), 800, projet2);
            tacheService.create(tache1);
            tacheService.create(tache2);
            tacheService.create(tache3);
            tacheService.create(tache4);

            // Affectation des tâches aux employés avec dates réelles
            EmployeeTache et1 = new EmployeeTache(emp1, tache1, sdf.parse("10/02/2013"), sdf.parse("20/02/2013"));
            EmployeeTache et2 = new EmployeeTache(emp1, tache2, sdf.parse("10/03/2013"), sdf.parse("15/03/2013"));
            EmployeeTache et3 = new EmployeeTache(emp2, tache3, sdf.parse("10/04/2013"), sdf.parse("25/04/2013"));
            employeeTacheService.create(et1);
            employeeTacheService.create(et2);
            employeeTacheService.create(et3);

            // Tests des fonctionnalités

            System.out.println("=== TEST 1: Tâches réalisées par un employé ===");
            employeeService.afficherTachesRealiseesParEmploye(emp1.getId());

            System.out.println("\n=== TEST 2: Projets gérés par un employé ===");
            employeeService.afficherProjetsGeresParEmploye(emp1.getId());

            System.out.println("\n=== TEST 3: Tâches planifiées pour un projet ===");
            projetService.afficherTachesPlanifieesPourProjet(projet1.getId());

            System.out.println("\n=== TEST 4: Tâches réalisées avec dates réelles ===");
            projetService.afficherTachesRealiseesAvecDatesReelles(projet1.getId());

            System.out.println("\n=== TEST 5: Tâches avec prix > 1000 DH ===");
            tacheService.getTachesPrixSuperieurA1000();

            System.out.println("\n=== TEST 6: Tâches entre deux dates ===");
            tacheService.getTachesEntreDates(sdf.parse("01/02/2013"), sdf.parse("31/03/2013"));

        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.shutdown();
        }
    }
}