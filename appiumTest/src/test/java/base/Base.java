/**
 * Company: Alo7
 * FileName: Base
 * Author: wanghuanhuan
 * CreateTime: 2019-06-22  17:25
 */
package base;

import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.functions.ExpectedCondition;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Base {
    public static AndroidDriver<WebElement> androidDriver;

    @BeforeClass
    @Parameters(value = {"devicesName", "platformName", "appPackage", "appActivity", "appiumUrl"})
    public static void setUp(String devicesName, String platformName, String appPackage, String appActivity, String appiumUrl) throws MalformedURLException {
        //初始化
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("deviceName", devicesName);
        desiredCapabilities.setCapability("platformName", platformName);
        desiredCapabilities.setCapability("appPackage", appPackage);
        desiredCapabilities.setCapability("appActivity", appActivity);
        desiredCapabilities.setCapability("noReset", true);//false清除应用的数据，true不清除
        //这个设置是为了获取toast类型的元素信息得到
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
        androidDriver = new AndroidDriver<WebElement>(
                new URL(appiumUrl), desiredCapabilities
        );
    }

    //智能等待
    public static WebElement getElement(By by) {

        WebDriverWait webDriverWait = new WebDriverWait(androidDriver, 30);

        WebElement webElement=webDriverWait.until(new ExpectedCondition<WebElement>(){
            @Override
            public WebElement apply(WebDriver input){
                return androidDriver.findElement(by);
            }
        });
        return webElement;
    }

    //执行adb命令
    public void execADB(String cmdstr){
        //当前环境运行时对象
        Runtime runtime=Runtime.getRuntime();
        try {
            runtime.exec(cmdstr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //自定义通过text文本查找元素的方法
    public static WebElement findElementByText(String text){
        return  androidDriver.findElementByAndroidUIAutomator("new UiSelector().text(\""+text+"\")");
    }

    //自定义下滑操作
    public void swipeDown(){
        //1. 初始化touchAction对象，和 触摸相关操作
        TouchAction touchAction=new TouchAction(androidDriver);
        //2. 通过手机屏幕的高度/宽度计算起始点
        int screenWidth=androidDriver.manage().window().getSize().width;
        int screenHeight=androidDriver.manage().window().getSize().height;
        //起始点坐标
        int startx=screenWidth/2;
        int starty=screenHeight/2;
        //终止点坐标
        int endx=startx;
        int endy=screenHeight*3/4;
        Duration duration=Duration.ofMillis(5000);
        touchAction.press(PointOption.point(startx,starty)).
                waitAction(WaitOptions.waitOptions(duration)).
                moveTo(PointOption.point(endx,endy)).release();
        touchAction.perform();
    }

    //放大
    public static void zoom(int x,int y){

        MultiTouchAction multiTouchAction=new MultiTouchAction(androidDriver);
        int scrHeight=androidDriver.manage().window().getSize().getHeight();
        int yOffset=100;
        if(y-100<0){
            yOffset=y;
        }else if (y+100>scrHeight){
            yOffset=scrHeight-y;
        }
        TouchAction action0=new TouchAction(androidDriver).press(PointOption.point(x,y)).moveTo(PointOption.point(0,-yOffset)).release();
        TouchAction action1=new TouchAction(androidDriver).press(PointOption.point(x,y)).moveTo(PointOption.point(0,yOffset)).release();
        multiTouchAction.add(action0).add(action1);
    }

    //app页面跳转
    public void startPage(){
        Activity activity=new Activity("com.lemon.lemonban",".activity.LoginActActivity");
        androidDriver.startActivity(activity);
    }

    //测试完毕后要关闭驱动
    @AfterClass
    public void tearDown() {
        androidDriver.quit();
    }
}
