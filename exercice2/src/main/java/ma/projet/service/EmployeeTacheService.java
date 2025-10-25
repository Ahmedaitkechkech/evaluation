package ma.projet.service;

import ma.projet.classes.EmployeeTache;
import ma.projet.dao.Dao;

public class EmployeeTacheService extends Dao<EmployeeTache> {

    public EmployeeTacheService() {
        super(EmployeeTache.class);
    }
}