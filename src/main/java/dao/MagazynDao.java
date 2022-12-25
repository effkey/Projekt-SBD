package dao;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.List;
import map.Magazyn;
import map.Magazyn;


public class MagazynDao extends DAO<Magazyn>{
    public MagazynDao(){
        this.setmodelClass(map.Magazyn.class);
    }

    @Override
    public List<Magazyn> search(Magazyn criteria) {
        return null;
    }   
}