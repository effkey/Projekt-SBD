package dao;

import java.util.ArrayList;
import java.util.List;
import map.Adres;
import map.Kategoria;
import map.SposobRealizacji;
import map.Uzytkownik;
import map.Zamowienie;
import map.Zamowienie;
import org.hibernate.Session;

public class ZamowienieDao extends DAO<Zamowienie>{
    public ZamowienieDao(){
        this.setmodelClass(map.Zamowienie.class);
    }
    
    
     public List<Zamowienie> getAll() {
        Session session = this.getSession();
        session.beginTransaction();
        ArrayList<Zamowienie> cat = null;
        cat = (ArrayList<Zamowienie>) session.createQuery(
                " select cat "
                + "from map.zamowienie cat ")
                .getResultList();
        session.getTransaction().commit();
        if (cat != null) {
            return cat;
        }

        return null;
    }

    public Zamowienie addZamowienie(String uwagiDoZamowienia,
			Adres adres, SposobRealizacji sposobRealizacji, Uzytkownik uzytkownik) {
        Session session = this.getSession();
        session.beginTransaction();
        Zamowienie cat = new Zamowienie();
        cat.setUwagiDoZamowienia(uwagiDoZamowienia);
        cat.setAdres(adres);
        cat.setSposobRealizacji(sposobRealizacji);
        cat.setUzytkownik(uzytkownik);
        session.persist(cat);
        session.getTransaction().commit();
        session.close();
        return null;
    } 
    
    

    @Override
    public List<Zamowienie> search(Zamowienie criteria) {
        return null;
    }   
}