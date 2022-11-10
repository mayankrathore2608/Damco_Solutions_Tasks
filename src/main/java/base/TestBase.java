package base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;
import java.util.*;

public class TestBase {
    public static WebDriver driver;

    public static void setup() throws Exception{
    if(driver==null){
     System.setProperty("webdriver.chrome.driver", "C:\\Users\\mayank.rathore\\Desktop\\driver\\chromedriver_104\\chromedriver.exe");
     driver = new ChromeDriver();
  }
    driver.manage().window().maximize();
    driver.manage().deleteAllCookies();
  }
}
