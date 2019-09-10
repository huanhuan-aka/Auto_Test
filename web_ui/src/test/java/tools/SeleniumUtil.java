package tools;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * 工具类
 * @author wanghuanhuan
 * @version 1.0
 * @since 09.10
 */

public class SeleniumUtil {

    public static final String IE_BROWSER_NAME="ie";
    public static final String FIREFOX_BROWSER_NAME="firefox";
    public static final String CHROME_BROWSER_NAME="chrome";


    public static WebDriver getWebDriver(String browswerType,String driverPath,String seleniumVersion,String browserExePath){
        if(IE_BROWSER_NAME.equalsIgnoreCase(browswerType)){
            return getIEDriver(driverPath);
        }else if(CHROME_BROWSER_NAME.equalsIgnoreCase(browswerType)){
            return getChromeDriver(driverPath);
        }else if(FIREFOX_BROWSER_NAME.equalsIgnoreCase(browswerType)){
            return getFirefoxDriver(driverPath,seleniumVersion,browserExePath);
        }else if("safari".equalsIgnoreCase(browswerType)){
            return getSafariDriver(driverPath);
        }
        return null;
    }

    private static WebDriver getFirefoxDriver(String driverPath, String seleniumVersion, String browserExePath) {
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_BINARY, browserExePath);
        if ("3.x".equalsIgnoreCase(seleniumVersion)) {
            System.setProperty(GeckoDriverService.GECKO_DRIVER_EXE_PROPERTY, driverPath);
        }
        return new FirefoxDriver();
    }

    private static WebDriver getChromeDriver(String driverPath) {
        System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,driverPath);
        return new ChromeDriver();
    }

    private static WebDriver getIEDriver(String driverPath) {
        System.setProperty(InternetExplorerDriverService.IE_DRIVER_EXE_PROPERTY,driverPath);
        DesiredCapabilities capabilities=new DesiredCapabilities();
        capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
        capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING,true);
        capabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL,"https://www.baidu.com/");
        return new InternetExplorerDriver(capabilities);
    }

    @Deprecated
    private static WebDriver getSafariDriver(String driverPath) {
        return null;
    }


}
