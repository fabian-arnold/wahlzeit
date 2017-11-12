package org.wahlzeit.services;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.wahlzeit.services.mailing.EmailServiceTest;
import org.wahlzeit.services.mailing.EmailServiceTestSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    LogBuilderTest.class,
    EmailAddressTest.class,
    EmailServiceTestSuite.class
})
public class ServicesTestSuite {

}
