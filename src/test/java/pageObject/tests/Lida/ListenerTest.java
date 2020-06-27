package pageObject.tests.Lida;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerTest implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("ERROR, take a screenshot!");
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("All started!");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("All finished");
    }
}
