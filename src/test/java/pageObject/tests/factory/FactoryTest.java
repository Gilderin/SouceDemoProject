package pageObject.tests.factory;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class FactoryTest {

    @Factory
    @Test
    public Object[] factoryMethod() {
        Object[] tests = new Object[2];
        tests[0] = new SmokeTest();
        tests[1] = new RegressionTest();
        return tests;
    }

}
