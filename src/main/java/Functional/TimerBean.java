package Functional;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.management.*;
import javax.servlet.http.HttpSession;
import java.lang.management.ManagementFactory;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;

@ManagedBean
@SessionScoped
public class TimerBean implements TimerBeanMBean {
    private LocalDateTime previous;
    private long summary;
    private int counter;

    public TimerBean() {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);

            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
            ObjectName name = new ObjectName("InfoBeans:name=TimerBean" + session.getId());

            mbs.registerMBean(this, name);
        } catch (MalformedObjectNameException | InstanceAlreadyExistsException | MBeanRegistrationException |
                 NotCompliantMBeanException e) {
            e.printStackTrace();
        }
    }

    public void process() {
        LocalDateTime now = LocalDateTime.now();

        if (previous == null) previous = LocalDateTime.now();
        else summary += Duration.between(previous, now).toMillis();

        previous = now;
        counter++;
    }

    public double getAverage() {
        double result = (double) summary / counter / 1000;
        if (Double.isNaN(result)) result = 0;

        BigDecimal bigDecimal = BigDecimal.valueOf(result);
        bigDecimal = bigDecimal.setScale(3, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }
}
