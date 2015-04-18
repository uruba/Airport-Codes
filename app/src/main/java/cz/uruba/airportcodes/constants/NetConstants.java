package cz.uruba.airportcodes.constants;

import android.text.TextUtils;

import cz.uruba.airportcodes.exceptions.FeedURLParamEmptyException;

/**
 * Created by Vaclav on 18.4.2015.
 */
public class NetConstants {
    public static final String BASE_URL = "http://server.fseconomy.net/data";

    private static final String FIELD_USER_KEY = "userkey";
    private static final String FIELD_FORMAT = "format";
    private static final String FIELD_QUERY = "query";
    private static final String FIELD_SEARCH = "search";

    private static final char SYMBOL_FIELD_COMMENCEMENT = '?';
    private static final char SYMBOL_FIELD_DELIMITER = '&';
    private static final char SYMBOL_FIELD_EQUALS = '=';

    private static final String DEFAULT_FIELD_FORMAT = "xml";

    private static String URLParamToString (String urlParamName, String urlParamValue, boolean isFirst) {
        if (TextUtils.isEmpty(urlParamName) || TextUtils.isEmpty(urlParamValue)) {
            return null;
        }

        return (isFirst ? SYMBOL_FIELD_COMMENCEMENT : SYMBOL_FIELD_DELIMITER) + urlParamName + SYMBOL_FIELD_EQUALS + urlParamValue;
    }

    private static String URLParamToString (String urlParamName, String urlParamValue) {
        return URLParamToString(urlParamName, urlParamValue, false);
    }


    public static String getFeedUrl(String userkey, String format, String query, String search) throws FeedURLParamEmptyException {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(BASE_URL);


        if (TextUtils.isEmpty(userkey)) {
            throw new FeedURLParamEmptyException(userkey);
        }
        stringBuilder.append(URLParamToString(FIELD_USER_KEY, userkey, true));

        if (TextUtils.isEmpty(format)) {
            format = DEFAULT_FIELD_FORMAT;
        }
        stringBuilder.append(URLParamToString(FIELD_FORMAT, format));

        if (TextUtils.isEmpty(query)) {
            throw new FeedURLParamEmptyException(query);
        }
        stringBuilder.append(URLParamToString(FIELD_QUERY, query));

        if (TextUtils.isEmpty(search)) {
            throw new FeedURLParamEmptyException(search);
        }
        stringBuilder.append(URLParamToString(FIELD_SEARCH, search));


        return stringBuilder.toString();
    }
}
