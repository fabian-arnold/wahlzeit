package org.wahlzeit.model.converter;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.wahlzeit.model.AccessRightsTest;
import org.wahlzeit.model.CartesianCoordinateTest;
import org.wahlzeit.model.FlagReasonTest;
import org.wahlzeit.model.GenderTest;
import org.wahlzeit.model.GuestTest;
import org.wahlzeit.model.LocationTest;
import org.wahlzeit.model.PhotoFilterTest;
import org.wahlzeit.model.SphericCoordinateTest;
import org.wahlzeit.model.TagsTest;
import org.wahlzeit.model.UserStatusTest;
import org.wahlzeit.model.ValueTest;
import org.wahlzeit.model.persistence.PersistenceTestSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    CoordinateConverterTest.class
})
public class ConverterTestSuite {

}
