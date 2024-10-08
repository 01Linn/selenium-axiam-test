package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
//import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.time.Duration;

public class TestLogin {
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

//      driver = new FirefoxDriver();
        driver = new ChromeDriver();
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://axiamai.com/");
    }
    @org.testng.annotations.Test
    public  void TC_Login01_CheckNavigateToLoginPageWhenClickSign_inButton(){
        Assert.assertEquals(driver.getCurrentUrl(),"https://axiamai.com/");

        //Verify navigate to Login page when click Sign_in button
        WebElement SignIn = driver.findElement(By.xpath("//a[@href='/users/sign_in']"));
        SignIn.click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://axiamai.com/users/sign_in");

        // Verify text--------------------------------------------------------------------------------------------------
        // Locate the element that contains the text
        WebElement WelcomeBack = driver.findElement(By.xpath("//h2[@class='title']"));

        // Get the text from the element
        String actualText = WelcomeBack.getText();

        // Expected text to verify
        String expectedText = "Welcome Back";

        // Assert that the actual text matches the expected text
        Assert.assertEquals(actualText, expectedText, "Text is not displayed correctly!");

    }

    @org.testng.annotations.Test
    public  void TC_Login02_CheckLoginWithCorrectAccount(){

        //Enter valid data into Email, password & click SignIn button
        WebElement Email = driver.findElement(By.xpath("//input[@id='user_email']"));
        Email.sendKeys("ax21@yopmail.com");
        WebElement password = driver.findElement(By.xpath("//input[@id='user_password']"));
        password.sendKeys("123456");
        WebElement SignInButton = driver.findElement(By.xpath("//button[@class='submit btn dream-btn w-100']"));
        SignInButton.click();

        //Verify display successful message-----------------------------------------------------------------------------
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement successMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ajs-message.ajs-success")));

        // Get the text of the alertify success message
        String actualMessage = successMessage.getText();
        // Expected message
        String expectedMessage = "Signed in successfully.";

        // Assert that the message is displayed as expected
        Assert.assertEquals(actualMessage,expectedMessage, "Message returned from script is incorrect!");
    }

    @AfterClass
    public  void afterClass(){
        driver.quit();
    }
}
