package pageObject.tests.Lida.factoryAnnatation;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class FactoryAnnotation {
    @Factory
    @Test
    public Object[] getTestFactoryMethod() {
        Object[] factoryTest = new Object[2];
        factoryTest[0] = new SmokeTest();
        factoryTest[1] = new RegressionTest();
        return factoryTest;
    }
}
