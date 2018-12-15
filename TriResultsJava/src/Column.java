package TriResultsJava;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Column {

    private String name;
    private int order;

    private List<String> alternativeNames;

    public Column(String name, int order, List<String> alternativeNames) {
        this.name = name;
        this.order = order;

        this.alternativeNames = alternativeNames;
    }

    public final String getName()
    {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return this.order;
    }

    public final int setOrder(int order){
        return order;
    }

    public final List<String> AlternativeNames() {
        return this.alternativeNames;
    }

    public final String GetStandardizedName(String alternativeName) {
        if ((this.name == alternativeName)) {
            return this.name;
        }

        Optional<String> hasMatch = this.alternativeNames.stream().filter(name -> name == alternativeName).findAny();
        if (hasMatch.isPresent()) {
            return this.name;
        }

        return "";
    }
}
