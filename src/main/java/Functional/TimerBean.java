package Functional;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;

@ManagedBean
@SessionScoped
public class TimerBean {
    private LocalDateTime previous;
    private long summary;
    private int counter;

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
