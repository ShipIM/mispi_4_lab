package DataBase;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ApplicationScoped
public class HitService implements Serializable {

    @ManagedProperty(value = "#{dataBaseHitDAO}")
    private DAO<DataBaseHit, Integer> hit;

    public HitService() {

    }

    public HitService(DAO<DataBaseHit, Integer> hit) {
        this.hit = hit;
    }

    public List<DataBaseHit> getAll() {
        return hit.getAll();
    }

    public void create(DataBaseHit hit) {
        this.hit.create(hit);
    }

    public DAO<DataBaseHit, Integer> getHit() {
        return hit;
    }

    public void setHit(DAO<DataBaseHit, Integer> hit) {
        this.hit = hit;
    }
}
