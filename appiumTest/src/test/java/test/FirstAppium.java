/**
 * Company: Alo7
 * FileName: FirstAppium
 * Author: wanghuanhuan
 * CreateTime: 2019-06-09  13:51
 */
package test;

import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Set;

public class FirstAppium {
    public static AndroidDriver<WebElement> androidDriver;
    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        DesiredCapabilities desiredCapabilities=new DesiredCapabilities();
        desiredCapabilities.setCapability("deviceName","192.168.56.101:5555");
        desiredCapabilities.setCapability("platformName","Android");
        desiredCapabilities.setCapability("appPackage","com.lemon.lemonban");
        desiredCapabilities.setCapability("appActivity","com.lemon.lemonban.activity.WelcomeActivity");
        desiredCapabilities.setCapability("noReset",true);//false清除应用的数据，true不清除
        //这个设置是为了获取toast类型的元素信息得到
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
         androidDriver=new AndroidDriver<WebElement>(
                new URL("http://127.0.0.1:4723/wd/hub"),desiredCapabilities
        );
         Thread.sleep(3000);
         /*
        System.out.println("元素页面结构："+androidDriver.getPageSource());
        System.out.println("当前的activity:"+androidDriver.currentActivity());
        System.out.println("配置为："+androidDriver.getCapabilities());

        //信息获取类API
        System.out.println("设备时间："androidDriver.getDeviceTime());
        System.out.println("设备屏幕密度DPI："androidDriver.getDisplayDensity());
        System.out.println("横竖屏状态："+androidDriver.getOrientation());
        */

         testListView("安全测试");
         //测试完毕后要关闭驱动
         androidDriver.quit();

    }

    public void login() throws InterruptedException {
        /* 登录流程：
        1. 打开测试app
        （1）初始化DesiredCapabilities对象
         (2) 初始化驱动
        2。 点击我的柠檬
        3。 点击登录
        4。 在登录页面输入手机号/密码
        5。 点击登录按钮*/
        Thread.sleep(3000);
        androidDriver.findElementById("com.lemon.lemonban:id/navigation_my").click();
        Thread.sleep(3000);
        androidDriver.findElementById("com.lemon.lemonban:id/fragment_my_lemon_avatar_layout").click();
        Thread.sleep(3000);
        androidDriver.findElementById("com.lemon.lemonban:id/et_mobile").sendKeys("12312300001");
        androidDriver.findElementById("com.lemon.lemonban:id/et_password").sendKeys("123456");
        androidDriver.findElementById("com.lemon.lemonban:id/btn_login").click();
        //获取toast信息
        String toastTips=getElement(By.xpath("//*[contains(@text,'错误的账号信息')]")).getText();
        System.out.println(toastTips);
        Thread.sleep(3000);
    }

    /**
     * 自定义通过text文本查找元素的方法
     */
    public static WebElement findElementByText(String text){
      return  androidDriver.findElementByAndroidUIAutomator("new UiSelector().text(\""+text+"\")");
    }

    /**
     自定义下滑操作
     */
    public static void swipeDown(){
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

    /**
     放大
     **/
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

    /**
     定制点击时间
     **/
    public static void tap(){

    }

    /**
     app页面跳转
     **/
    public void startPage(){
        Activity activity=new Activity("com.lemon.lemonban",".activity.LoginActActivity");
        androidDriver.startActivity(activity);
    }


    public static void testHybrid() throws InterruptedException {

        //1. 点击主页上的全程班元素
        findElementByText("全程班").click();
        Thread.sleep(5000);
//        System.out.println(androidDriver.getContextHandles());
//        androidDriver.context("WEBVIEW_com.lemon.lemonban");

        //2. 获取当前web页面所对于的所有contexts
        //3. 切换到webview所对应的context中去
        Set<String> contexts=androidDriver.getContextHandles();
        for (String context : contexts) {
            if(context.contains("WEBVIEW")){
                androidDriver.context(context);
            }
        }
        Thread.sleep(2000);

        //4. 定位到web页面里面的元素（chromedriver）
        androidDriver.findElementByXPath("//*[@id=\"js-bottom-fav\"]").click();
        Thread.sleep(2000);

    }


    /**
     执行adb命令
     **/
    public void execADB(String cmdstr){
        //当前环境运行时对象
        Runtime runtime=Runtime.getRuntime();
        try {
            runtime.exec(cmdstr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
        智能等待优化
     **/
    public static WebElement getElement(By by){
        //1. 初始化webDriverWait
        WebDriverWait webDriverWait=new WebDriverWait(androidDriver,30);

        //2. 使用webDriverWait的until方法
        return webDriverWait.until(new io.appium.java_client.functions.ExpectedCondition<WebElement>() {
            @Override
            public  WebElement apply(WebDriver input){
                return input.findElement(by);
            }
        });
    }

    /**滑动列表找指定的元素
     * @Author wanghuanhuan
     * @Description  //TODO
     * @CreateTime 20:50  2019-06-09
     * @param
     * @return void
     * webDriverWait.until(new ExpectedCondition<WebElement>() {
     *             @Override
     *             public WebElement apply(WebDriver input){
     *
     *                 return androidDriver.findElement(by);
     *             }
     *         });
     **/
    public static void testListView(String targetStr) throws InterruptedException {
        androidDriver.findElement(By.id("com.lemon.lemonban:id/navigation_tiku")).click();
        Thread.sleep(2000);

        WebDriverWait webDriverWait=new WebDriverWait(androidDriver,30);
        webDriverWait.until(new io.appium.java_client.functions.ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver input) {
                androidDriver.findElementsByAndroidUIAutomator(
                        "new UIScrollable(new UiSelector( ).scrollable(true).instance(0)).scrollIntoView(new UiSelector( ).textMatches(\"" + targetStr + "\").instance(0))"
                ).get(0).click();

                return null;
            }
        });
    }

    public void testUnLock(){
        //1. 初始化TouchAction对象
        TouchAction touchAction=new TouchAction(androidDriver);
        //2. 使用touchAction实现滑动
        //java-client升级到6.0+版本后，如果只需要拖动一次的时候，是没有问题的，但连续几次moveTo时，传入的坐标不再是相对坐标了，而是前一个坐标的偏移坐标
        touchAction.press(PointOption.point(180,540)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(200))).
                moveTo(PointOption.point(380,540)).
                moveTo(PointOption.point(200,0));
        //让滑动生效
        touchAction.perform();
    }

}
