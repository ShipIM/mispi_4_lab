package Containers;

import java.util.Map;

public abstract class CommandButtonsBean {
    private Map<Float, Boolean> conditions;
    private Float value;

    public CommandButtonsBean(Map<Float, Boolean> conditions) {
        this.conditions = conditions;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public boolean getCondition(float key) {
        return this.conditions.get(key);
    }

    public void changeCondition(float key) {
        this.conditions.entrySet().forEach((entry) -> entry.setValue(false));
        this.conditions.replace(key, false, true);

        if (conditions.containsKey(key))
            this.setValue(key);
    }

    public void setDefault() {
        this.conditions.entrySet().forEach((entry) -> entry.setValue(false));
        this.value = null;
    }

    public Map<Float, Boolean> getConditions() {
        return conditions;
    }

    public void setConditions(Map<Float, Boolean> conditions) {
        this.conditions = conditions;
    }
}
