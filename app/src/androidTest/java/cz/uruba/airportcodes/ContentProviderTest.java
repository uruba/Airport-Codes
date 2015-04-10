package cz.uruba.airportcodes;

import android.test.ProviderTestCase2;

import cz.uruba.airportcodes.constants.ContentProviderContracts;
import cz.uruba.airportcodes.contentproviders.AirportContentProvider;

/**
 * Created by Vaclav on 2.1.2015.
 */
public class ContentProviderTest extends ProviderTestCase2<AirportContentProvider> {

    public ContentProviderTest() {
        super(AirportContentProvider.class, ContentProviderContracts.AirportsContract.AUTHORITY);
    }

}
