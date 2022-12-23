
package dao;

import java.util.List;
import map.Kategoria;
import map.Kategoria;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author bdawi
 */
public class KategoriaDao extends DAO<Kategoria> {
    public KategoriaDao(){
        this.setmodelClass(map.Kategoria.class);
    }
    @Override
    public List<Kategoria> search(Kategoria criteria){
        return null;
    } 
    public Kategoria getKategoria( String nazwaKategorii, String opisKategorii){
        System.out.println(nazwaKategorii +"  "+opisKategorii);
        Session session = this.getSession();
        session.beginTransaction();
        Kategoria cat = null;
        cat = (Kategoria) session.createQuery(
                " select cat "
                + "from map.Kategoria cat "
                + "where cat.nazwaKategorii = :nazwaKategorii and cat.opisKategorii =:opisKategorii")
                .setParameter("nazwaKategorii", nazwaKategorii)
                .setParameter("opisKategorii", opisKategorii)
                .uniqueResult();
        
        session.getTransaction().commit();
        if(cat!=null){
            System.out.println(cat.getNazwaKategorii()+"  " + cat.getOpisKategorii());
            return cat;
        }
        
        return null;
    }
        public Kategoria addKategoria(String nazwaKategorii, String opisKategorii ){
        Session session = this.getSession();
        session.beginTransaction();
        Kategoria cat = new Kategoria();
        cat.setNazwaKategorii(nazwaKategorii);
        cat.setOpisKategorii(opisKategorii);
        session.persist(cat);
        session.getTransaction().commit();
        session.close();
        return null;
        }
        public Kategoria findKategoria(String nazwaKategorii)
       {
        Session session = this.getSession();
        session.beginTransaction();
        Kategoria cat = null;
       cat = (Kategoria) session.createQuery(
               " select cat.idKategoria, cat.nazwaKategorii, cat.opisKategorii "
                + "from map.Kategoria cat "
                + "where cat.nazwaKategorii = :nazwaKategorii")
                .setParameter("nazwaKategorii", nazwaKategorii)
               .uniqueResult();
        
        session.getTransaction().commit();
        if(cat!=null){
           System.out.println( cat.getOpisKategorii() + " " + cat.getIdKategoria());
           return cat;
        }
            return null;
        }
}

