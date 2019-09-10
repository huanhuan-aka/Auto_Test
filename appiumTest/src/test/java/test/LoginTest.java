/**
 * Company: Alo7
 * FileName: LoginTest
 * Author: wanghuanhuan
 * CreateTime: 2019-06-16  12:50
 */
package test;
import base.Base;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import util.ExcelUtil;

public class LoginTest extends Base {

    //在主页上操作
    @Test(priority = 1)
    public void testMainPage(){
        getElement(By.id("com.lemon.lemonban:id/navigation_my")).click();
        getElement(By.id("com.lemon.lemonban:id/fragment_my_lemon_avatar_layout")).click();
    }

    @Test(dataProvider = "getFailureDatas",priority = 5)
    public void loginFailure(String mobilephone,String pwd,String tips) throws InterruptedException {
        getElement(By.id("com.lemon.lemonban:id/et_mobile")).sendKeys(mobilephone);
        getElement(By.id("com.lemon.lemonban:id/et_password")).sendKeys(pwd);
        getElement(By.id("com.lemon.lemonban:id/btn_login")).click();
        //判断登录失败的toast信息文案是否正确
        String toastTips = getElement(By.xpath("//*[contains(@text,'"+tips+"')]")).getText();
 //     String actualTips = getText("提示内容元素");
 //     Assert.assertEquals(toastTips,actualTips);


//        androidDriver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
    }

    @Test(dataProvider = "getSuccessDatas",priority =3)
    public void loginSuccess(String mobilephone,String pwd,String tips) throws InterruptedException {
        getElement(By.id("com.lemon.lemonban:id/et_mobile")).sendKeys(mobilephone);
        getElement(By.id("com.lemon.lemonban:id/et_password")).sendKeys(pwd);
        getElement(By.id("com.lemon.lemonban:id/btn_login")).click();
        //判断登录成功

    }

    @DataProvider
    public static Object[][] getFailureDatas() {
        Object[][] datas = ExcelUtil.readExcel("/register.xlsx", 1, 2, 2, 1, 4);
        return datas;
    }
    @DataProvider
    public static Object[][] getSuccessDatas() {
        Object[][] datas = ExcelUtil.readExcel("/register.xlsx", 2, 8, 8, 1, 4);
        return datas;
    }


    public static void main(String[] args) {
        Object[][] success_datas=getSuccessDatas();
        for (Object[] objects : success_datas) {
            for (Object object : objects) {
                System.out.println(object.toString());
            }
        }
    }

}