package Functional;

import Containers.RadiusButtonsBean;
import Containers.XCoordinateButtonsBean;
import Containers.YInputTextBean;
import Entities.FloatContainer;
import Entities.Hit;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.management.*;
import javax.servlet.http.HttpSession;
import java.lang.management.ManagementFactory;

@ManagedBean
@SessionScoped
public class CounterBean extends NotificationBroadcasterSupport implements CounterBeanMBean {
    @ManagedProperty(value = "#{radiusButtonsBean}")
    private RadiusButtonsBean r;

    @ManagedProperty(value = "#{xCoordinateButtonsBean}")
    private XCoordinateButtonsBean x;

    @ManagedProperty(value = "#{yInputTextBean}")
    private YInputTextBean y;
    private int allCounter = 0;
    private int hitCounter = 0;

    private long sequenceNumber = 0;

    public CounterBean() {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);

            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
            ObjectName name = new ObjectName("InfoBeans:name=CounterBean" + session.getId());

            mbs.registerMBean(this, name);
        } catch (MalformedObjectNameException | InstanceAlreadyExistsException | MBeanRegistrationException |
                 NotCompliantMBeanException e) {
            e.printStackTrace();
        }
    }

    public void process() {
        FloatContainer r = new FloatContainer(this.r.getValue());
        FloatContainer x = new FloatContainer(this.x.getValue());
        FloatContainer y = new FloatContainer(this.y.getValue());

        Hit hit = new Hit(x, y, r);

        if (hit.result()) {
            allCounter++;
            hitCounter++;
        } else allCounter++;

        if (!(1 / 0.935 * r.content() > Math.abs(x.content()) && 1 / 0.935 * r.content() > Math.abs(y.content()))) {
            Notification n = new Notification("warning", this,
                    sequenceNumber++, System.currentTimeMillis(), "point goes beyond the rendering area");

            sendNotification(n);
        }
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
}
