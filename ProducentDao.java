package dao;

import java.util.List;
import map.Producent;
import map.Producent;
import org.hibernate.Session;


public class ProducentDao extends DAO<Producent>{
    public ProducentDao(){
        this.setmodelClass(map.Producent.class);
    }
    
    @Override
    public List<Producent> search(Producent criteria){
        return null;
    }   

    
    public Producent getProducent(String nazwaProducenta,String kraj,String opisProducenta){
        System.out.println(nazwaProducenta + "  " + kraj + "  " + opisProducenta);
        Session session = this.getSession();
        session.beginTransaction();
        Producent boss = null;
        boss = (Producent) session.createQuery(
        " select boss "
        +"from map.Producent boss "
        +"where boss.nazwaProducenta =:nazwaProducenta and boss.kraj =:kraj and boss.opisProducenta =:opisProducenta")
        .setParameter("nazwaProducenta", nazwaProducenta)
        .setParameter("kraj", kraj)
        .setParameter("opisProducenta", opisProducenta)
        .uniqueResult();
        
        session.getTransaction().commit();
        if(boss!=null){
            System.out.println(boss.getNazwaProducenta()+ "  " +boss.getKraj() + "  " +boss.getOpisProducenta() );
            return boss;
        }
        return null;
         
    }
    public Producent addProducent(String nazwaProducenta,String kraj,String opisProducenta){
        
        System.out.println(nazwaProducenta +"  "+kraj + "  " + opisProducenta);
        Session session = this.getSession();
        session.beginTransaction();
        Producent boss = new Producent();
        boss.setNazwaProducenta(nazwaProducenta);
        boss.setKraj(kraj);
        boss.setOpisProducenta(opisProducenta);
        session.persist(boss);
        session.getTransaction().commit();
        session.close();

        return null;
        }
}
    


