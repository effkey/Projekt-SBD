package dao;

import java.util.List;
import map.Produkt;
import map.Produkt;

public class ProduktDao extends DAO<Produkt>{
    public ProduktDao(){
        this.setmodelClass(map.Produkt.class);
    }
    
    @Override
    public List<Produkt> search(Produkt criteria){
        return null;
    }   
}
