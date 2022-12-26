package dao;

import java.util.List;
import map.Zamowienie;
import map.Zamowienie;

public class ZamowienieDao extends DAO<Zamowienie>{
    public ZamowienieDao(){
        this.setmodelClass(map.Zamowienie.class);
    }

    @Override
    public List<Zamowienie> search(Zamowienie criteria) {
        return null;
    }   
}