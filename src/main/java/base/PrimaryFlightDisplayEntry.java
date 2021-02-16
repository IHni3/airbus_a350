package base;

import javafx.beans.property.SimpleStringProperty;

public class PrimaryFlightDisplayEntry {
    private SimpleStringProperty attribute;
    private SimpleStringProperty value;

    public PrimaryFlightDisplayEntry() {
    }

    public PrimaryFlightDisplayEntry(String attribute, String value) {
        this.attribute = new SimpleStringProperty(attribute);
        this.value = new SimpleStringProperty(value);
    }

    public String getAttribute() {
        return attribute.get();
    }

    public void setAttribute(String attribute) {
        this.attribute.set(attribute);
    }

    public String getValue() {
        return value.get();
    }

    public void setValue(String value) {
        this.value.set(value);
    }

    public String toString() {
        return (attribute.get() + " - " + value.get());
    }
}