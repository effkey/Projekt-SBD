package dao;

import java.util.ArrayList;
import java.util.Date;
import map.Uzytkownik;
import java.util.List;
import map.Kategoria;
import map.SposobRealizacji;
import map.Uzytkownik;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class UzytkownikDao extends DAO<Uzytkownik>{
    public UzytkownikDao(){
        this.setmodelClass(map.Uzytkownik.class);
    }

    @Override
    public List<Uzytkownik> search(Uzytkownik criteria) {
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
                + "where user.nickname = :login and user.password =:password")
                .setParameter("login", login)
                .setParameter("password", password)
                .uniqueResult();
        
        session.getTransaction().commit();
        if(user!=null){
            System.out.println(user.getImie()+"  "+user.getNazwisko());
            return user;
        }
        return null;
    }

    public Uzytkownik addUser(String firstname, String surname, String login,  String password, Date dataZalozeniaKonta, String email, boolean admin){
        Session session = this.getSession();
        session.beginTransaction();
        Uzytkownik user = new Uzytkownik();
        user.setImie(firstname);
        user.setNazwisko(surname);
        user.setNickname(login);
        user.setPassword(password);
        user.setDataZalozeniaKonta(dataZalozeniaKonta);
        user.setEmail(email);
        user.setUprawnieniaAdministratora(admin);
        session.persist(user);
        session.getTransaction().commit();
        session.close();

                return null;
    }
    public Uzytkownik changeName(int ID, String name)
    {
        Session session = this.getSession();
        session.beginTransaction();
        Uzytkownik user =  null;
        user = (Uzytkownik) session.get(Uzytkownik.class, ID);
        user.setImie(name);
        session.update(user);
        session.getTransaction().commit();
        session.close(); 
        return null;
    }
        public Uzytkownik changeSurname(int ID, String surname)
    {
        Session session = this.getSession();
        session.beginTransaction();
        Uzytkownik user =  null;
        user = (Uzytkownik) session.get(Uzytkownik.class, ID);
        user.setNazwisko(surname);
        session.update(user);
        session.getTransaction().commit();
        session.close(); 
        return null;
    }
        
        public Uzytkownik changeEmail(int ID, String email)
    {
        Session session = this.getSession();
        session.beginTransaction();
        Uzytkownik user =  null;
        user = (Uzytkownik) session.get(Uzytkownik.class, ID);
        user.setEmail(email);
        session.update(user);
        session.getTransaction().commit();
        session.close();
        return null;
    }
        
        public Uzytkownik changePassword(int ID, String password)
    {
        Session session = this.getSession();
        session.beginTransaction();
        Uzytkownik user =  null;
        user = (Uzytkownik) session.get(Uzytkownik.class, ID);
        user.setPassword(password);
        session.update(user);
        session.getTransaction().commit();
        session.close();
        return null;
    }
        public Uzytkownik changeNickname(int ID, String nickname)
    {
        Session session = this.getSession();
        session.beginTransaction();
        Uzytkownik user =  null;
        user = (Uzytkownik) session.get(Uzytkownik.class, ID);
        user.setNickname(nickname);
        session.update(user);
        session.getTransaction().commit();
        session.close();
        return null;
    }
    
    
        public ArrayList<Uzytkownik> getAll() {
        Session session = this.getSession();
        session.beginTransaction();
        ArrayList<Uzytkownik> cat = null;
        cat = (ArrayList<Uzytkownik>) session.createQuery(
                " select cat "
                + "from map.Uzytkownik cat ")
                .getResultList();
        session.getTransaction().commit();
        if (cat != null) {
            return cat;
        }

        return null;
    }
        
        
        
}
