package Functional;

import Containers.RadiusButtonsBean;
import Containers.XCoordinateButtonsBean;
import Containers.YInputTextBean;
import Entities.FloatContainer;
import Entities.Hit;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class CounterBean {
    @ManagedProperty(value = "#{radiusButtonsBean}")
    private RadiusButtonsBean r;

    @ManagedProperty(value = "#{xCoordinateButtonsBean}")
    private XCoordinateButtonsBean x;

    @ManagedProperty(value = "#{yInputTextBean}")
    private YInputTextBean y;
    private int allCounter = 0;
    private int hitCounter = 0;

    private String message;

    public void process() {
        FloatContainer r = new FloatContainer(this.r.getValue());
        FloatContainer x = new FloatContainer(this.x.getValue());
        FloatContainer y = new FloatContainer(this.y.getValue());

        Hit hit = new Hit(x, y, r);

        if (hit.result()) addHit();
        else addMiss();

        this.message = 1 / 0.935 * r.content() > Math.abs(x.content()) && 1 / 0.935 * r.content() > Math.abs(y.content()) ?
                "" : "При указанном радиусе (" + (int) r.content().floatValue() + ") последняя введённая точка" +
                " (" + x.content() + ";" + y.content() + ") не попадает в область отрисовки";
    }

    private void addHit() {
        allCounter++;
        hitCounter++;
    }

    private void addMiss() {
        allCounter++;
    }

    public int getAllCounter() {
        return allCounter;
    }

    public int getHitCounter() {
        return hitCounter;
    }

    public RadiusButtonsBean getR() {
        return r;
    }

    public void setR(RadiusButtonsBean r) {
        this.r = r;
    }

    public XCoordinateButtonsBean getX() {
        return x;
    }

    public void setX(XCoordinateButtonsBean x) {
        this.x = x;
    }

    public YInputTextBean getY() {
        return y;
    }

    public void setY(YInputTextBean y) {
        this.y = y;
    }

    public String getMessage() {
        return message;
    }
}
