package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        SjavacTest.class,
        LineFactoryTest.class
})
public class AllTests {
}