package cz.uruba.airportcodes;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import cz.uruba.airportcodes.constants.ContentProviderContracts;
import cz.uruba.airportcodes.utils.DB.DBTableDescriptor;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {

    public ApplicationTest() {
        super(Application.class);
    }

    @SmallTest
    public void testDBDescriptor() {
        String testTableName = "test";
        DBTableDescriptor testDescriptor = new DBTableDescriptor(testTableName);
        assertEquals(testDescriptor.getTableName(), testTableName);

        String testColumnAlias = "COLUMN_TEST_ID";
        String testColumnName = "test_id";
       // testDescriptor.addColumn(testColumnAlias, testColumnName);
        assertEquals(testDescriptor.getColumnName(testColumnAlias), testColumnName);

        assertEquals(ContentProviderContracts.AirportsContract.DB_AIRPORTS.getColumnName("COLUMN_ID"), "id");
    }

}