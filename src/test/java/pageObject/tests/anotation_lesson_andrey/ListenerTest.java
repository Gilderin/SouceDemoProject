package pageObject.tests.anotation_lesson_andrey;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerTest implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test is start");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test is success");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("ERROR");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test is skiped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("Test is Failed");
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Start");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Finish");
    }
}
