package cz.uruba.airportcodes.models;

/**
 * Created by Vaclav on 31.12.2014.
 */
public class Country {
    private String code;
    private String name;

    public Country(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
