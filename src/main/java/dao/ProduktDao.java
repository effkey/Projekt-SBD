
package dao;

import java.util.ArrayList;
import java.util.List;
import map.Kategoria;
import map.Producent;
import map.Produkt;
import map.Produkt;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ProduktDao extends DAO<Produkt> {

    public ProduktDao() {
        this.setmodelClass(map.Produkt.class);
    }

    @Override
    public List<Produkt> search(Produkt criteria) {
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

    public void updateProdukt(Produkt obj) {
        Session session = this.getSession();
        session.beginTransaction();
//        Produkt produkt = (Produkt) session.createQuery(
//                "select p "
//                + "from map.Produkt p "
//                + "where p.idProduktu =:id")
//                .setParameter("id", obj.getIdProduktu())
//                .uniqueResult();
//        session.getTransaction().commit();
        Produkt produkt = session.load(Produkt.class, obj.getIdProduktu());
        System.out.println(produkt.getNazwaObrazka());
        produkt.setCena(obj.getCena());
        produkt.setKategoria(obj.getKategoria());
        produkt.setLiczbaSztuk(obj.getLiczbaSztuk());
        produkt.setNazwaObrazka(obj.getNazwaObrazka());
        produkt.setNazwaProduktu(obj.getNazwaProduktu());
        produkt.setOpis(obj.getOpis());
        produkt.setProducent(obj.getProducent());
        System.out.println(produkt.getNazwaObrazka());
//
//        session = this.getSession();
//        session.beginTransaction();
        session.update(produkt);
        session.getTransaction().commit();
        
    }
    
        public Produkt addProdukt(String nazwaProduktu, float cena,
            String opis, float masa, Kategoria kategoria, Producent producent, int liczbaSztuk, String nazwaObrazka) {
        Session session = this.getSession();
        session.beginTransaction();
        Produkt cat = new Produkt();
        cat.setCena(cena);
        cat.setKategoria(kategoria);
        cat.setLiczbaSztuk(liczbaSztuk);
        cat.setNazwaObrazka(nazwaObrazka);
        cat.setNazwaProduktu(nazwaProduktu);
        cat.setOpis(opis);
        cat.setProducent(producent);
        session.persist(cat);
        session.getTransaction().commit();
        session.close();
        return null;
    } 
    
    
}

