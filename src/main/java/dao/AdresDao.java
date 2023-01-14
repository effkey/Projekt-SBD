package dao;

import java.util.ArrayList;
import java.util.List;
import map.Adres;
import map.Kategoria;
import map.Produkt;
import org.hibernate.Session;

public class AdresDao extends DAO<Adres> {

    public AdresDao() {
        this.setmodelClass(map.Adres.class);
    }

    public ArrayList<Adres> getAll() {
        Session session = this.getSession();
        session.beginTransaction();
        ArrayList<Adres> cat = (ArrayList<Adres>) session.createQuery(
                " select p "
                + "from map.Adres p ")
                .getResultList();
        session.getTransaction().commit();
        if (cat != null) {
            return cat;
        }

        return null;
    }

    public Adres addAdres(int kodPocztowy, String miasto, String nrBudynku, int nrLokalu, String ulica) {
        Session session = this.getSession();
        session.beginTransaction();
        
        // Przypisz dane
        Adres adres = new Adres();
        adres.setKodPocztowy(kodPocztowy);
        adres.setMiasto(miasto);
        adres.setNrBudynku(nrBudynku);
        adres.setNrLokalu(nrLokalu);
        adres.setUlica(ulica);
        //
        
        session.persist(adres);
        session.getTransaction().commit();
        session.close();
        return null;
    }

    public Adres getAdres(String miasto, Integer kodPocztowy, String ulica, String nrBudynku, Integer nrLokalu) {
        Session session = this.getSession();
        session.beginTransaction();
        Adres adres = null;
        adres = (Adres) session.createQuery(
                "select adres "
                + "from map.Adres adres "
                + "where adres.miasto =:miasto "
                + "and adres.kodPocztowy =:kodPocztowy"
                + "and adres.ulica =:ulica"
                + "and adres.nrBudynku =:nrBudynku"
                + "and adres.nrLokalu =: nrLokalu")
                .setParameter("miasto", miasto)
                .setParameter("kodPocztowy", kodPocztowy)
                .setParameter("ulica", ulica)
                .setParameter("nrBudynku", nrBudynku)
                .setParameter("nrLokalu", nrLokalu)
                .uniqueResult();
        session.getTransaction().commit();
        if (adres != null) {
            return adres;
        }
        return null;
    }

    public Adres getAdres(String miasto, Integer kodPocztowy, String ulica, String nrBudynku) {
        Session session = this.getSession();
        session.beginTransaction();
        Adres adres = null;
        adres = (Adres) session.createQuery(
                "select adres "
                + "from map.Adres adres "
                + "where adres.miasto =:miasto "
                + "and adres.kodPocztowy =:kodPocztowy"
                + "and adres.ulica =:ulica"
                + "and adres.nrBudynku =:nrBudynku")
                .setParameter("miasto", miasto)
                .setParameter("kodPocztowy", kodPocztowy)
                .setParameter("ulica", ulica)
                .setParameter("nrBudynku", nrBudynku)
                .uniqueResult();
        session.getTransaction().commit();
        if (adres != null) {
            return adres;
        }
        return null;
    }

    @Override
    public List<Adres> search(Adres criteria) {
        return null;
    }

}
