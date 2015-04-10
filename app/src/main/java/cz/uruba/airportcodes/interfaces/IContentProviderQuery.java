package cz.uruba.airportcodes.interfaces;

import android.database.Cursor;

import cz.uruba.airportcodes.models.Airport;
import cz.uruba.airportcodes.utils.DB.DBTableDescriptor;

/**
 * Created by Vaclav on 2.1.2015.
 */
public interface IContentProviderQuery {
    public String[] getProjection();
    public abstract Airport getAirportFromCursor(Cursor cursor);
}
