package br.com.edu.jet.foodfusion.client.converter;

public abstract class AttributeConverter<K, V> {

    public abstract K to(V target);

    public abstract V from(K source);

}
