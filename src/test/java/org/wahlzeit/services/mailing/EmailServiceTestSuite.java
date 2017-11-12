package org.wahlzeit.services.mailing;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.wahlzeit.services.EmailAddressTest;
import org.wahlzeit.services.LogBuilderTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    EmailServiceTest.class
})
public class EmailServiceTestSuite {

}
