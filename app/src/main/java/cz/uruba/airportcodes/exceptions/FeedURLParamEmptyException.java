package cz.uruba.airportcodes.exceptions;

/**
 * Created by Vaclav on 18.4.2015.
 */
public class FeedURLParamEmptyException extends Exception {
    private String fieldName;

    private FeedURLParamEmptyException() {
        super();
    }

    public FeedURLParamEmptyException(String fieldName) {
        super();
        this.fieldName = fieldName;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " | Field " + fieldName + " cannot be empty.";
    }
}
