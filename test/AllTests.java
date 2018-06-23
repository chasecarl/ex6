package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        SjavacTest.class,
        LineFactoryTest.class
        // put other test classes here
})
public class AllTests {
}