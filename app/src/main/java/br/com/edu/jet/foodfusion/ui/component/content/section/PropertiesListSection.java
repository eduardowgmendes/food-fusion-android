package br.com.edu.jet.foodfusion.ui.component.content.section;

import java.util.Map;
import java.util.Objects;

public class PropertiesListSection extends AbstractSection {

    private Map<String, String> propertiesList;

    public Map<String, String> getPropertiesList() {
        return propertiesList;
    }

    public void setPropertiesList(Map<String, String> propertiesList) {
        this.propertiesList = propertiesList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PropertiesListSection)) return false;
        if (!super.equals(o)) return false;
        PropertiesListSection that = (PropertiesListSection) o;
        return Objects.equals(propertiesList, that.propertiesList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), propertiesList);
    }

    @Override
    public String toString() {
        return "OverviewSectionPropertiesList{" +
                "properties=" + propertiesList +
                '}';
    }
}
