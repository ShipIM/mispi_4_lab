package Functional;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class CounterBean {
    private int allCounter = 0;
    private int hitCounter = 0;

    public void addHit() {
        allCounter++;
        hitCounter++;
    }

    public void addMiss() {
        allCounter++;
    }

    public int getAllCounter() {
        return allCounter;
    }

    public int getHitCounter() {
        return hitCounter;
    }
}
