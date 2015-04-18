package cz.uruba.airportcodes;

import android.test.AndroidTestCase;
import android.util.Log;

import cz.uruba.airportcodes.constants.NetConstants;
import cz.uruba.airportcodes.exceptions.FeedURLParamEmptyException;

/**
 * Created by Vaclav on 18.4.2015.
 */
public class NetConstantsTest extends AndroidTestCase {

    private final String consoleTag = "AIRPORT_TEST";

    String SHOULD_BE = "http://server.fseconomy.net/data?userkey=AF93TO4QNE&format=xml&query=aircraft&search=configs";

    String userkey = "AF93TO4QNE";
    String format = "xml";
    String query = "aircraft";
    String search = "configs";


    public void testFeedURLCreationWithFormatExplicitlyStated() {
        try {
            assertEquals(NetConstants.getFeedUrl(userkey, format, query, search), SHOULD_BE);
        } catch (FeedURLParamEmptyException e) {
            Log.e(consoleTag, e.getMessage());
        }
    }

    public void testFeedURLCreationWithFormatNULL() {
        try {
            assertEquals(NetConstants.getFeedUrl(userkey, null, query, search), SHOULD_BE);
        } catch (FeedURLParamEmptyException e) {
            Log.e(consoleTag, e.getMessage());
        }
    }

}
