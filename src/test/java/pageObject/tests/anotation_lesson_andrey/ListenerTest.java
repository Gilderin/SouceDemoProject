package pageObject.tests.anotation_lesson_andrey;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
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

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }
    @Attachment
    public byte[] saveFailureScreenShot(WebDriver driver){
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = BaseTest.getDriver();
        // Allure ScreenShot and SaveTestLog
        if (driver instanceof WebDriver) {
            System.out.println("Screenshot captured for test case:" + getTestMethodName(result));
            saveFailureScreenShot(driver);
        }
        saveTextLog(getTestMethodName(result) + " failed and screenshot taken!");
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
