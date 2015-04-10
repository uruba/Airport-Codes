package cz.uruba.airportcodes.constants;

import android.database.Cursor;
import android.net.Uri;

import cz.uruba.airportcodes.interfaces.IContentProviderQuery;
import cz.uruba.airportcodes.models.Airport;
import cz.uruba.airportcodes.utils.DB.DBTableDescriptor;

/**
 * Created by Vaclav on 1.1.2015.
 */
public class ContentProviderContracts {
    public static class AirportsContract {
        public static final String AUTHORITY = "cz.uruba.airportcodes.provider";
        public static final String CONTENT_PATH = "airports";
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + CONTENT_PATH);

        public static final DBTableDescriptor DB_AIRPORTS = new DBTableDescriptor("airports");
        static {
            DB_AIRPORTS.addColumn("COLUMN_ID", "_id", DBTableDescriptor.Column.columnDataType.INT);
            DB_AIRPORTS.addColumn("COLUMN_NAME", "name", DBTableDescriptor.Column.columnDataType.TEXT);
        }

        public enum QueryAirportSummaries implements IContentProviderQuery {
            INSTANCE {
                private final DBTableDescriptor.Column[] columns = {
                        DB_AIRPORTS.getColumn("COLUMN_ID"),
                        DB_AIRPORTS.getColumn("COLUMN_NAME")
                };

                @Override
                public String[] getProjection() {
                    return DBTableDescriptor.getProjection(columns);
                }

                @Override
                public Airport getAirportFromCursor(Cursor cursor) {
                    Airport airport = new Airport(cursor.getInt(0));
                    airport.setName(cursor.getString(1));

                    return airport;
                }
            };
        }

        public enum QueryAirportDetail implements IContentProviderQuery {
            INSTANCE {
                private final DBTableDescriptor.Column[] columns = {
                        DB_AIRPORTS.getColumn("COLUMN_ID"),
                        DB_AIRPORTS.getColumn("COLUMN_NAME")
                };

                @Override
                public String[] getProjection() {
                    return DBTableDescriptor.getProjection(columns);
                }

                @Override
                public Airport getAirportFromCursor(Cursor cursor) {
                    Airport airport = new Airport(cursor.getInt(0));
                    airport.setName(cursor.getString(1));

                    return airport;
                }
            };
        }
    }
}
