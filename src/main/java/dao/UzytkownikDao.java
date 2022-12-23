package dao;

import map.Uzytkownik;
import java.util.List;
import map.SposobRealizacji;
import map.Uzytkownik;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class UzytkownikDao extends DAO<Uzytkownik>{
    public UzytkownikDao(){
        this.setmodelClass(map.Uzytkownik.class);
    }
    
    @Override
    public List<Uzytkownik> search(Uzytkownik criteria){
        return null;
    }   
    
    public Uzytkownik getUser(String password, String login){
        System.out.println(password +"  "+login);
        Session session = this.getSession();
        session.beginTransaction();
        Uzytkownik user = null;
        user = (Uzytkownik) session.createQuery(
                " select user "
                + "from map.Uzytkownik user "
                + "where user.nickname = :login")
                .setParameter("login", login).uniqueResult();
        
        session.getTransaction().commit();
        if(user!=null){
            System.out.println(user.getImie()+"  "+user.getNazwisko());
            return user;
        }
        return null;
    }
}