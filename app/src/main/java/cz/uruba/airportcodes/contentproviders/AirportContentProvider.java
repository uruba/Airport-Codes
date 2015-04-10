package cz.uruba.airportcodes.contentproviders;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import cz.uruba.airportcodes.constants.ContentProviderContracts;
import cz.uruba.airportcodes.helpers.AirportHelper;

/**
 * Created by Vaclav on 27.12.2014.
 */
public class AirportContentProvider extends ContentProvider {
    // uriMatcher codes
    private static final int ALL_ENTRIES = 1;
    private static final int AIRPORT_DETAIL = 2;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMatcher.addURI(ContentProviderContracts.AirportsContract.AUTHORITY, ContentProviderContracts.AirportsContract.CONTENT_PATH, ALL_ENTRIES);
        uriMatcher.addURI(ContentProviderContracts.AirportsContract.AUTHORITY, ContentProviderContracts.AirportsContract.CONTENT_PATH + "/#", AIRPORT_DETAIL);
    }

    private AirportHelper DBHelper;


    @Override
    public boolean onCreate() {
        DBHelper = AirportHelper.getInstance(getContext());

        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(ContentProviderContracts.AirportsContract.DB_AIRPORTS.getTableName());

        int uriType = uriMatcher.match(uri);
        switch (uriType) {
            case ALL_ENTRIES:
                break;
            case AIRPORT_DETAIL:
                queryBuilder.appendWhere(
                          ContentProviderContracts.AirportsContract.DB_AIRPORTS.getColumnName("COLUMN_ID")
                        + "="
                        + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException();
        }

        SQLiteDatabase db = DBHelper.getWritableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
