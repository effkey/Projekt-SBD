package dao;

import java.util.List;
import map.Adres;


public class AdresDao extends DAO<Adres>{
    public AdresDao(){
        this.setmodelClass(map.Adres.class);
    }
    
    @Override
    public List<Adres> search(Adres criteria) {
        return null;
    }   
}