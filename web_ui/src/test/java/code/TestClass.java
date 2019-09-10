package code;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import tools.SeleniumUtil;

public class TestClass {
    public static WebDriver driver;

    @BeforeSuite
    @Parameters(value = {"browserType","driverPath","seleniumVersion","browserExePath"})
    public void beforeSuite(String browserType,String driverPath,String seleniumVersion,String browserExePath){
        driver= SeleniumUtil.getWebDriver(browserType,driverPath,seleniumVersion,browserExePath);
    }

    @AfterSuite
    public void aftersuite() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }


    @Test
    public void test1(){
        System.out.println("这是个测试方法test1");
    }
}
