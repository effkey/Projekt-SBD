package dao;

import java.util.List;
import map.Producent;
import map.Producent;


public class ProducentDao extends DAO<Producent>{
    public ProducentDao(){
        this.setmodelClass(map.Producent.class);
    }
    
    @Override
    public List<Producent> search(Producent criteria){
        return null;
    }   
}