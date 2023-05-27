package Entities;

public class FloatContainer implements Containable<Float> {
    private final float number;

    public FloatContainer(float number) {
        this.number = number;
    }

    @Override
    public Float content() {
        return number;
    }
}
