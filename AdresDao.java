package dao;

import java.util.ArrayList;
import java.util.List;
import map.Adres;
import map.Kategoria;
import map.Produkt;
import org.hibernate.Session;


public class AdresDao extends DAO<Adres>{
    public AdresDao(){
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
    
        public Adres addAdres(String miasto, int kodPocztowy, 
			String ulica, String nrBudynku, int nrLokalu) {
        Session session = this.getSession();
        session.beginTransaction();
        Adres cat = new Adres();
        cat.setMiasto(miasto);
        cat.setKodPocztowy(kodPocztowy);
        cat.setUlica(ulica);
        cat.setNrBudynku(nrBudynku);
        cat.setNrLokalu(nrLokalu);
        session.persist(cat);
        session.getTransaction().commit();
        session.close();
        return null;
    }
    
    
    
    
    
    @Override
    public List<Adres> search(Adres criteria) {
        return null;
    }   
    
}