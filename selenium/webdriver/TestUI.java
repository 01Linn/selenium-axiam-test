package webdriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


public class TestUI {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public  void beforeClass(){
        if (osName.contains("Windows")){
            System.setProperty("webdriver.gecko.driver", projectPath +"\\browserDrivers\\geckodriver.exe");
        }
        else {
            System.setProperty("webdriver.gecko.driver", projectPath +"/browserDrivers/geckodriver");
        }

        driver = new FirefoxDriver();
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://www.axiamai.com/");
    }
    @org.testng.annotations.Test
    public  void TC_01_Url(){
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.axiamai.com/");
    }

    @org.testng.annotations.Test
    public  void TC_02_Logo(){
        Assert.assertTrue(driver.findElement(By.cssSelector("img.fb_logo")).isDisplayed());
    }

//    @org.testng.annotations.Test
//    public  void TC_03_Form(){
//        Assert.assertTrue(driver.findElement(By.xpath("form[@data-testid='royal_login_form']")).isDisplayed());
//    }

    @AfterClass
    public  void afterClass(){
        driver.quit();
    }


}
