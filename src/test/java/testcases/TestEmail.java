package testcases;

import base.TestBase;
import config.TestConfig;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TestEmail extends TestBase {


@BeforeMethod
public void initialize() {
try{
 setup();
}
catch (Exception e ){
 e.printStackTrace();
}
}

   @Test
    public void validateTest() throws InterruptedException, AWTException, IOException {


//       Open the temp mail website
       driver.get(TestConfig.TEMP_MAIL_URL);


//       Copy button to copy the mail id
       WebElement copyBtn = driver.findElement(By.id("click-to-copy"));
       copyBtn.click();


//     Getting the window handle so that we can redirect back and move to yahoo account
       String parentWindow = driver.getWindowHandle();

//       Switch to new window and open yahoo mail
       driver.switchTo().newWindow(WindowType.WINDOW);
       driver.get(TestConfig.YAHOO_ACCOUNT_URL);

//       Fill the credentials(username and pass ) and login into yahoo account
       WebElement gmailUsername = driver.findElement(By.id("login-username"));
       gmailUsername.sendKeys(TestConfig.YAHOO_EMAIL_USERNAME);
       WebElement nextBtn = driver.findElement(By.id("login-signin"));
       nextBtn.click();
       WebElement pass = driver.findElement(By.id("login-passwd"));
       pass.sendKeys(TestConfig.YAHOO_PASSWORD);
       nextBtn = driver.findElement(By.id("login-signin"));
       nextBtn.click();


//       Open yahoo mail
       driver.findElement(By.id("ymail")).click();

//       Click Compose button to create mail
       WebElement composeBtn = driver.findElement(By.xpath("//a[@href='/d/compose/']"));
       composeBtn.click();

//       Paste the mail in "To" field which is previously copied from temp mail
       WebElement toField = driver.findElement(By.id("message-to-field"));
       Robot robot = new Robot();
       Actions actions = new Actions(driver);
       actions.keyDown(Keys.CONTROL);
       actions.sendKeys("v");
       actions.keyUp(Keys.CONTROL);
       actions.build().perform();

//       Fill the Subject of the mail
       WebElement subjectTxt = driver.findElement(By.xpath("//input[@data-test-id='compose-subject']"));
       subjectTxt.click();
       subjectTxt.sendKeys(TestConfig.SUBJECT);

//       Fill the body of the mail
       WebElement bodyTxt = driver.findElement(By.xpath("//div[@data-test-id='rte']"));
       bodyTxt.sendKeys(TestConfig.BODY_TEXT);

//       Click Send button to send the mail
       WebElement sendBtn = driver.findElement(By.xpath("//button[@data-test-id='compose-send-button']"));
       sendBtn.click();

//       Switch to Temp mail website
       driver.switchTo().window(parentWindow);

//      Click and open the mail which is sent from yahoo account
       WebElement mail = driver.findElement(By.xpath("//span[@title='mayankrathore1996@yahoo.com']"));
       JavascriptExecutor js = (JavascriptExecutor)driver;
       js.executeScript("arguments[0].click();", mail);

//As Temp mail blocked me from accessing the mail so I am assuming , it has not blocked so now its time to validate
// the email id , subject and body of the mail
    String subjectText = driver.findElement(By.xpath("Xpath of subject")).getText();
    String bodyText = driver.findElement(By.xpath("Xpath of mail body")).getText();


    //    Validating the subject and body
    Assert.assertEquals(subjectText,TestConfig.SUBJECT);
    Assert.assertEquals(bodyText,TestConfig.BODY_TEXT);

// Taking Screenshot of the mail
    TakesScreenshot scrShot =((TakesScreenshot)driver);
    File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

//    Screenshot is stored in src/main/resources folder
    File DestFile=new File(TestConfig.SCREENSHOT_FILE_PATH);
    FileUtils.copyFile(SrcFile, DestFile);
   }

   @AfterSuite
 public void closeBrowser(){
    driver.quit();
   }
}