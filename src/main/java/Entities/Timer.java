package Entities;

import java.text.DecimalFormat;

public class Timer {
    private final Resultable<Boolean> resultable;

    public Timer(Resultable<Boolean> resultable) {
        this.resultable = resultable;
    }

    public float time() {
        long startTime = System.nanoTime();
        resultable.result();
        long endTime = System.nanoTime();

        DecimalFormat df = new DecimalFormat("#.#");
        df.setMaximumFractionDigits(8);

        return (endTime - startTime) / 1000000f;
    }
}
