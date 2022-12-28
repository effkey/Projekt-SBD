package dao;

import java.util.ArrayList;
import java.util.List;
import map.Adres;
import map.Magazyn;
import org.hibernate.Session;

public class MagazynDao extends DAO<Magazyn> {

    public MagazynDao() {
        this.setmodelClass(map.Magazyn.class);
    }

    public ArrayList<Magazyn> getAll() {
        Session session = this.getSession();
        session.beginTransaction();
        ArrayList<Magazyn> cat = null;
        cat = (ArrayList<Magazyn>) session.createQuery(
                " select cat "
                + "from map.Magazyn cat ")
                .getResultList();
        session.getTransaction().commit();
        if (cat != null) {
            return cat;
        }

        return null;
    }

        public Magazyn addMagazyn(int pojemnosc, Adres adres) {
        Session session = this.getSession();
        session.beginTransaction();
        Magazyn cat = new Magazyn();
        cat.setPojemnosc(pojemnosc);
        cat.setAdres(adres);
        session.persist(cat);
        session.getTransaction().commit();
        session.close();
        return null;
    }
    
    
    @Override
    public List<Magazyn> search(Magazyn criteria) {
        return null;
    }
}
