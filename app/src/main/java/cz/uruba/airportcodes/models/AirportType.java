package cz.uruba.airportcodes.models;

/**
 * Created by Vaclav on 31.12.2014.
 */
public class AirportType {
    private int id;
    private String string_ident;

    public AirportType(int id, String string_ident) {
        this.id = id;
        this.string_ident = string_ident;
    }

    public int getId() {
        return id;
    }

    public String getString_ident() {
        return string_ident;
    }
}
