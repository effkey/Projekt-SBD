package dao;

import java.util.List;
import map.SposobRealizacji;
import map.SposobRealizacji;

public class SposobRealizacjiDao extends DAO<SposobRealizacji>{
    public SposobRealizacjiDao(){
        this.setmodelClass(map.SposobRealizacji.class);
    }

    @Override
    public List<SposobRealizacji> search(SposobRealizacji criteria) {
        return null;
    }
}
