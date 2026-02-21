package listeners;

import com.example.framework.annotations.MobileTest;
import com.example.framework.annotations.WebTest;
import com.example.framework.core.TestContext;
import com.example.framework.enums.PlatformType;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.SkipException;

public class TestAnnotationListener implements IInvokedMethodListener {
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        PlatformType platform = TestContext.getPlatform();
        boolean isWebTest = method.getTestMethod().getConstructorOrMethod().getMethod().isAnnotationPresent(WebTest.class)
                || method.getTestMethod().getRealClass().isAnnotationPresent(WebTest.class);
        boolean isMobileTest = method.getTestMethod().getConstructorOrMethod().getMethod().isAnnotationPresent(MobileTest.class)
                || method.getTestMethod().getRealClass().isAnnotationPresent(MobileTest.class);

        if (platform == PlatformType.WEB && isMobileTest && !isWebTest) {
            throw new SkipException("Skipping MobileTest as platform is WEB");
        } else if ((platform == PlatformType.ANDROID || platform == PlatformType.IOS) && isWebTest && !isMobileTest) {
            throw new SkipException("Skipping WebTest as platform is MOBILE");
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        // No action needed
    }
}