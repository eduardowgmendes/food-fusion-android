package br.com.edu.jet.foodfusion.ui.component.content;

import java.util.Objects;

public class SimpleProperty<V> {
    private String key;
    private V value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SimpleProperty)) return false;
        SimpleProperty<?> that = (SimpleProperty<?>) o;
        return Objects.equals(key, that.key) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    @Override
    public String toString() {
        return "SimpleProperty{" +
                "key='" + key + '\'' +
                ", value=" + value +
                '}';
    }
}
