package webdriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
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
            System.setProperty("webdriver.gecko.driver", projectPath +"\\browserDrivers\\chromedriver.exe");
        }
        else {
            System.setProperty("webdriver.gecko.driver", projectPath +"/browserDrivers/chromedriver");
        }

//        driver = new FirefoxDriver();
        driver = new ChromeDriver();
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://axiamai.com/");
    }
    @org.testng.annotations.Test
    public  void TC_01_CheckUrl(){
        Assert.assertEquals(driver.getCurrentUrl(),"https://axiamai.com/");
    }

//    @org.testng.annotations.Test
//    public  void TC_02_Logo(){
//        Assert.assertTrue(driver.findElement(By.cssSelector("img.fb_logo")).isDisplayed());
//    }
//

    @org.testng.annotations.Test
    public void TC_02_CheckLogo(){
        Assert.assertTrue(driver.findElement(By.xpath("//a[@class='nav-brand']//img[@alt='logo']")).isDisplayed());
    }


    @AfterClass
    public  void afterClass(){
        driver.quit();
    }

}
