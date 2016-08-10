package org.tiwaria.gradletest;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import com.mobileenerlytics.eagle.tester.appium.EagleTester;
import com.mobileenerlytics.eagle.tester.common.EagleTesterArgument;

import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Hello {
    private AndroidDriver<?> dr;
    Scanner scn1;
    int a = 0;
    static int count = 0;
    static int delay = 0;
    static int delayIncrement = 3 * 1000;
    AndroidDriver driver;
    EagleTester eagleTester;


    @BeforeTest
    public void setUp() throws Exception {
        // Set up desired capabilities and pass the Android app-activity and
        // app-package to Appium
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.VERSION, "4.4.2");
        capabilities.setCapability(MobileCapabilityType.PLATFORM, "Windows");
        capabilities.setCapability("deviceName", "EQYPBIYPZ5FE4HP7");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appPackage", "launcher3.android.com.hivelauncher");
        capabilities.setCapability("appActivity", "com.android.launcher3.Launcher");
        dr.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        dr = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        EagleTesterArgument eagleTesterArgument = new EagleTesterArgument();
        eagleTesterArgument.setArguments(EagleTesterArgument.PACKAGE_NAME, "launcher3.android.com.hivelauncher");
        EagleTester eagleTester = new EagleTester(driver, eagleTesterArgument);
    }

    @Test
    public void setup() throws Exception {
        eagleTester.startMeasure();
        try {
            dr.findElement(By.id("launcher3.android.com.hivelauncher:id/layout")).click();
            dr.findElement(By.name("Calendar    ")).click();
            dr.navigate().back();
            Thread.sleep(5000);
            System.out.println("The Animation test cases successfully pass");
        } catch (Exception e) {
            count = count + 1;
            System.out.println("The Animations test cases fails" + count);
            delayTest();
            eagleTester.stopMeasure();

        }
    }

    @AfterTest
    public void quit() throws Exception {
        if (dr != null) {
            dr.quit();
            eagleTester.finish();
        }
    }

    static void delayTest() {
        delay += delayIncrement;
        System.out.println("New delay:" + delay);
        try {
            Thread.sleep(delay);
        } catch (Exception e) {

        }
    }

}
