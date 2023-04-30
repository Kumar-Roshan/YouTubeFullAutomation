package org.example;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;

import java.util.List;

public class AppTest {
   ChromeDriver driver;
    @BeforeClass
    void setupDriver () {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.get("https://www.youtube.com/");
        driver.manage().window().maximize();
    }

   /* WebDriver driver;
    @BeforeClass
    @Parameters({"browser","url"})
    void setupDriver (String browser,String link) {
        if(browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver=new EdgeDriver();

        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }


        driver.get("https://www.youtube.com/");
        driver.manage().window().maximize();
    }  */
    @Test(priority = 1)
    void loginYouTube() throws InterruptedException {

          driver.findElement(By.xpath("//*[@id=\"buttons\"]/ytd-button-renderer/yt-button-shape/a/yt-touch-feedback-shape/div/div[2]")).click();
          driver.findElement(By.xpath("//*[@id=\"identifierId\"]")).sendKeys("epamca3@gmail.com"); //finalprojectepam@gmail.com
          Thread.sleep(1000);
          driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/div/button")).click();
          Thread.sleep(5000);
          driver.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input")).sendKeys("Qwer@123");
          Thread.sleep(1000);
          driver.findElement(By.xpath("//*[@id=\"passwordNext\"]/div/button/span")).click();
          Thread.sleep(5000);

        String expectedTitle = "YouTube";
        String actualTitle = driver.getTitle();
        Assert.assertTrue(actualTitle.contains(expectedTitle), "Login failed");

    }
    @Test(priority = 2)
    void searchAndPlayVideo()throws InterruptedException{driver.findElement(By.name("search_query")).sendKeys("Jai Jai Shiv Shankar | Lata Mangeshkar, Kishore Kumar | Aap Ki Kasam 1974 Songs ");
        Thread.sleep(1000);
        driver.findElement(By.id("search-icon-legacy")).click();
        Thread.sleep(5000);
        WebElement video = driver.findElement(By.xpath("//*[@id=\"video-title\"]/yt-formatted-string"));
        Actions actions = new Actions(driver);
        actions.moveToElement(video).click().perform();
        Thread.sleep(5000);

    }
    @Test(priority = 3)
    void likeVideo() throws InterruptedException{
        driver.findElement(By.xpath("//*[@id=\"segmented-like-button\"]")).click();
        Thread.sleep(10000);

    }


    @Test(priority = 4)
        void createPlaylist()throws InterruptedException{

        driver.findElement(By.xpath("//*[@id=\"button-shape\"]/button/yt-touch-feedback-shape/div/div[2]")).click();

        driver.findElement(By.xpath("//*[@id=\"items\"]/ytd-menu-service-item-renderer[2]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"content-icon\"]/yt-icon")).click();
        Thread.sleep(1500);
        WebElement playlistName = driver.findElement(By.xpath("//*[@id=\"input-1\"]/input"));
        playlistName.sendKeys("My Playlist");
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"actions\"]/ytd-button-renderer/yt-button-shape/button/yt-touch-feedback-shape/div/div[2]")).click();
        Thread.sleep(1000);
    }
   /*
   @Test(priority = 5)
    void downloadVideo() throws InterruptedException{
        driver.findElement(By.xpath("//*[@id=\"flexible-item-buttons\"]/ytd-download-button-renderer/ytd-button-renderer/yt-button-shape/button/yt-touch-feedback-shape/div/div[2]")).click();
        Thread.sleep(10000);

    }

    */

    @Test(priority = 6)
    void renamePlaylist() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"guide-icon\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"endpoint\"]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"guide-icon\"]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"items\"]/ytd-mini-guide-entry-renderer[4]")).click();
        Thread.sleep(1000);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Scroll down the page by 1000 pixels
        js.executeScript("window.scrollBy(0,1000)");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"view-more\"]/a")).click();
        Thread.sleep(5000);

        WebElement playlistMenu =   driver.findElement(By.xpath("//*[@id=\"edit-button\"]/yt-button-shape/button/yt-touch-feedback-shape/div"));
        playlistMenu.click();
        WebElement playlistNameInput =   driver.findElement(By.xpath("//*[@id=\"input-3\"]/input"));
        //driver.findElement(By.xpath("//*[@id=\"input-3\"]/input")).clear();
        playlistNameInput.clear();
        playlistNameInput.sendKeys("My Renamed Playlist");

        driver.findElement(By.xpath("//*[@id=\"save-button\"]/yt-button-shape/button/yt-touch-feedback-shape/div")).click();
        Thread.sleep(1000);


    }


    @Test(priority = 7)
    void logOut() throws InterruptedException{
        driver.findElement(By.id("avatar-btn")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"items\"]/ytd-compact-link-renderer[4]")).click();
        Thread.sleep(50000);

    }


     @AfterClass
   void closeDriver () {
        driver.close();
    }

}
