package dao;

import java.util.ArrayList;
import java.util.List;
import map.Kategoria;
import map.Produkt;
import map.Produkt;
import org.hibernate.Session;

public class ProduktDao extends DAO<Produkt>{
    public ProduktDao(){
        this.setmodelClass(map.Produkt.class);
    }
    
    @Override
    public List<Produkt> search(Produkt criteria){
        return null;
    }   

    public ArrayList<Produkt> getAll() {
        Session session = this.getSession();
        session.beginTransaction();
        ArrayList<Produkt> cat = (ArrayList<Produkt>) session.createQuery(
                " select p "
                + "from map.Produkt p ")
                .getResultList();
        session.getTransaction().commit();
        if (cat != null) {
            return cat;
        }

        return null;
    }
    
}
