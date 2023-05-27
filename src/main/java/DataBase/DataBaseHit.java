package DataBase;

import javax.persistence.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "hit")
public class DataBaseHit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "r")
    private float r;
    @Column(name = "x")
    private float x;
    @Column(name = "y")
    private float y;
    @Column(name = "result")
    private boolean result;
    @Column(name = "request_time")
    private LocalDateTime requestTime;
    @Column(name = "handle_time")
    private float handleTime;

    @Transient
    private final DecimalFormat FORMAT = new DecimalFormat("#.#####");

    public DataBaseHit() {

    }

    public DataBaseHit(float r, float x, float y, boolean result, LocalDateTime requestTime,
                       float handleTime) {
        this.r = r;
        this.x = x;
        this.y = y;
        this.result = result;
        this.requestTime = requestTime;
        this.handleTime = handleTime;
    }

    public String getR() {
        return FORMAT.format(r).replace(",", ".");
    }

    public String getX() {
        return FORMAT.format(x).replace(",", ".");
    }

    public String getY() {
        return FORMAT.format(y).replace(",", ".");
    }

    public boolean isResult() {
        return result;
    }

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public String getHandleTime() {
        return FORMAT.format(handleTime).replace(",", ".");
    }

    public String formattedRequestTime() {
        return this.requestTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    @Override
    public boolean equals(Object o) {
        if (this.getClass() != o.getClass()) return false;

        DataBaseHit second = (DataBaseHit) o;

        return this.result == second.result
                && this.r == second.r
                && this.x == second.x
                && this.y == second.y
                && this.handleTime == second.handleTime
                && this.requestTime.equals(second.requestTime);
    }
}
