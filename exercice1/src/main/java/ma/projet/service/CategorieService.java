package ma.projet.service;

import ma.projet.classes.Categorie;
import java.util.ArrayList;
import java.util.List;

public class CategorieService {
    private List<Categorie> categories = new ArrayList<>();

    public void create(Categorie c) {
        categories.add(c);
    }

    public List<Categorie> getAll() {
        return categories;
    }

    public Categorie findById(int id) {
        for (Categorie c : categories) {
            if (c.getId() == id) return c;
        }
        return null;
    }

    public void update(Categorie c) {
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getId() == c.getId()) {
                categories.set(i, c);
                break;
            }
        }
    }

    public void delete(Categorie c) {
        categories.remove(c);
    }
}

