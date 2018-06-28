package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        SjavacTest.class,
        LineFactoryTest.class,
        LineFactoryTest.IntVar.class,
        LineFactoryTest.DoubleVar.class,
        LineFactoryTest.Assignment.class,
        LineFactoryTest.CharVar.class
        // put other test classes here
})
public class AllTests {
}